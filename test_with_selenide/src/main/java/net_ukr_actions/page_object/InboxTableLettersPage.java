package net_ukr_actions.page_object;


import com.codeborne.selenide.ElementsCollection;
import net_ukr_actions.model.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;


public class InboxTableLettersPage {
    private final WebDriver driver;
    private final ElementsCollection rowLetters;

    public InboxTableLettersPage(WebDriver driver) {
        this.driver = driver;
        this.rowLetters = $$(By.cssSelector("table.noselect tbody tr"));

    }

    public boolean findLetterInInbox(Letter letter) {
        var list = getAllLettersOnPage();
        return list.contains(letter);
    }

    public List<Letter> getAllLettersOnPage() {
        List<Letter> result = new ArrayList<>();

        //   rowLetters.;
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
