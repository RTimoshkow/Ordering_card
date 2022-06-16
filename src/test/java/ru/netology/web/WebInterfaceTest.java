package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebInterfaceTest {

    @Test
    void shouldValidValues() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Роман");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(text("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldInvalidNameValue() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Roman");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldInvalidTelephoneValue() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Роман");
        $("[data-test-id=phone] input").setValue("12345678901");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldUncheckedCheckBox() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Роман");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $(".button").click();
        $(".input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
