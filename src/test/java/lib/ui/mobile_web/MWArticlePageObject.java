package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE                       = "css:#content h1";
        FOOTER_ELEMENT              = "css:mw-footer";
        ADD_TO_MY_LIST_BUTTON       = "css:#ca-watch";
        REMOVE_FROM_MY_LIST_BUTTON  = "css:a.watched";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
