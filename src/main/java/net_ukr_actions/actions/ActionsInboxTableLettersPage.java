package net_ukr_actions.actions;


import net_ukr_actions.model.Letter;
import net_ukr_actions.page_object.InboxTableLettersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ActionsInboxTableLettersPage {
    InboxTableLettersPage inboxTableLettersPage;
    WebDriver driver;
    protected WebDriverWait wait;


    public ActionsInboxTableLettersPage(WebDriver driver) {
        this.driver = driver;
        this.inboxTableLettersPage = new InboxTableLettersPage();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean findLetterInInbox(Letter letter) {
        var list = getAllLettersOnPage();
        return list.contains(letter);
    }

    public List<Letter> getAllLettersOnPage() {
        List<Letter> result = new ArrayList<>();
        // List<WebElement> rowLetters = driver.findElements(inboxTableLettersPage.rowLetters());
        List<WebElement> rowLetters = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inboxTableLettersPage.rowLetters()));
        for (WebElement row : rowLetters) {
            var sender = getSenderEmail(row);
            var data = getDataEmail(row);
            var position = data.trim().indexOf(" ");
            var subject = data.trim().substring(0, position);
            var message = data.trim().substring(position).trim();
            result.add(new Letter(sender, subject, message));
        }
        return result;
    }

    private String getDataEmail(WebElement row) {
        return row.findElement(By.cssSelector(".msglist__row-subject a")).getText();
    }

    private String getSenderEmail(WebElement row) {
        return row.findElement(By.cssSelector(".msglist__row-address a")).getAttribute("data-email");
    }


}
