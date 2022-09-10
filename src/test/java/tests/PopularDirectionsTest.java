package tests;

import lib.CoreTestCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.DirectionPage;
import pages.PopularDirectionsForm;

import java.util.ArrayList;

public class PopularDirectionsTest extends CoreTestCase {
    private static PopularDirectionsForm popularDirection;

    @BeforeAll
    public static void initPage() {
        System.out.println("Тестирование панели 'Выберите направление в IT' на главной странице.");
        webDriver.get("https://skillfactory.ru/");
        popularDirection = new PopularDirectionsForm(webDriver);
    }

    @AfterEach
    public void afterEach() {
        webDriver.get("https://skillfactory.ru/");
        System.out.println();
    }

    @DisplayName("Тест 1. Сравнение заявленного количества курсов в направлении с открывшемся количеством курсов.")
    @ParameterizedTest
    @ValueSource(strings = {"Программирование", "Data Science", "Веб-разработка", "Аналитика",
            "Тестирование", "Разработка игр", "Высшее образование"})
    public void compareCountCoursesTest(String nameDirection) {
        System.out.println("Тест. Сравнение заявленного количества курсов в направлении с открывшемся количеством курсов.");
        System.out.println("Наименование направления: " + nameDirection);
        int countOnСard = popularDirection.getCountCourses(nameDirection);
        popularDirection.open(nameDirection);
        DirectionPage direction = new DirectionPage(webDriver, nameDirection);
        ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
        if (tabs.size() > 1) {
            webDriver.switchTo().window(tabs.get(1));
        }
        int count = direction.getCountCourses();
        System.out.println("Количество заявленных курсов = " + countOnСard + ". Количество курсов после открытия направления = " + count);
        Assertions.assertEquals(countOnСard, count);
    }
}
