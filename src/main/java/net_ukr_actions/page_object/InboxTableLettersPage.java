package net_ukr_actions.page_object;


import org.openqa.selenium.By;


public class InboxTableLettersPage {

//    @FindBy(css = "table.noselect tbody tr")
//    private List<WebElement> rowLetters;

    private final By rowLetters = By.cssSelector("table.noselect tbody tr");

    public By rowLetters() {
        return rowLetters;
    }
}
