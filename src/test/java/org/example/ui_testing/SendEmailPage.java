package org.example.ui_testing;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SendEmailPage {
    JavascriptExecutor js;
    private final WebDriver driver;
    private final WebElement buttonCreateEmail;
    private final WebElement inputReciver;
    private final WebElement inputSubject;
    private final WebElement buttonSend;
    private final WebElement buttonFileAttach;

    public SendEmailPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        buttonCreateEmail = driver.findElement(By.cssSelector(".button.primary.compose"));
        buttonCreateEmail.click();
        this.inputReciver = driver.findElement(By.name("toFieldInput"));
        this.inputSubject = driver.findElement(By.cssSelector("input.input[name='subject']"));
        //        this.bodyMessage = bodyMessage;
        this.buttonSend = driver.findElement(By.xpath("//*[@id='screens']/div/div[1]/div/button"));
        this.buttonFileAttach = driver.findElement(By.xpath("//*[@id=\"screens\"]/div/div[2]/section[2]/div[2]/label/button"));
    }


    public void sendEmail(Letter letter) {
        inputReciver.sendKeys(letter.receiver());
        inputSubject.sendKeys(letter.subject());
        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(iframe);
        String newBody = """
                '<body id="tinymce" class="mce-content-body " data-id="mce_2" contenteditable="true"><div><span style="font-size: 12pt; line-height: 14pt; font-family: Arial;" data-mce-style="line-height: 14pt; font-family: Arial; font-size: 12pt;" class="customFontStyle">%s</span></body>'    
                    """.formatted(letter.message());
        js.executeScript("document.getElementById('tinymce').innerHTML=" + newBody);
        driver.switchTo().defaultContent();
        buttonSend.click();

    }

    public void checkEmail(Letter letter) {

    }
}
//todo Command or Builder for dif variants send(simple,with  attach ...) ????????
//todo normalisation all selector !!!!!