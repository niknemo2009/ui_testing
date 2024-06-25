package net.ukr.page_object;

import net.ukr.model.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class InboxTableLettersPage extends BasePage {

    @FindBy(css = "table.noselect tbody tr")
    private List<WebElement> rowLetters;

    public InboxTableLettersPage(WebDriver driver) {
        super(driver);
    }

    public boolean findLetterInInbox(Letter letter) {
        var list = getAllLettersOnPage();
        return list.contains(letter);
    }

    public List<Letter> getAllLettersOnPage() {
        List<Letter> result = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(rowLetters));
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

    private static String getDataEmail(WebElement row) {
        //?????????????????????????????//
        return row.findElement(By.cssSelector(".msglist__row-subject a")).getText();
    }

    private static String getSenderEmail(WebElement row) {
        //?????????????????????????????//
        return row.findElement(By.cssSelector(".msglist__row-address a")).getAttribute("data-email");
    }

}
