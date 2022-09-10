package pages;

import lib.CorePageCase;
import lib.DirectionsСonstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PopularDirectionsForm extends CorePageCase {

    private final static String CONTAINER = "//*[@id='rec468113060']";
    private final static String BUTTON_DIRECTION = CONTAINER + "//*[@class ='tn-atom' and contains(@href, '{TEXT}')]/..";
    private final static String NAME = CONTAINER + "//*[text()='{TEXT}']";
    private final static String COUNT_COURSES = NAME + "//preceding::*[contains(text(), 'курс')][1]";

    public PopularDirectionsForm(WebDriver webDriver) {
        super(webDriver);
    }

    public int getCountCourses(String nameDirection) {
        By locator = By.xpath(COUNT_COURSES.replace("{TEXT}", nameDirection));
        WebElement element = waitForElementPresent(locator, "Cannot find count courses " + nameDirection, 5);
        String countStr = element.getText();
        return Integer.parseInt(countStr.substring(0,countStr.indexOf("курс")).trim());
    }

    public void open(String nameDirection) {
        By locator = By.xpath(BUTTON_DIRECTION.replace("{TEXT}", DirectionsСonstant.getHref(nameDirection)));
        List<WebElement> elements =  waitForElementsPresent(locator, "Cannot find button for direction " + nameDirection, 5);
        elements.get(elements.size()-1).click();

    }
}
