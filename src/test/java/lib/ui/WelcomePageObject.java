package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject{

    protected static final String
        STEP_LEARN_MORE_LINK                        = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT               = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK        = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK   = "id:Learn more about data collected",
        NEXT_LINK                                   = "id:Next",
        GET_STARTED_BUTTON                          = "id:Get started",
        SKIP                                        = "id:Skip";

    public WelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Wait for 'Learn more about Wikipedia' link")
    public void waitForLearnMoreLink(){
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10);
    }

    @Step("Wait for 'New ways to explore' text")
    public void waitForNewWaysToExploreText(){
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' text",
                10);
    }

    @Step("Wait for 'Add or edit preferred languages' link")
    public void waitForAddOrEditPreferredLanguagesText(){
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find 'Add or edit preferred languages' link",
                10);
    }

    @Step("Wait for 'Learn more about data collected' link")
    public void waitForLearnMoreAboutDataCollectedText(){
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find 'Learn more about data collected' link",
                10);
    }

    @Step("Click for 'Next' link")
    public void clickNextLink(){
        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click 'Next' link",
                10);
    }

    @Step("Click for 'Get started' button")
    public void clickGetStartedButton(){
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' button",
                10);
    }

    @Step("Click for 'Skip' button")
    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find and click 'Skip' button", 5);
    }
}
