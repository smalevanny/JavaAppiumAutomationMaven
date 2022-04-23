package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT             = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_INPUT                    = "xpath://*[contains(@text, 'Search…')]";
        SEARCH_CANCEL_BUTTON            = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL  = "xpath://*[contains(@text, '{SUBSTRING}')]";
        SEARCH_RESULTS_ELEMENT          = "id:org.wikipedia:id/search_results_list";
        EMPTY_RESULT_LABEL              = "xpath://*[@text='No results found']";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
