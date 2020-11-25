import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import org.openqa.selenium.chrome.ChromeOptions;

public class SistersTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverInitiate(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--window-size=1600,900");
        driver = new ChromeDriver(option);
    }

    @Test
    public void addItemsToBasketTest() throws InterruptedException {
        driver.get("https://sisters.by");
        WebElement searchString = driver.findElement(By.xpath("//input[@class=\"b-header-search\"]"));
        searchString.click();
        searchString.sendKeys("ПЛАТЬЕ ZIT 20 35801TR - БЕЖЕВЫЙ");
        searchString.sendKeys(Keys.ENTER);
        new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//img[@src=\"/_thumbs/items-product_item/icon-146540.jpg\"]")));
        WebElement chooseDress = driver.findElement(By.xpath("//img[@src=\"/_thumbs/items-product_item/icon-146540.jpg\"]"));
        chooseDress.click();
        new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@class=\"b-btn basket blue buy\"]")));
        WebElement addToBag = driver.findElement(By.xpath("//a[@class=\"b-btn basket blue buy\"]"));
        addToBag.click();
        driver.get("https://sisters.by/cart");
        List<WebElement> bagItems = driver.findElements(By.xpath("//form[@class=\"b-basket-table\"]"));
        Assert.assertTrue(bagItems.size()>0);
    }
    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
    }
}