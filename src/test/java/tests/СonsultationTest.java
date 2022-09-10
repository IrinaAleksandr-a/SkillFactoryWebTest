package tests;

import lib.ConsultationMessages;
import lib.CoreTestCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.СonsultationPage;


import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class СonsultationTest extends CoreTestCase {
    private static СonsultationPage consultForm;
    private static final String messagesSuccess = "Спасибо! Мы позвоним вам в течение дня с 10 до 19 и поможем выбрать направление для обучения";

    @BeforeAll
    public static void initPage() {
        System.out.println("Тестирование панели 'Получите консультацию по выбору курса в IT' на главной странице.");
        webDriver.get("https://skillfactory.ru/");
        consultForm = new СonsultationPage(webDriver);
    }

    @AfterEach
    public void afterEach() {
        ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
        if (tabs.size() > 1) {
            webDriver.close();
            webDriver.switchTo().window(tabs.get(0));
        }
        consultForm.waitMessagesInvisibility();
        consultForm.clearAllField();
    }

    @DisplayName("Тест 1. Заполнение формы для получения консультации валидными данными c cогласием на обработку персональных данных.")
    @Disabled("Sometimes a captcha")
    @ParameterizedTest
    @CsvSource(value = {"Masha, Test1@mail.ru, 9035554433"
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void setValidDataWithAgreedTest(String name, String email, String phone) {
        System.out.println("Тест. Заполнение формы для получения консультации валидными данными c cогласием на обработку персональных данных.");
        consultForm.setName(name);
        consultForm.setEmail(email);
        consultForm.setPhone(phone);
        ConsultationMessages messages = consultForm.clickButtonSubmit();

        System.out.println("Имя: <" + name + ">. Email: <" + email + ">. Телефон: <" + phone + ">");
        System.out.println(messages.toString());
        System.out.println();

        Assertions.assertEquals(messagesSuccess, messages.getSuccess());
        Assertions.assertNull(messages.getNameError());
        Assertions.assertNull(messages.getEmailError());
        Assertions.assertNull(messages.getPhoneError());
        Assertions.assertNull(messages.getAgreeError());
        Assertions.assertNull(messages.getErrorList());
    }

    @DisplayName("Тест 2. Заполнение формы для получения консультации валидными данными без cогласия на обработку персональных данных.")
    @Disabled("Sometimes a captcha")
    @ParameterizedTest
    @CsvSource(value = {"Misha, Test2@mail.ru, 9035554433"
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void setValidDataNotAgreedTest(String name, String email, String phone) {
        System.out.println("Тест. Заполнение формы для получения консультации валидными данными без cогласия на обработку персональных данных.");
        consultForm.setName(name);
        consultForm.setEmail(email);
        consultForm.setPhone(phone);
        consultForm.clickAgreed();
        ConsultationMessages messages = consultForm.clickButtonSubmit();

        System.out.println("Имя: <" + name + ">. Email: <" + email + ">. Телефон: <" + phone + ">");
        System.out.println(messages.toString());
        System.out.println();

        Assertions.assertNull(messages.getSuccess());
        Assertions.assertNull(messages.getNameError());
        Assertions.assertNull(messages.getEmailError());
        Assertions.assertNull(messages.getPhoneError());
        Assertions.assertNotNull(messages.getAgreeError());
        Assertions.assertNotNull(messages.getErrorList());
    }

    @DisplayName("Тест 3. Заполнение формы для получения консультации не валидными данными.")
    @ParameterizedTest
    @CsvSource(value = {
            ",,",
            "  ,  ,  ",
            "1,IvanovaI@mailru,+79034335",
            "2,IvanovaImail.ru, Ivan"
    }, ignoreLeadingAndTrailingWhitespace = false)
    public void setNotValidDataTest(String name, String email, String phone) {
        System.out.println("Тест. Заполнение формы для получения консультации не валидными данными.");
        consultForm.setName(name);
        consultForm.setEmail(email);
        consultForm.setPhone(phone);
        ConsultationMessages messages = consultForm.clickButtonSubmit();

        System.out.println("Имя: <" + name + ">. Email: <" + email + ">. Телефон: <" + phone + ">");
        System.out.println(messages.toString());
        System.out.println();

        Assertions.assertNull(messages.getSuccess());
        Assertions.assertNotNull(messages.getNameError());
        Assertions.assertNotNull(messages.getEmailError());
        Assertions.assertNotNull(messages.getPhoneError());
        Assertions.assertNull(messages.getAgreeError());
        Assertions.assertNotNull(messages.getErrorList());
    }

    @DisplayName("Тест 4. Заполнение поля email валидными данными.")
    @ParameterizedTest
    @ValueSource(strings = {"ivan@mail.ru", "IVAN@MAIL.RU", "ivan7@mail.ru",
            "ivan7@mail7.ru", "Petrov-ivan@mail.ru", "Petrov@mail-m.ru",
            "Petrov_ivan@mail.ru", "Petrov.Ivan@mail.ru", "Petrov.Ivan@m.a.i.l.ru",
            "Ivan@домен.рф"})
    public void setValidEmailTest(String email) {
        consultForm.setEmail(email);
        ConsultationMessages messages = consultForm.clickButtonSubmit();

        Assertions.assertNull(messages.getEmailError());
    }

    @DisplayName("Тест 5. Заполнение поля email не валидными данными.")
    @ParameterizedTest
    @ValueSource(strings = {"ivan@mailru", "ivanmail.ru", "@mail.ru", "ivan@.ru"})
    public void setNotValidEmailTest(String email) {
        System.out.println("Тест. Заполнение поля email не валидными данными.");
        consultForm.setEmail(email);
        ConsultationMessages messages = consultForm.clickButtonSubmit();

        System.out.println("Email: <" + email + ">");
        System.out.println("Сообщение об ошибке email: " + messages.getEmailError());
        System.out.println();

        Assertions.assertNotNull(messages.getEmailError());
    }

    @DisplayName("Тест 6. Смена страны в поле 'Телефон'.")
    @Test
    public void changePhoneMaskTest() {
        consultForm.setPhoneCountry("Ukraine");
        Assertions.assertEquals("+380", consultForm.getPhoneCode());
    }

    @DisplayName("Тест 7. Открытие страницы 'ПОЛОЖЕНИЕ о порядке хранения и защиты персональных данных пользователей'.")
    @Test
    public void openPositionOfUserPersonalDataPageеTest() {
        consultForm.clickPositionOfUserPersonalData();
        ArrayList<String> tabs = new ArrayList(webDriver.getWindowHandles());
        if (tabs.size() > 1) {
            webDriver.switchTo().window(tabs.get(1));
        }
        Assertions.assertEquals("Согласие на обработку персональных данных", webDriver.getTitle());
    }
}

