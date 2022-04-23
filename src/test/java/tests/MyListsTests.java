package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.factories.ArticlePageObjectFactory;
import lib.factories.MyListsObjectFactory;
import lib.factories.NavigationUIFactory;
import lib.factories.SearchPageObjectFactory;
import lib.ui.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    public static final String
            FOLDER_NAME = "Learning programming",
            LOGIN       = "bbserg",
            PASSWORD    = "Wiki1930";

    @Test
    public void testSaveFirstArticleToMyListAndDeleteIt(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(FOLDER_NAME);
        } else {
            articlePageObject.addArticleToMySaved();
        }
        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(LOGIN, PASSWORD);
            auth.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", article_title, articlePageObject.getArticleTitle());
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(FOLDER_NAME);
        }
        myListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyListAndDeleteSecondOne(){
        String search_input_for_first_article   = "Java";
        String search_input_for_second_article  = "Python";
        String search_text_for_first_article    = "Object-oriented programming language";
        String search_text_for_second_article   = "General-purpose programming language";

        //search for the first article
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_input_for_first_article);
        searchPageObject.clickByArticleWithSubstring(search_text_for_first_article);

        //get the first article's title
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement();
        String title_for_the_first_article = articlePageObject.getArticleTitle();

        //add first article to new reading list
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(FOLDER_NAME);
        } else {
            articlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(LOGIN, PASSWORD);
            auth.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", title_for_the_first_article, articlePageObject.getArticleTitle());
            articlePageObject.addArticleToMySaved();
        }
        articlePageObject.closeArticle();

        //search for the second article
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine(search_input_for_second_article);
        searchPageObject.clickByArticleWithSubstring(search_text_for_second_article);

        //get the second article's title
        articlePageObject.waitForTitleElement();
        String title_for_the_second_article = articlePageObject.getArticleTitle();

        //add second article to existing reading list
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(FOLDER_NAME, false);
        } else {
            articlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(LOGIN, PASSWORD);
            auth.submitForm();

            articlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", title_for_the_first_article, articlePageObject.getArticleTitle());
            articlePageObject.addArticleToMySaved();
        }

        articlePageObject.closeArticle();

        //open existing reading list and delete second article
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyLists();
        MyListsPageObject myListsPageObject = MyListsObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(FOLDER_NAME);
        }

        myListsPageObject.swipeArticleToDelete(title_for_the_second_article);

        //check that first article is still present
        //TODO: finnish when issue with Appium standalone Inspector is solved on My Mac
        myListsPageObject.clickArticleByTitle(title_for_the_first_article);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected text",
                title_for_the_first_article,
                article_title);
    }
}
