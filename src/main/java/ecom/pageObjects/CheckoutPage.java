package ecom.pageObjects;

import ecom.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver,this);
    }

    @FindBy (xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(xpath = "//button[contains(@class,'ta-item')]")
    List<WebElement> countryList;

    @FindBy(xpath="//a[contains(@class,'action__submit')]")
    WebElement placeOrderBtn;

    By selectSection = By.xpath("//section[contains(@class,'ta-results')]");
    By selectCountryBy = By.xpath("//input[@placeholder='Select Country']");
    public void selectCountry(String countrySearchKey,String countryName)
    {
        waitForElementToAppear(selectCountryBy);
        selectCountry.sendKeys("United");
        waitForElementToAppear(selectSection);

        WebElement countrySelect = countryList.stream().filter(a->a.getText().contains(countryName)).findFirst().orElse(null);
        countrySelect.click();

    }
    public OrderConfirmationPage placeOrder()
    {
        placeOrderBtn.click();
        return new OrderConfirmationPage(driver);
    }
}
