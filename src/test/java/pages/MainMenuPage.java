package pages;

import lib.CorePageCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class MainMenuPage extends CorePageCase {

    private final static By COURSES = By.xpath("//*[@class='tn-atom t978__tm-link']");
    private final static By COURSES_LIST = By.xpath("//*[@class='t978__link-inner']");
    private final static String COURSES_BY_DIRECTION_STR = "//*[@class='t978__link-inner'and contains(text(),'{TEXT}')]/..";
    private final static By FREE = By.xpath("//a[text()='Бесплатно']");

    public MainMenuPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openCourses() {
        waitForElementClickableAndClick(COURSES, "Cannot find button menu 'Курсы'", 10);
    }

    public void selectCourseByDirection(String nameDirection) {
        By xpathCourses = By.xpath(COURSES_BY_DIRECTION_STR.replace("{TEXT}", editName(nameDirection)));
        waitForElementClickableAndClick(xpathCourses, "Cannot find link menu 'Курсы' - '" + nameDirection + "'", 5);
    }

    public void openFree() {
        waitForElementClickableAndClick(FREE, "Cannot find button menu 'Бесплатно'", 10);
    }

    private String editName(String nameDirection) {
        if (nameDirection.equals("Безопасность") || nameDirection.equals("Кибербезопасность")) {
            List<String> titleCourses = getTitleCourses();
            if (titleCourses.contains("Безопасность")) {
                return "Безопасность";
            } else return "Кибербезопасность";
        }
        return nameDirection;
    }

    public List<String> getTitleCourses() {
        return getTextList(COURSES_LIST,
                "Couldn't get a list in menu 'Курсы'", 5);
    }
}
