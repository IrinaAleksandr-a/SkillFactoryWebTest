package tests;

import lib.CoreTestCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.FreeCoursePage;
import pages.FreeEventsPage;

import java.util.ArrayList;
import java.util.stream.IntStream;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class FreeEventsTest extends CoreTestCase {
    private static FreeEventsPage freeEvents;
    private static FreeCoursePage freeCourse;
    private static String titlePage;

    @BeforeAll
    public static void initPage() {
        System.out.println("Тестирование страницы 'Бесплатные мероприятия и программы'.");
        webDriver.get("https://skillfactory.ru/events");
        freeEvents = new FreeEventsPage(webDriver);
        freeCourse = new FreeCoursePage(webDriver);
        titlePage = webDriver.getTitle();
    }

    @AfterEach
    public void afterEach() {
        ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
        if (tabs.size() > 1) {
            webDriver.close();
            webDriver.switchTo().window(tabs.get(0));
        }
        if (freeCourse.isTestOpen()) {
            freeCourse.closeTest();
        }
        System.out.println();
    }

    static IntStream openDailyOnlineEventsTest() {
        return IntStream.range(1, freeEvents.getCountDailyOnline() + 1);
    }

    @DisplayName("Тест 1. Ежедневные онлайн-мероприятия.")
    @ParameterizedTest(name = "Мероприятие № {0}")
    @MethodSource
    public void openDailyOnlineEventsTest(int number) {
        System.out.println("Ежедневное онлайн-мероприятие № " + number);
        String name = freeEvents.getNameDailyOnline(number);
        freeEvents.clickDailyOnline(number);
        System.out.println("Наименование онлайн-мероприятия  : " + name);

        ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
        if (tabs.size() > 1) {
            webDriver.switchTo().window(tabs.get(1));
        }
        String titleEvent = webDriver.getTitle();
        System.out.println("Наименование открывшейся страницы: " + titleEvent);
        Assertions.assertNotEquals(titleEvent, titlePage);
        Boolean isButtonJoin = freeCourse.isButtonJoinPresent();
        System.out.println("Кнопка 'Участвовать' или 'Записаться', или 'Начать' присутствует: " + isButtonJoin);
        Assertions.assertTrue(isButtonJoin);
    }

    static IntStream openFreeEventsAndMicrocoursesTest() {
        return IntStream.range(1, freeEvents.getCountFreeEventsAndMicrocourses() + 1);
    }

    @DisplayName("Тест 2. Бесплатные мероприятия и микро-курсы.")
    @ParameterizedTest(name = "Мероприятие № {0}")
    @MethodSource
    public void openFreeEventsAndMicrocoursesTest(int number) {
        System.out.println("Бесплатное мероприятие или микро-курс № " + number);
        String name = freeEvents.getNameFreeEventsAndMicrocourses(number);
        freeEvents.clickFreeEventsAndMicrocourses(number);
        System.out.println("Наименование онлайн-мероприятия  : " + name);
        ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
        if (tabs.size() > 1) {
            webDriver.switchTo().window(tabs.get(1));
        }
        String titleEvent = webDriver.getTitle();
        System.out.println("Наименование открывшейся страницы: " + titleEvent);
        Assertions.assertNotEquals(titleEvent, titlePage);
    }

    static IntStream openUsefulMaterialsTest() {
        return IntStream.range(1, freeEvents.getCountUsefulMaterials() + 1);
    }

    @DisplayName("Тест 3. Полезные материалы.")
    @ParameterizedTest(name = "Мероприятие № {0}")
    @MethodSource
    public void openUsefulMaterialsTest(int number) {
        System.out.println("Полезные материалы № " + number);
        String name = freeEvents.getNameUsefulMaterials(number);
        System.out.println("Наименование онлайн-мероприятия  : " + name);
        freeEvents.clickUsefulMaterials(number);
        if (freeEvents.isTestUsefulMaterials(number)) {
            Boolean isTestOpen = freeCourse.isTestOpen();
            System.out.println("Открылось окно с вопросами: " + isTestOpen);
            Assertions.assertTrue(isTestOpen);
        } else {
            ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
            if (tabs.size() > 1) {
                webDriver.switchTo().window(tabs.get(1));
            }
            String titleEvent = webDriver.getTitle();
            System.out.println("Наименование открывшейся страницы: " + titleEvent);
            Assertions.assertNotEquals(titleEvent, titlePage);
        }
    }
}
