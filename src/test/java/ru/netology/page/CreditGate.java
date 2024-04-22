package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditGate {
    private final SelenideElement heading = $(byText("Кредит по данным карты"));
    private final SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("input");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("input");
    private final SelenideElement yearField = $(byText("Год")).parent().$("input");
    private final SelenideElement cardHolderField = $(byText("Владелец")).parent().$("input");
    private final SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("input");
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private final SelenideElement successNotification = $(".notification_status_ok");
    private final SelenideElement errorNotification = $(".notification_status_error");
    private final SelenideElement wrongFormat = $(byText("Неверный формат"));
    private final SelenideElement cardDataIncorrect = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardDataExpired = $(byText("Истёк срок действия карты"));
    private final SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));


    public void inputData(DataHelper.CardInfo card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getName());
        cvvField.setValue(card.getCode());
        continueButton.click();
    }

    public void successfulNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void wrongFormat() {
        wrongFormat.shouldBe(Condition.visible);
    }

    public void cardDataIncorrect() {
        cardDataIncorrect.shouldBe(Condition.visible);
    }

    public void cardDataExpired() {
        cardDataExpired.shouldBe(Condition.visible);
    }

    public void requiredField() {
        requiredField.shouldBe(Condition.visible);
    }

    public void heading() {
        heading.shouldBe(Condition.visible);
    }
}