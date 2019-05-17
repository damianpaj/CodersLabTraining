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

public class HomeWork {

    private static final String NAME = "name";
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
    public void testFirstName() throws FileNotFoundException {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
        int counter = 1;
        // Read file
        File file = new File("firstName.txt");
        Scanner read = new Scanner(file);

        // Find elements
        WebElement firstName = driver.findElement(By.id("first-name"));
        String firstNameAttribute = firstName.getAttribute(NAME);
        WebElement lastName = driver.findElement(By.id("last-name"));
        String lastNameAttribute = lastName.getAttribute(NAME);
        List<WebElement> elementsGender = driver.findElements(By.cssSelector(".radio-inline"));
        WebElement dateOfBirth = driver.findElement(By.id("dob"));
        String dobAttribute = dateOfBirth.getAttribute(NAME);
        WebElement address = driver.findElement(By.id("address"));
        String addressAttribute = address.getAttribute(NAME);
        WebElement email = driver.findElement(By.id("email"));
        String emailAttribute = email.getAttribute(NAME);
        WebElement password = driver.findElement(By.id("password"));
        String passwordAttribute = password.getAttribute(NAME);
        WebElement company = driver.findElement(By.id("company"));
        String companyAttribute = company.getAttribute(NAME);
        Select roleDropDown = new Select(driver.findElement(By.name("role")));
        Select jobDropDown = new Select(driver.findElement(By.name("expectation")));
        WebElement comment = driver.findElement(By.id("comment"));
        String commentAttribute = comment.getAttribute(NAME);

        // Start fill Form
        String lastNameInput = "Kowalski";
        workWithWebElement(lastName, lastNameAttribute, lastNameInput);
        for (WebElement element : elementsGender) {
            if (element.getText().equals("Female")) {
                element.click();
                break;
            }
        }
        String dateOfBirthInput ="02/10/1995";
        workWithWebElement(dateOfBirth , dobAttribute, dateOfBirthInput);
        driver.findElement(By.id("address")).click();
        String addressInput = "Cracow 35/17";
        workWithWebElement(address, addressAttribute, addressInput);
        String emailInput = "test@gmail.com";
        workWithWebElement(email, emailAttribute, emailInput);
        String passwordInput = "pass123";
        workWithWebElement(password, passwordAttribute, passwordInput);
        String companyInput = "CodersLab";
        workWithWebElement(company, companyAttribute, companyInput);
        roleDropDown.selectByVisibleText("Manager");
        jobDropDown.selectByVisibleText("High salary");
        driver.findElement(By.xpath("//label[text()='Read books']")).click();
        String commentInput = "This is comment";
        workWithWebElement(comment, commentAttribute, commentInput);
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

    private void workWithWebElement(WebElement webElement, String name, String inputValue) {
        if (webElement.isEnabled()) {
            webElement.sendKeys(inputValue);
            System.out.println(name + ": " + inputValue);
        }
    }
}





