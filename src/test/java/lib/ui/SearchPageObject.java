package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULTS_ELEMENT,
            EMPTY_RESULT_LABEL;

    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /* Template methods */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Template methods */

    @Step("Initialize search field")
    public void initSearchInput(){
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find init search element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click init search element", 5);
    }

    @Step("Type search '{search_line}' line into search field")
    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 5);
    }

    @Step("Wait for specific search result, which contains '{substring}'")
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }

    @Step("Click by article, which contains '{substring}'")
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    @Step("Wait for button to cancel search")
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    @Step("Wait for search cancel button to disappear")
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    @Step("Wait for search results to disappear")
    public void waitForSearchResultsToDisappear(){
        this.waitForElementNotPresent(SEARCH_RESULTS_ELEMENT, "Search results are still present", 5);
    }

    @Step("Click search cancel button")
    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    @Step("Get amount of articles in search results")
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULTS_ELEMENT,
                "Cannot find anything by the request ",
                15);

        return this.getAmountOfElements(SEARCH_RESULTS_ELEMENT);
    }

    @Step("Wait for empty search result label")
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                EMPTY_RESULT_LABEL,
                "Cannot find empty result label",
                15);
    }

    @Step("Make sure that there are no results for the search")
    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULTS_ELEMENT, "We supposed not to find any results");
    }
}
