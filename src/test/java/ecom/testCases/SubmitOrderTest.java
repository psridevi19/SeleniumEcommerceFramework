package ecom.testCases;

import com.aventstack.extentreports.ExtentTest;
import ecom.data.DataReader;
import ecom.pageObjects.*;
import ecom.testComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";


    @Test(dataProvider ="getData",groups={"Purchase"})
    public  void submitOrder(HashMap<String,String> map) throws IOException {

        String countryToSelect = "United Kingdom";
        String countrySearchKey = "united";
        String confirmMessageExpected = "Thankyou for the order.";

        //LandingPage landingPage = launchApplication();
        ProductsCataloguePage productsCataloguePage = landingPage.loginApplication(map.get("email"),map.get("password"));

        List<WebElement> products = productsCataloguePage.getProductsList();
        productsCataloguePage.addProductToCart(map.get("product"));
        CartPage cartPage = productsCataloguePage.clickCartHeader();

        boolean match = cartPage.verifyProductsInCart(map.get("product"));
        Assert.assertTrue(match,"Products Not Matched");

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.selectCountry(countrySearchKey,countryToSelect);
        OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();

        String confirmationMessageActual = orderConfirmationPage.verifyConfirmationMessage();
        Assert.assertTrue(confirmationMessageActual.equalsIgnoreCase(confirmMessageExpected));


    }

    /*
    // getData as normal Strings
    @Test(dataProvider ="getData",groups={"Purchase"})
    public  void submitOrder(String email, String password, String productName) throws IOException {
        String countryToSelect = "United Kingdom";
        String countrySearchKey = "united";
        String confirmMessageExpected = "Thankyou for the order.";

        //LandingPage landingPage = launchApplication();
        ProductsCataloguePage productsCataloguePage = landingPage.loginApplication(email,password);

        List<WebElement> products = productsCataloguePage.getProductsList();
        productsCataloguePage.addProductToCart(productName);
        CartPage cartPage = productsCataloguePage.clickCartHeader();

        boolean match = cartPage.verifyProductsInCart(productName);
        Assert.assertTrue(match,"Products Not Matched");

        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.selectCountry(countrySearchKey,countryToSelect);
        OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();

        String confirmationMessageActual = orderConfirmationPage.verifyConfirmationMessage();
        Assert.assertTrue(confirmationMessageActual.equalsIgnoreCase(confirmMessageExpected));
    }
*/
    @Test(dependsOnMethods ={"submitOrder"})
    public void orderHistoryTest()
    {
        ProductsCataloguePage productsCataloguePage = landingPage.loginApplication("testuser2025@gmail.com","Academy@123");
        OrderPage orderPage = productsCataloguePage.clickOrderHeader();
        boolean match = orderPage.verifyOrderDisplay(productName);
        Assert.assertTrue(match);

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        //getJsonDataToMap() is implemented in BaseTest
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"/src/main/java/ecom/data/PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

    /*
    @DataProvider
    public Object[][] getData()
    {
        return new Object[][] {{"testuser2025@gmail.com","Academy@123","ZARA COAT 3"},{"testusersel@gmail.com","Academy@123","ADIDAS ORIGINAL"}};
    }
    */

   /*
    @DataProvider
    public Object[][] getData() throws IOException {

        HashMap<String,String> map = new HashMap<>();
        map.put("email","testuser2025@gmail.com");
        map.put("password","Academy@123");
        map.put("product","ZARA COAT 3");
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("email","testusersel@gmail.com");
        map1.put("password","Academy@123");
        map1.put("product","ADIDAS ORIGINAL");

        return new Object[][] {map},{map1}};
    }
    */


}
