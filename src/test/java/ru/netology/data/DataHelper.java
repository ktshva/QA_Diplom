package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private static final Faker faker = new Faker();

    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    public static String getValidMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getValidYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidOwnerName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getValidCvc() {
        String cvc = faker.regexify("[0-9]{3}");
        return cvc;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String name;
        String code;
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwnerName(), getValidCvc());
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidOwnerName(), getValidCvc());
    }

    public static CardInfo getEmptyCardInfo() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getInvalidCardNumber() {
        return new CardInfo("4444 4444 4444", getValidMonth(), getValidYear(), getValidOwnerName(), getValidCvc());
    }

    public static CardInfo getValidCardNumberNotInDB() {
        return new CardInfo("4444 4444 4444 4443", getValidMonth(), getValidYear(), getValidOwnerName(), getValidCvc());
    }

    public static CardInfo getInvalidMonth() {
        return new CardInfo(getApprovedCardNumber(), "13", getValidYear(), getValidOwnerName(), getValidCvc());
    }

    public static CardInfo getInvalidYear() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "23", getValidOwnerName(), getValidCvc());
    }

    public static CardInfo getYearMore5() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), "30", getValidOwnerName(), getValidCvc());
    }

    public static CardInfo getInvalidOwner() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "*****", getValidCvc());
    }

    public static CardInfo getNameOwnerRu() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), "Иван Петров", getValidCvc());
    }

    public static CardInfo getInvalidCVC() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidOwnerName(), "99");
    }
}
