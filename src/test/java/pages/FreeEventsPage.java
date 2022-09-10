package pages;

import lib.CorePageCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FreeEventsPage extends CorePageCase {

    //Ежедневные онлайн-мероприятия. Daily online events
    private final static String DAILY_CONTAINER = "//*[@id='rec440229625']";
    //Бесплатные мероприятия и микрокурсы.  Free events and micro-courses
    private final static String FREE_CONTAINER = "//*[@id='rec455450952']";
    //Полезные материалы. Useful materials
    private final static String USEFUL_CONTAINER = "//*[@id='rec455276253']";

    private final static By NAMES_DAILY = By.xpath(DAILY_CONTAINER + "//*[@style='line-height: 31px;']");
    private final static By LINKS_DAILY = By.xpath(DAILY_CONTAINER + "//a");
    private final static String NAMES_FREE_AND_USEFUL = "//*[@class='t774__title t-name t-name_xs']";
    private final static String LINKS_FREE_AND_USEFUL = NAMES_FREE_AND_USEFUL + "/../..";
    private final static String DESCR_FREE_AND_USEFUL = "//*[@class='t774__descr t-descr t-descr_xxs']";

    public FreeEventsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public int getCountDailyOnline() {
        return waitForElementsPresent(LINKS_DAILY, "Couldn't get links 'Daily online events", 10).size();
    }

    public int getCountFreeEventsAndMicrocourses() {
        By locator = By.xpath(FREE_CONTAINER + NAMES_FREE_AND_USEFUL);
        return waitForElementsPresent(locator, "Couldn't get list names 'Free events and micro-courses'", 10).size();
    }

    public int getCountUsefulMaterials() {
        By locator = By.xpath(USEFUL_CONTAINER + NAMES_FREE_AND_USEFUL);
        return waitForElementsPresent(locator, "Couldn't get list names 'Useful materials'", 10).size();
    }

    public String getNameDailyOnline(int number) {
        return getTextList(NAMES_DAILY,
                "Couldn't get name 'Daily online events' № " + number, 5).get(number - 1);
    }

    public String getNameFreeEventsAndMicrocourses(int number) {
        By locator = By.xpath(FREE_CONTAINER + NAMES_FREE_AND_USEFUL);
        return getTextList(locator,
                "Couldn't get name 'Free events and micro-courses' № " + number, 5).get(number - 1);
    }

    public String getNameUsefulMaterials(int number) {
        By locator = By.xpath(USEFUL_CONTAINER + NAMES_FREE_AND_USEFUL);
        return getTextList(locator,
                "Couldn't get name 'Useful materials' № " + number, 5).get(number - 1);
    }

    public void clickDailyOnline(int number) {
        waitForElementsPresent(LINKS_DAILY, "Couldn't get link 'Daily online events' № " + number, 5).get(number - 1).click();
    }

    public void clickFreeEventsAndMicrocourses(int number) {
        By locator = By.xpath(FREE_CONTAINER + LINKS_FREE_AND_USEFUL);
        waitForElementsPresent(locator, "Couldn't get link 'Free events and micro-courses' № " + number, 5).get(number - 1).click();
    }

    public void clickUsefulMaterials(int number) {
        By locator = By.xpath(USEFUL_CONTAINER + LINKS_FREE_AND_USEFUL);
        waitForElementsPresent(locator, "Couldn't get link 'Useful materials' № " + number, 5).get(number - 1).click();
    }

    public Boolean isTestUsefulMaterials(int number) {
        By locator = By.xpath(USEFUL_CONTAINER + DESCR_FREE_AND_USEFUL);
        String description = getTextList(locator,
                "Couldn't find description 'Useful materials' № " + number, 5).get(number - 1);
        return description.contains("Пройдите тест");
    }

}
