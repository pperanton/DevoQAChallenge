package pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage_PF {

    WebDriver driver;

    @FindBy(id = "lt-main-logo")
    WebElement mainLogo;

    @FindBy(id = "lt-landing")
    WebElement welcomePopup;

    @FindBy(xpath = "//*[@id=\"lt-landing\"]/div/button")
    WebElement closeWelcomePopup;

    @FindBy(className = "account-timeZone")
    WebElement timeIcon;

    @FindBy(className = "selection")
    WebElement timezoneDropdownMenu;

    @FindBy(className = "select2-search__field")
    WebElement timezoneDropdownSearchbar;

    @FindBy(className = "time-zone-picker__session-checkbox")
    WebElement timezonePickerSessionCheckbox;

    @FindBy(className = "time-zone-picker-modal__save-btn")
    WebElement timezonePickerSaveButton;

    @FindBy(xpath = "//*[@id=\"time-zone-picker\"]/div[3]/div[1]/h6")
    WebElement timezoneUserTimezone;

    public HomePage_PF(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkUserIsOnMainPage(){
        mainLogo.isDisplayed();
    }

    public void welcomePopupDismiss(){
        if (welcomePopup.isDisplayed()){
            closeWelcomePopup.click();
        }
        checkUserIsOnMainPage();
    }

    public void clickOnLeftBannerAndTimezone(){
        mainLogo.click();
        timeIcon.click();
    }

    public void entersTimezoneInformation(String Timezone){
        timezoneDropdownMenu.click();
        timezoneDropdownSearchbar.sendKeys(Timezone, Keys.ENTER);
        timezonePickerSessionCheckbox.click();
        timezonePickerSaveButton.click();
    }

    public void verifyTimezoneInformation(String Timezone){
        boolean result = timezoneUserTimezone.getText().contains(Timezone);
        Assert.assertTrue(result);
    }
}
