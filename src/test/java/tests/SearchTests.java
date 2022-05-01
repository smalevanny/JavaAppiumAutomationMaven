package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.factories.SearchPageObjectFactory;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Perform search")
    @Description("Search for a specific result")
    @Step("Start test testSearch")
    public void testSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel search")
    @Description("Init search, cancel search")
    @Step("Start test testCancelSearch")
    public void testCancelSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check not empty search result")
    @Description("Search and check that search result is not empty")
    @Step("Start test testAmountOfElementsInSearchResult")
    public void testAmountOfElementsInSearchResult(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_input = "Linkin Park Discography";
        searchPageObject.typeSearchLine(search_input);
        Assert.assertTrue("we found too few results", searchPageObject.getAmountOfFoundArticles() > 0);
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Check empty search result")
    @Description("Search and check that search result is empty")
    @Step("Start test testAmountOfElementsInEmptySearchResult")
    public void testAmountOfElementsInEmptySearchResult(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_input = "hcgvsacghvashcvsdcgh";
        searchPageObject.typeSearchLine(search_input);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    @Features(value = {@Feature(value = "Search")})
    @DisplayName("Cancel search")
    @Description("Perform search, cancel search")
    @Step("Start test testCheckSearchAndCancelSearch")
    public void testCheckSearchAndCancelSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        Assert.assertTrue("we found too few results", searchPageObject.getAmountOfFoundArticles() > 0);
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForSearchResultsToDisappear();
    }
}
