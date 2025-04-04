package ecom.pageObjects;

import ecom.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderConfirmationPage extends AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//h1[@class='hero-primary']")
    WebElement confirmationMessage;

    public String verifyConfirmationMessage()
    {
        return confirmationMessage.getText();

    }

}
