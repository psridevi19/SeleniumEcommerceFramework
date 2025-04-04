package ecom.pageObjects;

import ecom.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProductsCataloguePage extends AbstractComponent{

    WebDriver driver;

    public ProductsCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement spinner;

    By toastMessageBy = By.cssSelector("#toast-container");
    By productsBy = By.cssSelector(".mb-3");
    By addToCart =  By.cssSelector(".card-body button:last-of-type");

    public List<WebElement> getProductsList()
    {
        waitForElementToAppear(productsBy);
        waitForElementToDisappear(driver.findElement(toastMessageBy));
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod =  getProductsList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName)
    {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessageBy);
        //waitForElementToDisappear(spinner);  Application has hidden spinner too. test is waiting for that too to disappear.
        // So testing is slow. To workaround with this, use Thread.sleep();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
