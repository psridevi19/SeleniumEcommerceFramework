package ecom.pageObjects;


import ecom.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="//div[@class='cartSection']/h3")
    List<WebElement> cartProducts;

    @FindBy(css=".totalRow button")
    WebElement checkoutBtn;

    public boolean verifyProductsInCart(String productName)
    {
        Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage clickCheckout()
    {
        checkoutBtn.click();
        return new CheckoutPage(driver);
    }
}
