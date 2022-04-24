package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";
    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        sendTextToElement(By.id("username"),"tomsmith");
        sendTextToElement(By.id("password"),"SuperSecretPassword!");
        clickOnElement(By.xpath("//button[@class='radius']/i"));
        verifyText("Secure Area",By.xpath("//div[@id='content']/div/h2"),"Secure area text validation");

    }
    @Test
    public void verifyTheUsernameErrorMessage() {
        sendTextToElement(By.id("username"),"tomsmith1");
        sendTextToElement(By.id("password"),"SuperSecretPassword!");
        clickOnElement(By.xpath("//button[@class='radius']/i"));
        WebElement text=driver.findElement(By.xpath("//div[@id='flash-messages']/div[1]"));//
        String actualMsg=text.getText();// get text
        String actual1Msg=actualMsg.substring(0,25);// split the text through substring method
        String expectedMsg="Your username is invalid!";//expected message
        Assert.assertEquals("Error message not visible",expectedMsg,actual1Msg);// validation
    }
    @Test
    public void verifyThePasswordErrorMessage(){
        sendTextToElement(By.id("username"),"tomsmith");
        sendTextToElement(By.id("password"),"SuperSecretPassword");
        clickOnElement(By.xpath("//button[@class='radius']/i"));
        WebElement text=driver.findElement(By.xpath("//div[@id='flash-messages']/div[1]"));
        String actualMsg=text.getText();
        String actual1Msg=actualMsg.substring(0,25);// split the text through substring method
        String expectedMsg="Your password is invalid!";
        Assert.assertEquals("Error message not visible",expectedMsg,actual1Msg);
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}
