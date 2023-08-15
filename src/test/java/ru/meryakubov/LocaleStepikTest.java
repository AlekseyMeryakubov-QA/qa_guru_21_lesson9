package ru.meryakubov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;

public class LocaleStepikTest {

    @BeforeEach
    void setUp() {
        open("https://stepik.org/learn");
    }

    static Stream<Arguments> stepikLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.GERMANY, List.of("Katalog", "Learning", "Teaching")),
                Arguments.of(Locale.ENGLISH, List.of("Catalog", "Learning", "Teaching"))
        );
    }

    @Tags({
            @Tag("smoke"),
            @Tag("web")
    })
    @MethodSource
    @ParameterizedTest
    void stepikLocaleTest(Locale locale, List<String> expectedButtons) {
        open();
    }
}
