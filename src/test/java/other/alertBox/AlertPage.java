package other.alertBox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertPage {
    private final WebDriver driver;
    @FindBy(xpath= "//*[@id=\"__next\"]/section[3]/div/div/div/div[1]/p/button")
    private WebElement buttonSimpleAlert;
    @FindBy(xpath = "//*[@id=\"__next\"]/section[3]/div/div/div/div[2]/div/p[1]/button")
    private WebElement buttonConfirmAlert;
    @FindBy(xpath = "//*[@id=\"__next\"]/section[3]/div/div/div/div[3]/p[1]/button")
    private WebElement buttonPrompt;
    @FindBy(css = "#confirm-demo")
    private WebElement textConfirm;

    @FindBy(id = "prompt-demo")
    private WebElement textPrompt;
    public AlertPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void simpleAlert(){
        buttonSimpleAlert.click();
    }

    public void confirmAlert(){
        buttonConfirmAlert.click();
    }

    public void promptAlert(){
        buttonPrompt.click();
    }

    String getConfirmText(){
        return textConfirm.getText();
    }

    String getPromptText(){
        return textPrompt.getText();
    }
}
