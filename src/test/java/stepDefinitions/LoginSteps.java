package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage_PF;
import pages.LoginPage_PF;

import java.time.Duration;

public class LoginSteps {

    WebDriver driver = null;
    LoginPage_PF login;
    HomePage_PF home;

    @Before(value = "@login", order = 0)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_win.exe"); //Windows OS default webdriver
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_mac"); //Mac OS webdriver
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_linux"); //Linux OS webdriver
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().window().maximize();
    }

    @After(value = "@login", order = 9)
    public void teardown() {
        driver.close();
        driver.quit();
    }

          /*@Before
    public void browserSetup(String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            System.out.println("Chrome test starting ...");
            System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
            driver.manage().window().maximize();
        }
        else {
            throw new Exception("Browser is not supported");
        }
    }

    public String getChromeDriverPath() {
        String OS = System.getProperty("os.name");

        if (OS.contains("Window")) {
            return "src/test/resources/drivers/chromedriver_win";
        } else if (OS.contains("Mac")) {
            return "src/test/resources/drivers/chromedriver_mac";
        } else {
            return "src/test/resources/drivers/chromedriver_linux";
        }
    }*/

    @Given("user is on login page")
    public void user_is_on_login_page() {
        login = new LoginPage_PF(driver);

        login.goToLoginUrl();
    }

    @And("^user enters valid (.*) and (.*)$")
    public void user_enters_valid_username_and_password(String username, String password) {
        login.enterUsername(username);
        login.enterPassword(password);
    }

    @And("^user enters invalid (.*) and (.*)$")
    public void user_enters_invalid_username_and_password(String username, String password) {
        login.enterUsername(username);
        login.enterPassword(password);
    }

    @When("click on login button")
    public void click_on_login_button() {
        login.clickOnLogin();
    }

    @Then("user navigates to home page")
    public void user_navigates_to_home_page() {
        home = new HomePage_PF(driver);
        home.checkUserIsOnMainPage();
    }

    @Then("error popup is displayed")
    public void error_popup_is_displayed() {
        login.loginErrorPopup();
    }

}
