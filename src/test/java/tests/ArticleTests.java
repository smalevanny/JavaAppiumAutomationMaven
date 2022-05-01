package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.factories.ArticlePageObjectFactory;
import lib.factories.SearchPageObjectFactory;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Compare article title with expected one")
    @Description("Search a specific article and then check that it has correct title")
    @Step("Start test testCompareArticleTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected text",
                "Java (programming language)",
                article_title);
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("Search an article, click on it and then scroll to its footer")
    @Step("Start test testSwipeArticle")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        articlePageObject.swipeToFooter();
    }

    @Test
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Check that article has not empty title")
    @Description("Search an article, click on it and check that it has not empty title")
    @Step("Start test testCheckArticleHasTitle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckArticleHasTitle(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle();

        Assert.assertNotNull(article_title);
    }
}
