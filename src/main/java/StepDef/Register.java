package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import config.env_target;

import java.time.Duration;
import java.util.Random;

public class Register extends env_target {
    @Given("^User is on parabank homepage$")
    public void userIsOnParabankHomepage() {
        //Set driver location path
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        //Maximize driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Set URL
        driver.get(paraBankUrl);
        //Set duration
        Duration duration = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("leftPanel")));
    }

    @When("^User click register link button$")
    public void userClickRegisterLinkButton() {
        driver.findElement(By.xpath("//*[@id=\"loginPanel\"]/p[2]/a")).click();
    }

    @Then("^User is in register page$")
    public void userIsInRegisterPage() {
        Duration duration = Duration.ofSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Signing up is easy!')]")));
    }

    @When("^User input name$")
    public void userInputName() {
        //User input firstname
        driver.findElement(By.id("customer.firstName")).sendKeys("parabank_qa");
        //User input lastname
        driver.findElement(By.id("customer.lastName")).sendKeys("parabank_password");
    }

    @And("^User input address detail$")
    public void userInputAddressDetail() {
        driver.findElement(By.id("customer.address.street")).sendKeys("parabank_street");
        driver.findElement(By.id("customer.address.city")).sendKeys("parabank_city");
        driver.findElement(By.id("customer.address.state")).sendKeys("parabank_state");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("111111");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("111111111111");
        driver.findElement(By.id("customer.ssn")).sendKeys("111111111");
    }

    @And("^User fill valid username and password$")
    public void userFillValidUsernameAndPassword() {
        Random random = new Random();
        int userRand = random.nextInt(10000);
        driver.findElement(By.id("customer.username")).sendKeys("user" + userRand);
        driver.findElement(By.id("customer.password")).sendKeys("parabank_password");
    }

    @And("^User input password confirmation$")
    public void userInputPasswordConfirmation() {
        driver.findElement(By.id("repeatedPassword")).sendKeys("parabank_password");
    }

    @When("^user click register button$")
    public void userClickRegisterButton() {
        driver.findElement(By.xpath("//*[@class='button'][@value='Register']")).click();
    }

    @Then("^User register successfully$")
    public void userRegisterSuccessfully() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Your account was created successfully. You are now logged in.')]")));
        driver.quit();
    }

    @And("^User input invalid password confirmation$")
    public void userInputInvalidPasswordConfirmation() {
        driver.findElement(By.id("repeatedPassword")).sendKeys("wrong_password");
    }

    @Then("^User get error password did not match$")
    public void userGetErrorPasswordDidNotMatch() {
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Passwords did not match.')]")));
        driver.quit();
    }
}
