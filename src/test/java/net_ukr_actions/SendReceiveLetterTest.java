package net_ukr_actions;


import net_ukr_actions.actions.ActionsLoginPage;
import net_ukr_actions.actions.ActionsSendEmailPage;
import net_ukr_actions.base.BaseTest;
import net_ukr_actions.model.Letter;
import net_ukr_actions.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.UUID;

public class SendReceiveLetterTest extends BaseTest {
    private ActionsLoginPage actionsLoginPage;
    // private InboxTableLettersPage inboxTableLettersPage;
    private final User EXISTING_USER = new User(System.getProperty("user"), System.getProperty("password"));
    private final String EXPECTED_TITLE = "Пошта @ ukr.net - українська електронна пошта • Створи емейл";


    @BeforeEach
    public void beforeEach() {
        driver.get("https://accounts.ukr.net/login");
        actionsLoginPage = new ActionsLoginPage(driver);

    }


    @RepeatedTest(1)
    public void sendValidEmail() throws Exception {
        Assertions.assertEquals(EXPECTED_TITLE, driver.getTitle());
        Letter validLetter = new Letter(EXISTING_USER.getEmail(), "test_subject_" + UUID.randomUUID(), "message 133333327777456");
        var expectedActions = actionsLoginPage.signInUser(EXISTING_USER, new ActionsSendEmailPage(driver));
        expectedActions.sendEmail(validLetter, new ActionsSendEmailPage(driver));
//                        .
//                sendEmail(validLetter, new ActionsSendEmailPage(driver));
//                        .
//                toInbox();
//        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(1)); //??????????????????????????????????????
//        wait.until(d -> expectedActions.findLetterInInbox(validLetter));
//
//        Assertions.assertTrue(expectedActions.findLetterInInbox(validLetter));


    }

}
