package ukr_net.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ukr_net.pojo.Letter;

import java.util.ArrayList;
import java.util.List;

public class InboxTableLettersPage {
    private final WebDriver driver;
    @FindBy(css = "table.noselect")
    private WebElement tableLetters;

    public InboxTableLettersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean findLetterInInbox(Letter letter) {
        var list = getAllOnFirstPage();
        return list.contains(letter);
    }

    public List<Letter> getAllOnFirstPage() {
        List<Letter> result = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("table.noselect tbody tr "));
        for (WebElement row : rows) {
            var sender = row.findElement(By.cssSelector(".msglist__row-address a")).getAttribute("data-email");
            var data = row.findElement(By.cssSelector(".msglist__row-subject a")).getText();
            var position = data.trim().indexOf(" ");
            var subject = data.trim().substring(0, position);
            var message = data.trim().substring(position).trim();
            result.add(new Letter(sender, subject, message));
        }
        return result;
    }

}
