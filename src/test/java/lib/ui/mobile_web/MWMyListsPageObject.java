package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL    = "//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "//li[contains(@title, '{TITLE}')]//a[contains(@title, 'Remove this page from your watchlist')]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
