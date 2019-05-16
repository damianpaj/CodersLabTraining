import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class HomeWork
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
        System.out.println("Test passed");
        driver.quit();
    }


    @Test
    public void testFirstName()  throws FileNotFoundException
    {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        int counter=1;
        // Read file
        File file = new File("firstName.txt");
        Scanner read = new Scanner(file);

        // Find elements
        WebElement firstName = driver.findElement(By.id("first-name"));
        WebElement lastName = driver.findElement(By.id("last-name"));
        List<WebElement> elementsGender = driver.findElements(By.cssSelector(".radio-inline"));
        WebElement dateOfBirth = driver.findElement(By.id("dob"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement company = driver.findElement(By.id("company"));
        Select roleDropDown = new Select(driver.findElement(By.name("role")));
        Select jobDropDown = new Select(driver.findElement(By.name("expectation")));
        WebElement comment = driver.findElement(By.id("comment"));

        // Start fill Form
        lastName.sendKeys("Kowalski");
        for (WebElement element : elementsGender)
        {
            if (element.getText().equals("Female"))
            {
                element.click();
                break;
            }
        }
        dateOfBirth.sendKeys("02/10/1995");
        driver.findElement(By.id("address")).click();
        address.sendKeys("Cracow 35/17");
        email.sendKeys("test@gmail.com");
        password.sendKeys("hasl0123");
        company.sendKeys("CodersLab");
        roleDropDown.selectByVisibleText("Manager");
        jobDropDown.selectByVisibleText("High salary");
        driver.findElement(By.xpath("//label[text()='Read books']")).click();
        comment.sendKeys("This is comment");
        firstName.sendKeys("");

        //submit
        driver.findElement(By.id("submit")).click();
        String textForm = driver.findElement(By.id("submit-msg")).getText();

        // Test off firstName until pass
        while (!textForm.equals("Successfully submitted!")) {
            String testFirstName = driver.findElement(By.id("first-name-error")).getText();
            if (testFirstName.equals("This field is required.")) {
                System.out.println("Test failed " + counter + " times");
                System.out.println("Read data from file");
                String firstNameUpdate = read.nextLine();
                System.out.println("Test name: " + firstNameUpdate);
                firstName.sendKeys(firstNameUpdate);
                driver.findElement(By.id("submit")).click();
                counter++;

            } else {
                break;
            }
        }

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
        listId.add("dob");
        listId.add("address");
        listId.add("email");
        listId.add("password");
        listId.add("company");
        return listId;
    }
}





