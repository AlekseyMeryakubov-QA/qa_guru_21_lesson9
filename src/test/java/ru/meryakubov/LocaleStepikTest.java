package ru.meryakubov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LocaleStepikTest {

    @BeforeEach
    void setUp() {
        open("https://stepik.org/learn");
    }

    static Stream<Arguments> stepikLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.DE, List.of("Katalog", "Teaching")),
                Arguments.of(Locale.EN, List.of("Catalog", "Teaching"))
        );
    }
    @Tags({
            @Tag("smoke"),
            @Tag("web")
    })
    @MethodSource("stepikLocaleTest")
    @ParameterizedTest
    void stepikLocaleTest(Locale locale, List<String> expectedButtons) {
        $(".modal-button-close-icon").click();
        $(".drop-down-toggler--auto-toggle").click();
        $(".drop-down__body").$(byText(locale.getLanguage())).click();
        $("#main-navbar").shouldHave(text(String.join(" ", expectedButtons)));
    }
}
