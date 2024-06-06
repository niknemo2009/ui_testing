package checkBox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckBoxPage {
    private final WebDriver driver;
    @FindBy(css = "#isAgeSelected")
    WebElement singleCheckBox;
    @FindBy(css = "#txtAge")
    public WebElement textSingle;

    @FindBy(xpath="//*[@id=\"__next\"]/div/section[2]/div/div/div/div[2]/div[2]/div[1]/input")
    private WebElement disableCheckBox1;

    @FindBy(xpath="//*[@id=\"__next\"]/div/section[2]/div/div/div/div[2]/div[2]/div[2]/input")
    private WebElement disableCheckBox2;
    @FindBy(xpath="//*[@id=\"__next\"]/div/section[2]/div/div/div/div[2]/div[2]/div[3]/input")
    private WebElement disableCheckBox3;
    @FindBy(xpath="//*[@id=\"__next\"]/div/section[2]/div/div/div/div[2]/div[2]/div[4]/input")
    private WebElement disableCheckBox4;

    public CheckBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void singleCheckBox() {
       singleCheckBox.click();
    }


}
