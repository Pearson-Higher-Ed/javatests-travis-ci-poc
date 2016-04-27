package utilities;

import molecules.moleculesPageObjects.AppHeaderPageObjects;
import molecules.moleculesPageObjects.ContextualHelpPageObjects;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Parameters;

import elements.elementsPageObjects.ResponsiveUtilitiesPageObjects;
import elements.elementsPageObjects.TypographyPageObjects;
import elements.elementsPageObjects.ButtonsPageObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by umahaea on 2/3/16.
 */
public class BaseClass {

    public static WebDriver driver;
    public static AppiumDriver appium;
    public static ResponsiveUtilitiesPageObjects respPgObj;
    public static TypographyPageObjects typoPgObj;
    public static ButtonsPageObjects btnPgObj;
    public static AppHeaderPageObjects appHeaderPgObj;
    public static ContextualHelpPageObjects conxHelpPgObj;
    public static CommonUtils commonUtils;
    final static String USERNAME = System.getenv("SAUCE_USERNAME");
    final static String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    DesiredCapabilities caps;

    @Parameters({"runEnv", "travis", "desktop", "platform", "sauceBrowser", "sauceBrowserVer", "localBrowser", "mobile", "appiumDriver", "mobDeviceName", "mobilePlatformVer", "mobBrowser", "appiumVer"})
    @BeforeSuite(alwaysRun = true)
    protected void setUp(String runEnv, String travis, String desktop, String platform, String sauceBrowser, String sauceBrowserVer, String localBrowser, String mobile, String appiumDriver, String mobDeviceName, String mobilePlatformVer, String mobBrowser, String appiumVer) throws MalformedURLException {

        caps = new DesiredCapabilities();

        //The below conditions is to set capabilities for desktop run and in testng.xml -> set mobile to 'off'/groups to 'desktop' and desktop to 'on' and followed by platform details
        if (runEnv.equals("sauce")) {

            if (desktop.equals("on")) {
                //The below conditions is to launch the respective browser driver on Sauce machine
                if (sauceBrowser.equals("chrome")) {
                    caps = DesiredCapabilities.chrome();
                } else if (sauceBrowser.equals("firefox")) {
                    caps = DesiredCapabilities.firefox();
                } else if (sauceBrowser.equals("ie")) {
                    caps = DesiredCapabilities.internetExplorer();
                } else if (sauceBrowser.equals("safari")) {
                    caps = DesiredCapabilities.safari();
                } else if (sauceBrowser.equals("edge")) {
                    caps = DesiredCapabilities.edge();
                }
                caps.setCapability("platform", platform);
                caps.setCapability("version", sauceBrowserVer);
                caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
                caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
                driver = new RemoteWebDriver(new URL(URL), caps);
                respPgObj = new ResponsiveUtilitiesPageObjects(driver);
                typoPgObj = new TypographyPageObjects(driver);
                btnPgObj = new ButtonsPageObjects(driver);
                appHeaderPgObj = new AppHeaderPageObjects(driver);
                conxHelpPgObj = new ContextualHelpPageObjects(driver);
                commonUtils = new CommonUtils(driver);
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }

            //The below conditions is to set capabilities for mob device and in testng.xml -> set desktop to 'off'/groups to 'mobile' and mobile to 'on' and followed by platform details
            else if (mobile.equals("on")) {
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, mobDeviceName);
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilePlatformVer);
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, mobBrowser);
                caps.setCapability(MobileCapabilityType.APPIUM_VERSION, appiumVer);
                caps.setCapability("tunnel-identifier", System.getenv("TRAVIS_JOB_NUMBER"));
                caps.setCapability("build", System.getenv("TRAVIS_BUILD_NUMBER"));
                if (appiumDriver.equals("iOS")) {
                    appium = new IOSDriver(new URL(URL), caps);
                } else if (appiumDriver.equals("android")) {
                    appium = new AndroidDriver(new URL(URL), caps);
                }
                respPgObj = new ResponsiveUtilitiesPageObjects(appium);
                typoPgObj = new TypographyPageObjects(appium);
                btnPgObj = new ButtonsPageObjects(appium);
                appHeaderPgObj = new AppHeaderPageObjects(appium);
                conxHelpPgObj = new ContextualHelpPageObjects(appium);
                commonUtils = new CommonUtils(appium);
                appium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }
        }

        //The below else condition is to launch browser driver on your local machine. In testng.xml -> set runEnv != sauce
        else {
            if (localBrowser.equals("firefox")) {
                driver = new FirefoxDriver();
                respPgObj = new ResponsiveUtilitiesPageObjects(driver);
                typoPgObj = new TypographyPageObjects(driver);
                btnPgObj = new ButtonsPageObjects(driver);
                appHeaderPgObj = new AppHeaderPageObjects(driver);
                conxHelpPgObj = new ContextualHelpPageObjects(driver);
                commonUtils = new CommonUtils(driver);
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }
        }
    }

    @Parameters({"mobile"})
    @AfterSuite(alwaysRun = true)
    public void tearDown(String mobile) {
        if (mobile.equals("on")) {
            appium.close();
            appium.quit();
        } else {
            // driver.close();
            driver.quit();
        }
    }
}