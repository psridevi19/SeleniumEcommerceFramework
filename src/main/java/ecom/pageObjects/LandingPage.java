package ecom.pageObjects;

import ecom.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.FindBy;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver)
        {
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver,this);
        }

    //Page Factory
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginBtn;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo(String url)
    {
        driver.navigate().to(url);
    }

    public ProductsCataloguePage loginApplication(String userName, String password)
    {
      userEmail.sendKeys(userName);
      userPassword.sendKeys(password);
      loginBtn.submit();
      return new ProductsCataloguePage(driver);
    }

    public String getErrorMessage()
    {
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

}
