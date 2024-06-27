package net_ukr_actions.page_object;


import org.openqa.selenium.By;


public class InboxTableLettersPage {

    private final By rowLetters = By.cssSelector("table.noselect tbody tr");

    public By rowLetters() {
        return rowLetters;
    }
}
