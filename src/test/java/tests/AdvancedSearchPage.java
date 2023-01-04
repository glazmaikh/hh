package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("advanced_search_tests")
public class AdvancedSearchPage extends TestBase {
    SelenideElement searchingForm = $("[data-qa='advanced-vacancy-search__form']");
    SelenideElement submitButton = $(".bloko-modal-footer").find(byText("Выбрать"));
    SelenideElement employmentFindDiv = $(".novafilters");

    @DisplayName("Part-time search test")
    @Test
    void createPartTimeSearchTest() {
        step("Open Advanced Search page", () -> {
            open("/search/vacancy/advanced");
        });
        step("Set IT category", () -> {
            searchingForm.find(byText("Указать специализации")).click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").click();
        });
        step("Set QA profile", () -> {
            $(".bloko-tree-selector__items").find(byText("Тестировщик")).click();
            submitButton.click();
        });
        step("Set region", () -> {
            searchingForm.find(byText("Москва")).ancestor(".bloko-tag-list")
                    .sibling(0).$(".bloko-input-text").val("Россия");
        });
        step("Set remote", () -> {
            searchingForm.find(byText("Удаленная работа")).click();
        });
        step("Set part-time employment", () -> {
            searchingForm.find(byText("Неполный день")).scrollTo().click();
            searchingForm.find(byText("От 4 часов в день")).click();
            searchingForm.find(byText("По выходным")).click();
            searchingForm.find(byText("По вечерам")).click();
        });
        step("Check checked inputs in result", () -> {
            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text("Найден"));

            employmentFindDiv.find(byText("Неполный день")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("От 4 часов в день")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("По выходным")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("По вечерам")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Россия")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Тестировщик")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Удаленная работа")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
        });
    }

    @DisplayName("Selenide from header search test")
    @Test
    void createSelenideFromSearchHeaderTest() {
        step("Open Advanced Search page", () -> {
            open("/search/vacancy/advanced");
        });
        step("Input Selenide to header", () -> {
            searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val("Selenide");
        });
        step("Set IT category", () -> {
            searchingForm.find(byText("Указать специализации")).click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").click();
        });
        step("Set QA profile", () -> {
            $(".bloko-tree-selector__items").find(byText("Тестировщик")).click();
            submitButton.click();
        });
//        step("Set region Russia", () -> {
//            searchingForm.find(byText("Москва")).ancestor(".bloko-tag-list")
//                    .sibling(0).$(".bloko-input-text").val("Россия");
//        });
        step("Set null experience", () -> {
            searchingForm.find(byText("Нет опыта")).scrollTo().click();
        });
        step("Set remote", () -> {
            searchingForm.find(byText("Удаленная работа")).scrollTo().click();
        });
        step("Check checked inputs in result", () -> {
            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text("Selenide"));

            employmentFindDiv.find(byText("Россия")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Тестировщик")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Нет опыта")).ancestor("label")
                    .$(".bloko-radio__input").shouldBe(checked);
            employmentFindDiv.find(byText("Удаленная работа")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("В названии вакансии")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("В названии компании")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("В описании вакансии")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
        });
    }

    @ValueSource(strings = {"Selenide", "Java", "Gradle"})
    @ParameterizedTest(name = "check headers parameter {arguments}")
    void headersParameterTest(String arg) {
        step("Open Advanced Search page", () -> {
            open("/search/vacancy/advanced");
        });
        step("Input Selenide to header", () -> {
            searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val(arg);
        });
        step("Check headers parameter in result", () -> {
            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text(arg));

        });
    }
}
