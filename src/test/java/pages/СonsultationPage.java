package pages;

import lib.ConsultationMessages;
import lib.CorePageCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class СonsultationPage extends CorePageCase {

    private final static String xpathForm = "//*[@id='form456746058']";
    private final static By NAME_FIELD = By.xpath(xpathForm + "//input[@name='name']");
    private final static By EMAIL_FIELD = By.xpath(xpathForm + "//input[@name='email']");
    private final static By PHONE_FIELD = By.xpath(xpathForm + "//input[@name='tildaspec-phone-part[]']");
    private static final By PHONE_MASK_SELECT = By.xpath(xpathForm + "//div[@class='t-input-phonemask__select']");
    private static final String PHONE_MASK_COUNTRY = xpathForm + "//*[contains(@data-phonemask-name,'countryName')]";
    private static final By PHONE_CODE = By.xpath(xpathForm + "//*[@class='t-input-phonemask__select-code']");
    private static final By AGREED_CHECKBOX = By.xpath(xpathForm + "//*[@class='t-checkbox__indicator']");
    private static final By POSITION_OF_USER_PERSONAL_DATA = By.xpath(xpathForm + "//a[text()='обработку персональных данных']");
    private static final By BUTTON_SUBMIT = By.xpath(xpathForm + "//div[5]/button[@class='t-submit']");
    private static final By NAME_ERROR_MESSAGE = By.xpath(xpathForm + "//input[@name='name']/following-sibling::div");
    private static final By EMAIL_ERROR_MESSAGE = By.xpath(xpathForm + "//input[@name='email']/following-sibling::div");
    private static final By PHONE_ERROR_MESSAGE = By.xpath(xpathForm + "//*[@class='t-input t-input-phonemask__wrap']/following-sibling::div");
    private static final By ERROR_MESSAGE_LIST = By.xpath("//*[@class='t-form__errorbox-item']");
    private static final By SUCCESS_MESSAGE = By.xpath("//*[@id='tildaformsuccesspopuptext']");
    private static final By SUCCESS_MESSAGE_CLOSE = By.xpath("//*[@class='t-form-success-popup__close-icon']");

    public СonsultationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void setName(String name) {
        if (name != null) {
            waitForElementPresent(NAME_FIELD, "Cannot find input 'Name'", 5).sendKeys(name);
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            waitForElementPresent(EMAIL_FIELD, "Cannot find input 'Email'", 5).sendKeys(email);
        }
    }

    public void setPhone(String phone) {
        if (phone != null) {
            waitForElementPresent(PHONE_FIELD, "Cannot find input 'Phone'", 5).sendKeys(phone);
        }
    }

    public void setPhoneCountry(String countryName) {
        waitForElementClickableAndClick(PHONE_MASK_SELECT, "Cannot find 'Phone mask select'", 5);
        By locator = By.xpath(PHONE_MASK_COUNTRY.replace("countryName", countryName));
        waitForElementClickableAndClick(locator, "Cannot find 'Phone mask' for country" + countryName, 5);
    }

    public String getPhoneCode() {
        return waitForElementPresent(PHONE_CODE, "Cannot find 'Phone code'", 5).getText();
    }

    public void clickAgreed() {
        waitForElementClickableAndClick(AGREED_CHECKBOX, "Cannot find checkbox 'Agreed'", 5);
    }

    public void clickPositionOfUserPersonalData() {
        waitForElementClickableAndClick(POSITION_OF_USER_PERSONAL_DATA, "Cannot find link 'обработку персональных данных'", 5);
    }

    public ConsultationMessages clickButtonSubmit() {
        waitForElementClickableAndClick(BUTTON_SUBMIT, "Cannot find button 'Submit'", 5);
        return getMessages();
    }

    private ConsultationMessages getMessages() {
        String success = null;
        try {
            WebElement messageElement = webDriver.findElement(SUCCESS_MESSAGE);
            success = messageElement.getText();
        } catch (Exception ignore) {
        }
        String nameError = getErrorMessage(NAME_ERROR_MESSAGE);
        String emailError = getErrorMessage(EMAIL_ERROR_MESSAGE);
        String phoneError = getErrorMessage(PHONE_ERROR_MESSAGE);
        String agreeError = null;
        String[] errorList = getErrorMessageList();

        return new ConsultationMessages(success, nameError, emailError, phoneError, agreeError, errorList);
    }

    private String getErrorMessage(By locator) {
        WebElement errorElement = webDriver.findElement(locator);
        if (errorElement.isDisplayed()) {
            return errorElement.getText();
        } else return null;
    }

    private String[] getErrorMessageList() {
        List<WebElement> errorElements = webDriver.findElements(ERROR_MESSAGE_LIST);
        if (!errorElements.isEmpty()) {
            String[] errorMessages = new String[errorElements.size()];
            for (int i = 0; i < errorElements.size(); i++) {
                errorMessages[i] = errorElements.get(i).getText();
            }
            return errorMessages;
        } else return null;
    }

    public void waitMessagesInvisibility() {
        try {
            webDriver.findElement(SUCCESS_MESSAGE_CLOSE).click();
        } catch (Exception ignore) {
        }
        waitForElementNotPresent(ERROR_MESSAGE_LIST, "Ждем исчезновения сообщений", 15);
    }

    public void clearAllField() {
        waitForElementPresent(NAME_FIELD, "Cannot find input 'Name'", 5).clear();
        waitForElementPresent(EMAIL_FIELD, "Cannot find input 'Email'", 5).clear();
        waitForElementPresent(PHONE_FIELD, "Cannot find input 'Phone'", 5).clear();
    }
}
