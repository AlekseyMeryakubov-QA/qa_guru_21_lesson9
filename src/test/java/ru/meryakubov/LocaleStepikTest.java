package ru.meryakubov;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;

public class LocaleStepikTest {

    @BeforeEach
    void setUp() {
        open("https://stepik.org/learn");
    }

    static Stream<Arguments> stepikLocaleTest() {
        return Stream.of(
                Arguments.of(Locale.Deutsch, List.of("Katalog", "Teaching")),
                Arguments.of(ru.meryakubov.Locale.English, List.of("Catalog", "Learning"))
        );
    }

    @Tags({
            @Tag("smoke"),
            @Tag("web")
    })
    @MethodSource("stepikLocaleTest")
    @ParameterizedTest
    void stepikLocaleTest(Locale locale, List<String> expectedButtons) {
        $("span[id='ember121']").click();
        $$("button[id='ember248']").findBy(Condition.attribute((String.valueOf(locale)))).click();
        $$("nav[id='main-navbar']").shouldHave(CollectionCondition.texts(expectedButtons));
    }
}
