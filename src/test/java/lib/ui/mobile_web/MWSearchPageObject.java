package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT             = "css:button#searchIcon";
        SEARCH_INPUT                    = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON            = "css:div.header-action>button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL  = "xpath://*[contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULTS_ELEMENT          = "css:ul.page-list>li.page-summary";
        EMPTY_RESULT_LABEL              = "css:p.without-results";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
