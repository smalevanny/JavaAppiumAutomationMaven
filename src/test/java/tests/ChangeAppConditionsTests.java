package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.factories.ArticlePageObjectFactory;
import lib.factories.SearchPageObjectFactory;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for special application condition")
public class ChangeAppConditionsTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Special Conditions")})
    @DisplayName("Compare article titles before and after rotation")
    @Description("Search a specific article and check its title before and after screen rotation")
    @Step("Start test testChangeScreenOrientationSearchResults. Does nothing for Mobile Web")
    public void testChangeScreenOrientationSearchResults(){
        if (Platform.getInstance().isMW()) return;
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = articlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article"), @Feature(value = "Special Conditions")})
    @DisplayName("Check that search result is present after going to background mode")
    @Description("Search a specific article, go to background mode for 2 seconds, check that same article is still visible")
    @Step("Start test testSearchArticleInBackground. Does nothing for Mobile Web")
    public void testSearchArticleInBackground(){
        if (Platform.getInstance().isMW()) return;
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
