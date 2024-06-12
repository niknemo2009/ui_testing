package lambdatest_com.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckBoxPage {
    private final WebDriver driver;
    @FindBy(css = "#isAgeSelected")
  public   WebElement singleCheckBox;
    @FindBy(css = "#txtAge")
    public WebElement textSingle;
    @FindBy(css = "div.w-full.text-center.mt-40 input")
  public   WebElement buttonMulti;
    //    @FindBys({@FindBy(tagName = "div"), @FindBy(css= ".pb-10  input")})
    @FindBy(css = "div.pb-10  input")
    private List<WebElement> disableCheckBoxes;
    @FindBy(css = ".cb-element.mr-10")
    private List<WebElement> multiCheckBoxes;

    public CheckBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public WebElement getDisabledCheckBox1() {
        return disableCheckBoxes.subList(1, 5).get(0);
    }

    public WebElement getDisabledCheckBox2() {
        return disableCheckBoxes.subList(1, 5).get(1);
    }

    public WebElement getDisabledCheckBox3() {
        return disableCheckBoxes.subList(1, 5).get(2);
    }

    public WebElement getDisabledCheckBox4() {
        return disableCheckBoxes.subList(1, 5).get(3);
    }

    public WebElement getMultiCheckBox1() {
        return multiCheckBoxes.subList(0, 4).get(0);
    }

    public WebElement getMultiCheckBox2() {
        return multiCheckBoxes.subList(0, 4).get(1);
    }

    public WebElement getMultiCheckBox3() {
        return multiCheckBoxes.subList(0, 4).get(2);
    }

    public WebElement getMultiCheckBox4() {
        return multiCheckBoxes.subList(0, 4).get(3);
    }

}
