package tests;

import lib.CoreTestCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.DirectionPage;
import pages.MainMenuPage;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class MainMenuTest extends CoreTestCase {
    private static MainMenuPage menu;

    @BeforeAll
    public static void initPage() {
        System.out.println("Тестирование главного меню.");
        webDriver.get("https://skillfactory.ru/");
        menu = new MainMenuPage(webDriver);
    }

    @AfterEach
    public void afterEach() {
        webDriver.get("https://skillfactory.ru/");
        System.out.println();
    }

    @DisplayName("Тест 1. Открытие подпунктов пункта меню 'Курсы'")
    @ParameterizedTest
    @CsvSource(value = {
            "ВСЕ КУРСЫ, 70",
            "Data Science, 9",
            "Аналитика данных, 10",
            "Программирование, 28",
            "Веб-разработка, 9",
            "Backend-разработка, 7",
            "Тестирование, 3",
            "Разработка приложений, 3",
            "Кибербезопасность, 1",
            "Инфраструктура, 2",
            "Разработка игр, 3",
            "Маркетинг, 2",
            "Дизайн, 21",
            "Менеджмент, 5",
            "Высшее образование, 5",
            "Создание сайтов, 13"
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void openCourseByDirectionTest(String nameDirection, int countCoursesExpected) {
        System.out.println("Открытие подпункта меню 'Курсы': " + nameDirection);
        menu.openCourses();
        menu.selectCourseByDirection(nameDirection);
        System.out.println("Наименование открывшейся страницы: " + webDriver.getTitle());

        //Проверка количества курсов
        DirectionPage direction = new DirectionPage(webDriver, nameDirection);
        int countCoursesActual = direction.getCountCourses();
        System.out.println("Количество курсов = " + countCoursesActual);
        Assertions.assertEquals(countCoursesExpected, countCoursesActual);
    }

    @DisplayName("Тест 2. Открытие пункта меню 'Бесплатно'.")
    @Test
    public void openFreeTest() {
        System.out.println("Открытие пункта меню 'Бесплатно'.");
        menu.openFree();
        Assertions.assertTrue(webDriver.getCurrentUrl().contains("events"));
        String titleOpenPage = webDriver.getTitle();
        System.out.println("Наименование открывшейся страницы: " + titleOpenPage);
        Assertions.assertTrue(titleOpenPage.contains("Бесплатн"));
    }
}
