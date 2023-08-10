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

public class Login extends env_target {
    @Given("User is on login page")
    public void userIsOnLoginPage() {
        //Set driver location path
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        //Maximize driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Set URL
        driver.get(sauceDemoUrl);
        //Set duration
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit'][@data-test='login-button']"))
        );
    }

    @When("User fill username and password")
    public void userFillUsernameAndPassword() {
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
    }

    @And("user click login button")
    public void userClickLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }

    @Then("User verify login result")
    public void userVerifyLoginResult() {
        //Set duration
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Products')]"))
        );
        //Set Quit
        driver.quit();
    }

    @When("User fill invalid username and password")
    public void userFillInvalidUsernameAndPassword() {
        driver.findElement(By.name("user-name")).sendKeys("wrong_username");
        driver.findElement(By.name("password")).sendKeys("wrong_password");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        //Set duration
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
        );
        //Set Quit
        driver.quit();
    }

    @When("^User input (.*) and (.*)$")
    public void userFillInvalidUsernameAndPassword(String username, String password) {
        driver.findElement(By.name("user-name")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @Then("^User get verify login (.*)$")
    public void userGetVerifyLoginResultTdd(String result) {
        //Set duration
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver, duration);
        if (result == "Passed") {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='title'][contains(text(),'Products')]"))
            );
        } else if (result == "Failed") {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("error-button"))
            );
        }
        //Set Quit
        driver.quit();
    }
}
