package ecom.testCases;

import ecom.pageObjects.CartPage;
import ecom.pageObjects.ProductsCataloguePage;
import ecom.testComponents.BaseTest;
import ecom.testComponents.ExtendReportListeners;
import ecom.testComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners({ExtendReportListeners.class})
public class ErrorValidationTest extends BaseTest {

    @Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
    public void loginErrorValidation() {

        ProductsCataloguePage productsCataloguePage = landingPage.loginApplication("testuser2025@gmail.com", "A123");
        Assert.assertEquals(landingPage.getErrorMessage(),"I-Incorrect email or password.");
    }

    @Test
    public void productValidation()
    {
        String productName = "ZARA COAT 3";
        ProductsCataloguePage productsCataloguePage = landingPage.loginApplication("testuser2025@gmail.com","Academy@123");

        List<WebElement> products = productsCataloguePage.getProductsList();
        productsCataloguePage.addProductToCart(productName);
        CartPage cartPage = productsCataloguePage.clickCartHeader();

        boolean match = cartPage.verifyProductsInCart(productName);
        Assert.assertTrue(match,"Products Not Matched");
    }


}
