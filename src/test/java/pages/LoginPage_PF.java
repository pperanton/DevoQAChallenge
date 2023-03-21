package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_PF {

    WebDriver driver;

    @FindBy(id = "loginEmail")
    WebElement field_username;

    @FindBy(id = "loginPass")
    WebElement field_password;

    @FindBy(id = "btSignIn")
    WebElement btn_signin;

    @FindBy(className = "notipop__content")
    WebElement signErrorPopup;

    public String url_login = "https://eu.devo.com/login";

    public LoginPage_PF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToLoginUrl(){
        driver.navigate().to(url_login);
    }

    public void enterUsername (String username){
        field_username.sendKeys(username);
    }

    public void enterPassword(String password){
        field_password.sendKeys(password);
    }

    public void clickOnLogin(){
        btn_signin.click();
    }

    public void loginErrorPopup(){
        signErrorPopup.isDisplayed();
    }
}
