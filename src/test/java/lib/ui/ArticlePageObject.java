package lib.ui;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_MENU,
            ADD_TO_MY_LIST_BUTTON,
            REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            EXISTING_FOLDER_ELEMENT_TPL;

    /* Template methods */
    private static String getExistingFolderElement(String folder_name){
        return EXISTING_FOLDER_ELEMENT_TPL.replace("{FOLDER_NAME}", folder_name);
    }
    /* Template methods */

    public ArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle(){
        WebElement element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()){
            return element.getAttribute("name");
        } else {
            return element.getText();
        }

    }

    public void swipeToFooter(){
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.scrollWebPageTillElementIsVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyList(String folder_name, Boolean newReadingList){
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);

        this.waitForElementPresent(
                OPTIONS_MENU,
                "Cannot find options menu",
                15);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);

        if (newReadingList) {
            this.waitForElementAndClick(
                    ADD_TO_MY_LIST_OVERLAY,
                    "Cannot find 'GOT IT' tip overlay",
                    5);

            this.waitForElementAndClear(
                    MY_LIST_NAME_INPUT,
                    "Cannot find input to set name articles folder",
                    5);

            this.waitForElementAndSendKeys(
                    MY_LIST_NAME_INPUT,
                    folder_name,
                    "Cannot put text into articles folder name input",
                    5);

            this.waitForElementAndClick(
                    MY_LIST_OK_BUTTON,
                    "Cannot find 'OK' button",
                    5);
        } else {
            this.waitForElementAndClick(
                    getExistingFolderElement(folder_name),
                    "Cannot find option to add article to reading list",
                    5);
        }
    }

    public void addArticleToMyList(String folder_name){
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromMySavedIfAdded();
        }
        addArticleToMyList(folder_name, true);
    }

    public void addArticleToMySaved() {
        this.waitForElementAndClick(ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromMySavedIfAdded(){
        if (this.isElemenetPresent(REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove an article from saved", 1);
            this.waitForElementPresent(ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list after removing it from the list before");
        }
    }

    public void closeArticle(){
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot find X button",
                    5);
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }
}
