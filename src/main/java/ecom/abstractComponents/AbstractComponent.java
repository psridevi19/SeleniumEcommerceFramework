package ecom.abstractComponents;
import ecom.pageObjects.CartPage;
import ecom.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css="[routerlink*='myorders']")
    WebElement orderHeader;


    public void waitForElementToAppear(By locator)
    {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForWebElementToAppear(WebElement ele)
    {

        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForElementToDisappear(WebElement ele)
    {
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage clickCartHeader()
    {
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrderPage clickOrderHeader()
    {
        orderHeader.click();
        return new OrderPage(driver);
    }
}
