package facebookapiparser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebBrowser {

    private FirefoxProfile fx = null;
    private WebDriver wd = null;

    public WebBrowser() {
        fx = new FirefoxProfile();
        fx.setPreference("browser.helperApp.neverAsk.saveToDisk", "application/pdf");
    }

    public WebDriver getBrowser() {
        ProfilesIni allProfiles = new ProfilesIni();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Public\\chromedriver.exe");
        DesiredCapabilities caps = new DesiredCapabilities();
    caps.setJavascriptEnabled(true);                      
    setCaps(caps);
        try {
            wd = new PhantomJSDriver(caps);
            return wd;
        } catch (Exception e) {
            System.out.println("Getting page failed");
            e.printStackTrace();
            return null;
        }
    }

    private void setCaps(DesiredCapabilities caps) {
        String os = System.getProperty("os.name");
        if(os.contains("Linux")){
            caps.setCapability(
        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
        "/tmp/phantomjs");
        }
        else{
         caps.setCapability(
        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
        "C:\\Users\\Public\\phantomjs.exe");   
        }
    }
}
