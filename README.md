## Selenium Java Practice Scripts

This repository contains a collection of Java-based Selenium test scripts designed to automate and validate various web interactions.  
One of the key scenarios included is the identification and verification of all hyperlinks on a webpage to determine whether they are functional or broken.

---

### 🔗 Example: Automating Link Validation

The provided script demonstrates how to:

- ✅ Extract all anchor (`<a>`) tags from a webpage.
- 🔍 Retrieve the `href` attribute of each link.
- 🌐 Send HTTP requests to each URL and capture the response status.
- 📊 Classify links based on their HTTP response codes (e.g., `200 OK`, `404 Not Found`).
- 📝 Report the status of each link, indicating whether it is **functional** or **broken**.
