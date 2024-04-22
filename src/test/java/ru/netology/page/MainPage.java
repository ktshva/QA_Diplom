package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement BuyButton = $(byText("Купить"));
    private SelenideElement CreditButton = $(byText("Купить в кредит"));

    public BuyGate goToBuyPage() {
        BuyButton.click();
        return new BuyGate();
    }

    public CreditGate goToCreditPage() {
        CreditButton.click();
        return new CreditGate();
    }
}