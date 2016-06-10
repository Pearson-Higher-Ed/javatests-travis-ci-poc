package origamiV2Tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.BaseClass;

import java.lang.reflect.Method;

/**
 * Created by umahaea on 6/10/16.
 */
public class DrawerTest extends BaseClass {

    private final String drawerUrl = "http://localhost:8000/src/main/java/origamiV2/fixtures/drawer/drawer.html";
    private boolean isDrawerOpened = false;
    private boolean isDrawerClosed = false;
    private String contentInDrawer = "";
    private boolean isContentInDrawer = false;
    private static String setMobile;

    final static Logger log = Logger.getLogger(DrawerTest.class.getName());

    @DataProvider(name = "Open Drawer Test Data")
    public Object[][] getOpenDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.leftDrawerOpened},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.rightDrawerOpened}
        };
    }
    //Open Drawer
    @Test(testName = "Open Drawer Test", dataProvider = "Open Drawer Test Data", groups = {"desktop-regression", "origamiV2"})
    private void openDrawerTest(String drawerType, By drawerLinkElement, By drawerOpenStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl);

        commonUtils.click(drawerLinkElement);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);
    }

    @DataProvider(name = "Toggle Drawer Test Data")
    public Object[][] getToggleDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.toggleLeftDrawerLink, drawerPgObj.closeLeftDrawerLink, drawerPgObj.leftDrawerOpened, drawerPgObj.leftDrawerClosed},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.toggleRightDrawerLink, drawerPgObj.closeRightDrawerLink, drawerPgObj.rightDrawerOpened, drawerPgObj.rightDrawerClosed}
        };
    }
    //Toggle Drawer
    @Test(testName = "Toggle Drawer Test", dataProvider = "Toggle Drawer Test Data", groups = {"desktop-regression", "origamiV2"})
    private void toggleDrawerTest(String drawerType, By openDrawerLinkElement, By toggleDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl);

        commonUtils.click(openDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementsVisibleOnPage(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        commonUtils.clickUsingJS(toggleDrawerLinkElement);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);

        commonUtils.clickUsingJS(toggleDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementsVisibleOnPage(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        commonUtils.clickUsingJS(closeDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);
    }

    @DataProvider(name = "Close Drawer Test Data")
    public Object[][] getCloseDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.closeLeftDrawerLink, drawerPgObj.leftDrawerOpened, drawerPgObj.leftDrawerClosed},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.closeRightDrawerLink, drawerPgObj.rightDrawerOpened, drawerPgObj.rightDrawerClosed}
        };
    }
    //Close Drawer
    @Test(testName = "Close Drawer Test", dataProvider = "Close Drawer Test Data", groups = {"desktop-regression", "origamiV2"})
    private void closeDrawerTest(String drawerType, By openDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl);

        //Step 1: Open Drawer
        commonUtils.click(openDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Step 2: Close Drawer
        commonUtils.clickUsingJS(closeDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);
    }

    //Use data-target instead of href
    @Test(testName = "Use Data Target Test", groups = {"desktop-regression", "origamiV2"})
    private void useDataTargetForDrawerTest() throws Exception {
        commonUtils.getUrl(drawerUrl);

        commonUtils.click(drawerPgObj.useDataTargetButton);
        isDrawerOpened = commonUtils.isElementPresent(drawerPgObj.rightDrawerOpened);
        Assert.assertTrue(isDrawerOpened);
        contentInDrawer = commonUtils.getText(drawerPgObj.rightDrawerOpened);

        isContentInDrawer = commonUtils.assertCSSProperties(contentInDrawer.toString(), contentInDrawer, new String[]{"Using data-target instead of href.", "Using data-target instead of href. "});
        if (isContentInDrawer == false) {
            log.info("Error: Data Target is not working as per the spec");
        }
        Assert.assertTrue(isContentInDrawer);
    }

    @DataProvider(name = "Use API Test Data")
    public Object[][] getToggleStatusTextDrawerTest() {
        return new Object[][]{
                {"left drawer", drawerPgObj.toggleLeftDrawerLink},
                {"right drawer", drawerPgObj.toggleRightDrawerLink}
        };
    }
    //Use API
    @Test(testName = "Use API Drawer Test", dataProvider = "Use API Test Data", groups = {"desktop-regression", "origamiV2"})
    private void useAPIDrawerTest(String drawerType, By element) throws Exception {
        String toggleStatusText = "";
        boolean isToggleStatusText = false;

        commonUtils.getUrl(drawerUrl);
        //Test1- open the drawer
        commonUtils.click(element);
        Thread.sleep(1000);
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText);
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is opened", drawerType + " open() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);

        //Test2- close the drawer
        commonUtils.clickUsingJS(element);
        Thread.sleep(1000);
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText);
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is closed", drawerType + " close() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);
    }

    @DataProvider(name = "Other Drawer Test Data")
    public Object[][] getOtherDrawerTestData() {
        return new Object[][]{
                {"left drawer", drawerPgObj.openLeftDrawerLink, drawerPgObj.leftDrawerOpened, drawerPgObj.leftDrawerClosed, drawerPgObj.otherLeftDrawerLink, drawerPgObj.otherLeftDrawerOpened, drawerPgObj.otherRightDrawerClosed},
                {"right drawer", drawerPgObj.openRightDrawerLink, drawerPgObj.rightDrawerOpened, drawerPgObj.rightDrawerClosed, drawerPgObj.otherRightDrawerLink, drawerPgObj.otherRightDrawerOpened, drawerPgObj.otherRightDrawerClosed}
        };
    }
    //close other drawers
    @Test(testName = "Other Drawer Test", dataProvider = "Other Drawer Test Data", groups = {"desktop-regression", "origamiV2"})
    private void otherDrawerTest(String drawerType, By openDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement, By otherDrawerLinkElement, By otherDrawerOpenedStatusElement, By otherDrawerClosedStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl);

        //Open Drawer
        commonUtils.clickUsingJS(openDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Open other Drawer
        commonUtils.clickUsingJS(otherDrawerLinkElement);
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementPresent(otherDrawerOpenedStatusElement);
        Assert.assertTrue(isDrawerOpened);

        //Verify if the first drawer is closed
        Thread.sleep(1000);
        System.out.println(commonUtils.getAttributeValue(drawerClosedStatusElement, "class"));
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement);
        Assert.assertTrue(isDrawerClosed);
    }

    /****************
     * Mobile Tests *
     ****************/

    //Open Drawer
    @Test(testName = "Mobile: Open Drawer Test", dataProvider = "Open Drawer Test Data", groups = {"mobile-regression", "origamiV2"})
    private void openDrawerMobileTest(String drawerType, By drawerLinkElement, By drawerOpenStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl, "mobile");

        commonUtils.click(drawerLinkElement, "mobile");
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);
    }

    //Toggle Drawer
    @Test(testName = "Mobile: Toggle Drawer Test", dataProvider = "Toggle Drawer Test Data", groups = {"mobile-regression", "origamiV2"})
    private void toggleDrawerMobileTest(String drawerType, By openDrawerLinkElement, By toggleDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl, "mobile");

        commonUtils.click(openDrawerLinkElement, "mobile");
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        commonUtils.click(toggleDrawerLinkElement, "mobile");
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement, "mobile");
        Assert.assertTrue(isDrawerClosed);

        commonUtils.click(toggleDrawerLinkElement, "mobile");
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        commonUtils.click(closeDrawerLinkElement, "mobile");
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement, "mobile");
        Assert.assertTrue(isDrawerClosed);
    }

    //Close Drawer
    @Test(testName = "Mobile: Close Drawer Test", dataProvider = "Close Drawer Test Data", groups = {"mobile-regression", "origamiV2"})
    private void closeDrawerMobileTest(String drawerType, By openDrawerLinkElement, By closeDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl, "mobile");

        commonUtils.click(openDrawerLinkElement, "mobile");
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement, "mobile");
        Assert.assertTrue(isDrawerOpened);

        commonUtils.click(closeDrawerLinkElement, "mobile");
        isDrawerClosed = commonUtils.isElementPresent(drawerClosedStatusElement, "mobile");
        Assert.assertFalse(isDrawerClosed);
    }

    //Use data-target instead of href
    @Test(testName = "Mobile: Use Data Target Test", groups = {"mobile-regression", "origamiV2"})
    private void useDataTargetForDrawerMobileTest() throws Exception {
        String text = "Using data-target instead of href.";
        commonUtils.getUrl(drawerUrl, "mobile");

        commonUtils.click(drawerPgObj.useDataTargetButton, "mobile");
        isDrawerOpened = commonUtils.isElementPresent(drawerPgObj.rightDrawerOpened, "mobile");
        Assert.assertTrue(isDrawerOpened);
        contentInDrawer = commonUtils.getText(drawerPgObj.rightDrawerOpened, "mobile");
        isContentInDrawer = commonUtils.assertValue(contentInDrawer, text, "Error: Data Target is not working as per the spec");
        Assert.assertTrue(isContentInDrawer);
    }

    //Use API
    @Test(testName = "Mobile: Use API Drawer Test", dataProvider = "Use API Test Data", groups = {"mobile-regression", "origamiV2"})
    private void useAPIDrawerMobileTest(String drawerType, By element) throws Exception {
        String toggleStatusText = "";
        boolean isToggleStatusText = false;

        commonUtils.getUrl(drawerUrl, "mobile");
        //Test1- open the drawer
        commonUtils.click(element, "mobile");
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText, "mobile");
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is opened", drawerType + " open() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);
        commonUtils.click(element, "mobile");
        //Test2- close the drawer
        toggleStatusText = commonUtils.getText(drawerPgObj.toggleStatusText, "mobile");
        isToggleStatusText = commonUtils.assertValue(toggleStatusText, "Drawer is closed", drawerType + " close() API is not working as per the spec");
        Assert.assertTrue(isToggleStatusText);
    }

    //close other drawers
    @Test(testName = "Mobile: Other Drawer Test", dataProvider = "Other Drawer Test Data", groups = {"mobile-regression", "origamiV2"})
    private void otherDrawerMobileTest(String drawerType, By openDrawerLinkElement, By drawerOpenStatusElement, By drawerClosedStatusElement, By otherDrawerLinkElement, By otherDrawerOpenedStatusElement, By otherDrawerClosedStatusElement) throws Exception {
        commonUtils.getUrl(drawerUrl,"mobile");

        //Open Drawer
        commonUtils.clickUsingJS(openDrawerLinkElement,"mobile");
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementPresent(drawerOpenStatusElement,"mobile");
        Assert.assertTrue(isDrawerOpened);

        //Open other Drawer
        commonUtils.clickUsingJS(otherDrawerLinkElement,"mobile");
        Thread.sleep(1000);
        isDrawerOpened = commonUtils.isElementPresent(otherDrawerOpenedStatusElement,"mobile");
        Assert.assertTrue(isDrawerOpened);

        //Verify if the first drawer is closed
        Thread.sleep(1000);
        isDrawerClosed = commonUtils.isElementsVisibleOnPage(drawerClosedStatusElement,"mobile");
        Assert.assertTrue(isDrawerClosed);
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }

    @Parameters({"mobile"})
    @BeforeClass(alwaysRun = true)
    private void beforeClass(String mobile) throws Exception {
        Thread.sleep(3000);
        setMobile = mobile;
        if (setMobile.equals("on")) {
            appium.manage().deleteAllCookies();
        } else {
            driver.manage().deleteAllCookies();
        }
    }
}