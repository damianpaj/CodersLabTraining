import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormTest
{

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/damianpajonk/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @After

    public void tearDown() {
       // driver.quit();
    }

    @Test
    public void fillForm() {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");

        WebElement firstName = driver.findElement(By.id("first-name"));
         if(firstName.isDisplayed()){
             firstName.sendKeys("Karol");
        }else
        {
            System.out.println("Nie udało sie znaleźć elementu");
        }

        WebElement lastName = driver.findElement(By.id("last-name"));
        String name = lastName.getAttribute("name");
        String inputValue = "Kowalski";
        workWithWebElement(lastName, name, inputValue);

        List<WebElement> elements = driver.findElements(By.cssSelector(".radio-inline"));
        for (WebElement element : elements) {
            if (element.getText().equals("Female")) {
                element.click();
                break;
            }

        }
        driver.findElement(By.id("dob")).sendKeys("02/10/1995");
        WebElement address = driver.findElement(By.id("address"));
        String addressName = address.getAttribute("name");
        String addressInputValue = "Cracow 35/17";
        workWithWebElement(address, addressName, addressInputValue);

        driver.findElement(By.id("email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("password")).sendKeys("hasl0123");
        driver.findElement(By.id("company")).sendKeys("CodersLab");
        Select roleDropDown = new Select(driver.findElement(By.name("role")));
        roleDropDown.selectByVisibleText("Manager");
        Select jobDropDown = new Select(driver.findElement(By.name("expectation")));
        jobDropDown.selectByVisibleText("High salary");
        driver.findElement(By.xpath("//label[text()='Read books']")).click();
        driver.findElement(By.id("comment")).sendKeys("This is comment");
        driver.findElement(By.id("submit")).click();
        assertEquals("Successfully submitted!", driver.findElement(By.id("submit-msg")).getText());
    }
    @Test
    public void checkErrors() {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        driver.findElement(By.id("submit")).click();
         List<String> listOfIds = getListOfIds();

        int counter = 0;
        for (String elementLocator : listOfIds) {
            elementLocator = elementLocator + "-error";
            assertEquals("This field is required.", driver.findElement(By.id(elementLocator)).getText());
            counter++;
        }
        System.out.println(counter);
    }
    private List<String> getListOfIds() {
        List<String> listId = new ArrayList<>();
        listId.add("first-name");
        listId.add("last-name");
        listId.add("gender");
        return listId;
    }
    private void workWithWebElement(WebElement webElement, String name, String inputValue){
        if(webElement.isDisplayed()){
            webElement.sendKeys(inputValue);
            System.out.println(name + ": " +inputValue);
        }

    }

}

// kontrakt equals hashcode

//$$('.radio-inline')
// $x ('//label[text()="Read books"]')
// List<String> listOfIds = Arrays.asList("first-name", "last-name", "gender");
// import org.openqa.selenium.TakesScreenshot;

        //     elements.get().click();
         //    System.out.println(elements.get(0).getText());
         //  System.out.println(elements.get(0).getText());
         //System.out.println(elements.get(0).getText());
/*ctrl alt m do metody
         v do zmiennej
         c do stalej
         n do inline */
/*for (int i = 0; i < elements.size(); i++) {
    if (elements.get(i).getText().equals("Female")) {
        elements.get(i).click();
        break;

driver.findElement(By.id("submit")).click();
        String textForm = driver.findElement(By.id("submit-msg")).getText();
        assertEquals("Successfully submitted!", textForm);
//$$('.radio-inline')
// $x ('//label[text()="Read books"]')

    //     elements.get().click();
    //    System.out.println(elements.get(0).getText());
    //  System.out.println(elements.get(0).getText());
    //System.out.println(elements.get(0).getText());
/*ctrl alt m do metody
         v do zmiennej
         c do stalej
         n do inline


 /*for (int i = 0; i < elements.size(); i++) {
                    if (elements.get(i).getText().equals("Female")) {
                        elements.get(i).click();
                        break;



while (testFirstNames.equals("This field is required.")) {
            driver.findElement(By.id("submit")).click();
            String testFirstNam = driver.findElement(By.id("first-name-error")).getText();
            testFirstNam.equals("This field is required.");
            if (testFirstNam.equals("This field is required.")) {
                System.out.println("Wczytywanie danych z pliku");
                String firstNameUpdate = read.nextLine();
                firstName.sendKeys(firstNameUpdate);
                driver.findElement(By.id("submit")).click();

            } else {

                driver.findElement(By.id("submit")).click();
                String textForm = driver.findElement(By.id("submit-msg")).getText();
                assertEquals("Successfully submitted!", textForm);
                System.out.println("Test wykonano poprawnie");
                driver.quit();
                break;
            }
        }


          */


