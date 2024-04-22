package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbDataHelper;
import ru.netology.page.CreditGate;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    @AfterAll
    static void clearTables() {
        DbDataHelper.clearTables();
    }

    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    public void shouldApprovedOnCredit() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getApprovedCardInfo();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.successfulNotification();
        assertEquals("APPROVED", DbDataHelper.getCreditStatus());
    }

    @Test
    public void shouldDeclinedOnCredit() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.errorNotification();
        assertEquals("DECLINED", DbDataHelper.getCreditStatus());
    }

    @Test
    public void shouldEmptyFields() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getEmptyCardInfo();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
        creditGate.requiredField();
    }

    @Test
    public void shouldInvalidCardNumber() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getInvalidCardNumber();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }

    @Test
    public void shouldValidCardNumberNotInDB() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getValidCardNumberNotInDB();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.errorNotification();
    }

    @Test
    public void shouldInvalidMonth() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getInvalidMonth();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidYear() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getInvalidYear();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.cardDataExpired();
    }

    @Test
    public void shouldYearMore5() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getYearMore5();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidOwner() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getInvalidOwner();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }

    @Test
    public void shouldNameOwnerRu() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getNameOwnerRu();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }

    @Test
    public void shouldInvalidCVC() {
        var mainPage = new MainPage();
        var creditGate = new CreditGate();
        var cardInfo = DataHelper.getInvalidCVC();
        mainPage.goToCreditPage();
        creditGate.heading();
        creditGate.inputData(cardInfo);
        creditGate.wrongFormat();
    }
}
