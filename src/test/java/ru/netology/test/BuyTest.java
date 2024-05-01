package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbDataHelper;
import ru.netology.page.BuyGate;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BuyTest {
    @AfterEach
    void clearTables() {
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
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getApprovedCardInfo();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.successfulNotification();
        assertEquals("APPROVED", DbDataHelper.getBuyStatus());
    }

    @Test
    public void shouldDeclinedOnCredit() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.errorNotification();
        assertEquals("DECLINED", DbDataHelper.getBuyStatus());
    }

    @Test
    public void shouldEmptyFields() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getEmptyCardInfo();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
        buyGate.requiredField();
    }

    @Test
    public void shouldInvalidCardNumber() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getInvalidCardNumber();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }

    @Test
    public void shouldValidCardNumberNotInDB() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getValidCardNumberNotInDB();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.errorNotification();
    }

    @Test
    public void shouldInvalidMonth() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getInvalidMonth();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidYear() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getInvalidYear();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.cardDataExpired();
    }

    @Test
    public void shouldYearMore5() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getYearMore5();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidOwner() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getInvalidOwner();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }

    @Test
    public void shouldNameOwnerRu() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getNameOwnerRu();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }

    @Test
    public void shouldInvalidCVC() {
        var mainPage = new MainPage();
        var buyGate = mainPage.goToBuyPage();
        var cardInfo = DataHelper.getInvalidCVC();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }
}


