package ru.meryakubov;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.pageLoadStrategy;
import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTest {

    static {
        pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {

        open("https://kinopoisk.ru/");
    }

    //    @CsvSource(value = {
//            "I Am Legend | я легенда",
//            "Барби 2023  |  Барби"
//    },
//            delimiter = '|')
    @CsvFileSource(resources = "/successfulSearchTextTest.csv")
    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска на Кинопоиске")
    @ParameterizedTest(name = "В поисковой выдаче Кинопоиска присутствует фильм {0} по запросу {1}")
    void successfulSearchTextTest(String url, String searchQuery) {
        $("input[type='text']")
                .setValue(searchQuery)
                .pressEnter();

        $(".block_left_pad").shouldHave(text(url));
    }

    @ValueSource(
            strings = {"Барби 2023", "Барби"}
    )
//    @CsvFileSource(resources = "/successfulSearchTextTest.csv")
    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска на Кинопоиске")
    @ParameterizedTest(name = "В поиске Кинопоиска выдача не пустая для запроса {0}")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("input[type='text']")
                .setValue(searchQuery)
                .pressEnter();

        $$(".block_left_pad").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}
