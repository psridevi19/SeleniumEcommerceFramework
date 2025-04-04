package ecom.pageObjects;


import ecom.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> orderProducts;

    public boolean verifyOrderDisplay(String productName)
    {
        Boolean match = orderProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }

}
