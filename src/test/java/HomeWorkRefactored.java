import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class HomeWorkRefactored {


    private static final String NAME = "name";
    private static final List<String> LOCATORS = Arrays.asList("first-name", "last-name", "dob", "address", "email", "password", "company", "comment");
    private static final List<String> INPUT_VALUES = Arrays.asList("Adrian", "Kowalski", "02/10/1995", "Cracow 35/17", "test@gmail.com",
            "pass123", "CodersLab", "This is comment");

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/users/adrianlachowski/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        System.out.println("Test passed");
        driver.quit();
    }


    @Test
    public void testFirstName() {
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");

        List<WebElement> webElements = LOCATORS.stream().map(locator -> driver.findElement(By.id(locator))).collect(Collectors.toList());
        List<String> nameAttributes = webElements.stream().map(webElement -> webElement.getAttribute(NAME)).collect(Collectors.toList());
        List<WebElement> elementsGender = driver.findElements(By.cssSelector(".radio-inline"));

        selectGender(elementsGender, "Female");

        fillRestOfTheForm(webElements, nameAttributes, INPUT_VALUES);

        selectValueFromDropDown("role", "Manager");
        selectValueFromDropDown("expectation", "High salary");

        driver.findElement(By.xpath("//label[text()='Read books']")).click();
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

    private void selectGender(List<WebElement> elements, String checkboxName) {
        for (WebElement element : elements) {
            if (element.getText().equals(checkboxName)) {
                element.click();
                break;
            }
        }
    }

    private void fillRestOfTheForm(List<WebElement> webElements, List<String> nameAttributes, List<String> inputValues) {
        for (int i = 0; i < webElements.size(); i++) {
            workWithWebElement(webElements.get(i), nameAttributes.get(i), inputValues.get(i));
        }
    }

    private void selectValueFromDropDown(String locator, String visibleText) {
        new Select(driver.findElement(By.name(locator))).selectByVisibleText(visibleText);
    }
}

