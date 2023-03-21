package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage_PF;
import pages.LoginPage_PF;

import java.time.Duration;


public class TimezoneSteps {

    WebDriver driver = null;
    HomePage_PF home;
    LoginPage_PF login;

    String username = "pperanton@gmail.com";
    String password = "devocodechallenge";


    @Before(value = "@timezone", order = 0)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver_win.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        driver.manage().window().maximize();
    }

    @After(value = "@timezone", order = 9)
    public void teardown() {
        driver.close();
        driver.quit();
    }

    @Given("user goes to login page")
    public void user_goes_to_login_page() {
        login = new LoginPage_PF(driver);
        login.goToLoginUrl();

    }

    @Given("user login using valid credentials and click login button")
    public void user_login_using_valid_credentials_and_click_login_button() {
        login.enterUsername(username);
        login.enterPassword(password);
        login.clickOnLogin();
    }

    @When("user is on home page")
    public void user_is_on_home_page() {
        home = new HomePage_PF(driver);
        home.welcomePopupDismiss();
    }

    @When("user clicks on Timezone option")
    public void user_clicks_on_timezone_option() {
        home.clickOnLeftBannerAndTimezone();
    }

    @When("^user selects a new (.*) for current session only$")
    public void user_selects_a_new_timezone_for_current_session_only(String Timezone) {
        home.entersTimezoneInformation(Timezone);
    }

    @Then("^(.*) selection must be reflected on user left banner$")
    public void timezone_selection_must_be_reflected_on_user_left_banner(String Timezone) {
        home.welcomePopupDismiss();
        home.clickOnLeftBannerAndTimezone();
        home.verifyTimezoneInformation(Timezone);

    }


}
