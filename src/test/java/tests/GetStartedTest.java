package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests for iOS Welcome Page")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "iOS Welcome Page")})
    @DisplayName("Pass through iOS Welcome Page")
    @Description("Pass all steps at iOS Welcome Page Wizard")
    @Step("Start test testPassThroughWelcome. Does nothing for Android and Mobile Web")
    public void testPassThroughWelcome(){
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isMW()) {
            return;
        }

        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextLink();
        welcomePage.waitForNewWaysToExploreText();
        welcomePage.clickNextLink();
        welcomePage.waitForAddOrEditPreferredLanguagesText();
        welcomePage.clickNextLink();
        welcomePage.waitForLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }

}
