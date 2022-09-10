package lib;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CorePageCase {

    protected WebDriver webDriver;

    public CorePageCase(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebElement waitForElementPresent(By locator, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    protected boolean waitForElementNotPresent(By locator, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    protected List<WebElement> waitForElementsPresent(By locator, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return webDriver.findElements(locator);
    }

    protected void waitForElementClickableAndClick(By locator, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
        element.click();
    }

    protected List<String> getTextList(By locator, String error_message, long timeoutInSeconds) {
        try {
            List<WebElement> list = waitForElementsPresent(locator, error_message, timeoutInSeconds);
            return getTextWebElements(list);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    private List<String> getTextWebElements(List<WebElement> listElements) {
        List<String> text = new ArrayList<>();
        for (WebElement listElement : listElements) {
            text.add(listElement.getText());
        }
        return text;
    }
}
