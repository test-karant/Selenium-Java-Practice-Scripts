package Automate_broken_link;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Broken_links_Ext {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless=new");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.get("https://www.google.com/");

		List<WebElement> links = driver.findElements(By.xpath("//a[@href]"));
		
		System.out.println("Number of Links are "+links.size());

		List <String> urlList = new ArrayList<String>();

		for(WebElement e:links) {
			String url = e.getDomProperty("href");
			urlList.add(url);
			//checkBrokenLink(url);
		}
		Long stTime = System.currentTimeMillis();
		urlList.parallelStream().forEach(e -> checkBrokenLink(e));
		long fTime = System.currentTimeMillis();
		System.out.println("Total time took: "+(fTime-stTime)/1000+" S");
		driver.quit();
	}
	public static void checkBrokenLink(String linkUrl) {
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			huc.setConnectTimeout(5000);
			huc.connect();

			if(huc.getResponseCode()>=400) {
				System.out.println(linkUrl + " ====> "+ huc.getResponseMessage() + " -> a Broken link");
			}else {
				System.out.println(linkUrl + " ====> "+ huc.getResponseMessage()+" => "+huc.getResponseCode());
			}
		}catch(Exception e){

		}	
	}
}