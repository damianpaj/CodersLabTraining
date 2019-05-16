import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

public class SearchTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/damianpajonk/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @After

    public void tearDown() {
       driver.quit();
        }

    /*
    @Test
    public void searchTest(){
        driver.get("http://www.google.pl");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Testowanie Selenium");
        input.submit();
        String title = driver.getTitle();
        assertEquals("Testowanie Selenium - Szukaj w Google", title);
        System.out.println(title);
    }

    @Test
    public void searchTest(){
        driver.get("http://www.bing.com");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Mistrzostwa Świata w piłce nożnej 2018");
        input.submit();
        String title = driver.getTitle();
        assertEquals("Mistrzostwa Świata w piłce nożnej 2018 - Bing", title);
        System.out.println(title);
    }*/

    @Test
    public void searchTest() throws InterruptedException {
        driver.get("http://www.bing.com");
        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys("Mistrzostwa Świata w piłce nożnej 2018");
        WebElement submit = driver.findElement(By.id("sb_form_go"));
        submit.click();
        WebElement firstLink = driver.findElement(By.partialLinkText("Wikipedia, wolna ..."));
        String link1 = firstLink.getText();
        WebElement secondLink = driver.findElement(By.xpath("//*[@id=\"b_results\"]/li[2]/h2/a"));
        String link2 = secondLink.getText();
       // WebElement allLinks = driver.findElement(By.xpath("//*[@id=\"b_results\"]"));
        assertEquals("Mistrzostwa Świata w Piłce Nożnej 2018 – Wikipedia, wolna ...", link1);
        assertEquals("Mundial 2018 - Mistrzostwa Świata w piłce nożnej - Wyniki", link2);
        System.out.println(link1);
        System.out.println(link2);
    }




}

/* Assertequals. Sprawdzenie, że w pierwszym linku jest to co trzeba. */
