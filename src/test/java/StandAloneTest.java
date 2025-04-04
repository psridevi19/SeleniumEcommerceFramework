import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import org.testng.Assert;

public class StandAloneTest {

    public static void main(String[] args)
    {

        //https://rahulshettyacademy.com/client
        //username - testuser2025@gmail.com
        //password - Academy@123

        String productName = "ZARA COAT 3";

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://rahulshettyacademy.com/client");


        driver.findElement(By.id("userEmail")).sendKeys("testuser2025@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Academy@123");
        driver.findElement(By.id("login")).submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

        List<WebElement> cards = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod =  cards.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        /*  If streams is not used, this is the replacement with for loop.
        for(WebElement list : cards)
        {
            if(list.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
            {
                list.findElement(By.cssSelector(".card-body button:last-of-type")).click();
             }
        }
        */

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartItem = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        Boolean match = cartItem.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("United");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));

        List<WebElement> autoSelectList = driver.findElements(By.xpath("//button[contains(@class,'ta-item')]"));

        autoSelectList.stream().forEach(a->System.out.println("Countries : "+a.getText()));
        WebElement autoItemToSelect = autoSelectList.stream().filter(a->a.getText().contains("United Kingdom")).findFirst().orElse(null);
        autoItemToSelect.click();

        driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();

        String confirmMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
        System.out.println("Success Message : "+confirmMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        driver.quit();

    }

}

