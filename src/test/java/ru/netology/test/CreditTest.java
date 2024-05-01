package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbDataHelper;
import ru.netology.page.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    @AfterEach
    void clearTables() throws SQLException {
        DbDataHelper.clearTables();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setup() {
        var weburl = System.getProperty("web.url");
        open(weburl);
    }

    @Test
    public void shouldApprovedOnCredit() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getApprovedCardInfo();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.successfulNotification();
        assertEquals("APPROVED", DbDataHelper.getCreditStatus());
    }

    @Test
    public void shouldDeclinedOnCredit() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.errorNotification();
        assertEquals("DECLINED", DbDataHelper.getCreditStatus());
    }

    @Test
    public void shouldEmptyFields() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getEmptyCardInfo();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
        creditGate.requiredField();
    }

    @Test
    public void shouldInvalidCardNumber() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getInvalidCardNumber();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }

    @Test
    public void shouldValidCardNumberNotInDB() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getValidCardNumberNotInDB();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.errorNotification();
    }

    @Test
    public void shouldInvalidMonth() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getInvalidMonth();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidYear() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getInvalidYear();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.cardDataExpired();
    }

    @Test
    public void shouldYearMore5() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getYearMore5();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidOwner() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getInvalidOwner();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }

    @Test
    public void shouldNameOwnerRu() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getNameOwnerRu();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }

    @Test
    public void shouldInvalidCVC() {
        var mainPage = new MainPage();
        var creditGate = mainPage.goToCreditPage();
        var cardInfo = DataHelper.getInvalidCVC();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }
}
