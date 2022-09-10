package pages;

import lib.CorePageCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FreeCoursePage extends CorePageCase {

    private final static By BUTTON_JOIN = By.xpath("//button[contains(text(),'Участв') or contains(text(),'Записываюсь') or contains(text(),'Начать')]");
    private final static By QUIZ_CONTAINER = By.xpath("//*[@class='marquiz__modal']");
    private final static By QUIZ_CONTAINER_CLOSE = By.xpath("//button[@id='marquiz__close']");

    public FreeCoursePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonJoinPresent() {
        try {
            int countButton = waitForElementsPresent(BUTTON_JOIN, "Couldn't find button 'Начать' or 'Участв' or 'Записываюсь'", 5).size();
            return (countButton) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean isTestOpen() {
        WebElement element = waitForElementPresent(QUIZ_CONTAINER, "Couldn't find 'Quiz questions modal'", 5);
        return element.isDisplayed();
    }

    public void closeTest() {
        waitForElementClickableAndClick(QUIZ_CONTAINER_CLOSE, "Couldn't find button 'Close modal quiz questions'", 5);
    }
}
