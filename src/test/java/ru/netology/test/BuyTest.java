package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbDataHelper;
import ru.netology.page.BuyGate;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BuyTest {
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
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getApprovedCardInfo();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.successfulNotification();
        assertEquals("APPROVED", DbDataHelper.getBuyStatus());
    }

    @Test
    public void shouldDeclinedOnCredit() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getDeclinedCardInfo();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.errorNotification();
        assertEquals("DECLINED", DbDataHelper.getBuyStatus());
    }

    @Test
    public void shouldEmptyFields() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getEmptyCardInfo();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
        buyGate.requiredField();
    }

    @Test
    public void shouldInvalidCardNumber() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getInvalidCardNumber();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }

    @Test
    public void shouldValidCardNumberNotInDB() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getValidCardNumberNotInDB();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.errorNotification();
    }

    @Test
    public void shouldInvalidMonth() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getInvalidMonth();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidYear() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getInvalidYear();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.cardDataExpired();
    }

    @Test
    public void shouldYearMore5() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getYearMore5();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.cardDataIncorrect();
    }

    @Test
    public void shouldInvalidOwner() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getInvalidOwner();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }

    @Test
    public void shouldNameOwnerRu() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getNameOwnerRu();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }

    @Test
    public void shouldInvalidCVC() {
        var mainPage = new MainPage();
        var buyGate = new BuyGate();
        var cardInfo = DataHelper.getInvalidCVC();
        mainPage.goToBuyPage();
        buyGate.heading();
        buyGate.inputData(cardInfo);
        buyGate.wrongFormat();
    }
}


