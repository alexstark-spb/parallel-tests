package com.alexstark;

import com.alexstark.page.YandexMainPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;

@ExtendWith(SimpleCallback.class)
public class ParallelTests {

    private YandexMainPage ymp = new YandexMainPage();

    @ValueSource(strings = {
            "selenide",
            "gradle",
            "qameta",
            "allure"
    })
    @ParameterizedTest(name = "{0} test")
    void yandexSearchTest(String searchQuery, TestInfo testInfo) {
        Configuration.startMaximized = true;
        open(ymp.URL);
        ymp.doSearch(searchQuery).checkResults(searchQuery);

        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
    }

    @DisplayName("JDI")
    @Test
    void minimizedWindowTest(TestInfo testInfo) {
        Configuration.startMaximized = false;
        open(YandexMainPage.URL);
        ymp.doSearch("JDI")
                .checkResults("JDI");
        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
    }
}