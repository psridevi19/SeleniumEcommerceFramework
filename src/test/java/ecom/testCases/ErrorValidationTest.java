package ecom.testCases;

import ecom.pageObjects.CartPage;
import ecom.pageObjects.ProductsCataloguePage;
import ecom.testComponents.BaseTest;
import ecom.testComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidationTest extends BaseTest {

    @Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
    public void loginErrorValidation() {

        ProductsCataloguePage productsCataloguePage = landingPage.loginApplication("testuser2025@gmail.com", "A123");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
    }

  

}
