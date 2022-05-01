package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /* Template methods */
    private static String getFolderXpathByName(String folder_name){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getdArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }
    /* Template methods */

    @Step("Open '{folder_name}' folder")
    public void openFolderByName(String folder_name){
        this.waitForElementAndClick(
                getFolderXpathByName(folder_name),
                "Cannot find folder by name" + folder_name,
                5);
    }

    @Step("Delete article with '{article_title}' title")
    public void swipeArticleToDelete(String article_title){
        this.waitFotArticleToAppearByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    getdArticleXpathByTitle(article_title),
                    "Cannot find saved article");
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot click button to remove article from saved", 10);
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(getdArticleXpathByTitle(article_title), "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitFotArticleToDisappearByTitle(article_title);
    }

    @Step("Wait for article with '{article_title}' title to appear")
    public void waitFotArticleToAppearByTitle(String article_title){
        this.waitForElementPresent(
                getdArticleXpathByTitle(article_title),
                "Cannot find saved article with title " + article_title,
                5);
    }

    @Step("Wait for article with '{article_title}' title to disappear")
    public void waitFotArticleToDisappearByTitle(String article_title){
        this.waitForElementNotPresent(
                getdArticleXpathByTitle(article_title),
                "Saved article still present with title " + article_title,
                15);
    }

    @Step("Click by the article with '{article_title}' title")
    public void clickArticleByTitle(String article_title){
        this.waitForElementAndClick(
                getdArticleXpathByTitle(article_title),
                "Cannot find article with title " + article_title,
                5);
    }
}
