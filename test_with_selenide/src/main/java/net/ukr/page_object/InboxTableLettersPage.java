package net.ukr.page_object;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import net.ukr.model.Letter;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;


public class InboxTableLettersPage {
    private final ElementsCollection rowLetters;

    public InboxTableLettersPage() {
        this.rowLetters = $$(By.cssSelector("table.noselect tbody tr"));

    }

    public boolean findLetterInInbox(Letter letter) {
        var list = getAllLettersOnPage();
        return list.contains(letter);
    }

    public List<Letter> getAllLettersOnPage() {
        List<Letter> result = new ArrayList<>();
        for (SelenideElement row : rowLetters) {
            var sender = getSenderEmail(row);
            var data = getDataEmail(row);
            var position = data.trim().indexOf(" ");
            var subject = data.trim().substring(0, position);
            var message = data.trim().substring(position).trim();
            result.add(new Letter(sender, subject, message));
        }
        return result;
    }

    private String getDataEmail(SelenideElement row) {
        return row.find(".msglist__row-subject a").getText();
    }

    private String getSenderEmail(SelenideElement row) {
        return row.find(".msglist__row-address a").attr("data-email");
    }

}
