package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "firstName")
    private WebElement firstNameTxtbx;

    @FindBy(name = "lastName")
    private WebElement lastNameTxtbx;

    @FindBy(name = "email")
    private WebElement usernameTxt;

    @FindBy(name = "password")
    private WebElement passwordTxtBx;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordTxtBx;

    @FindBy(name = "register")
    private WebElement submitBtn;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        this.driver.get("http://www.newtours.demoaut.com/mercuryregister.php");
        this.wait.until(ExpectedConditions.visibilityOf(firstNameTxtbx));
    }

    public void enterUserDetails(String firstname, String lastname) {
        this.firstNameTxtbx.sendKeys(firstname);
        this.lastNameTxtbx.sendKeys(lastname);
    }

    public void enterUserCredentials(String username, String password) {
        this.usernameTxt.sendKeys(username);
        this.passwordTxtBx.sendKeys(password);
        this.confirmPasswordTxtBx.sendKeys(password);
    }

    public void submit() {
        this.submitBtn.click();
    }


}
