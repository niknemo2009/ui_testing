package org.example.ui_testing;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailPage {
    private WebDriver driver;
    public WebElement buttonCreateEmail;
    public WebElement  inputReciver;
    public  WebElement  inputSubject;
    public  WebElement  bodyMessage ;
    public  WebElement  buttonSend;
    public  WebElement  buttonFileAttach;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
        buttonCreateEmail=driver.findElement(By.cssSelector(".button.primary.compose"));
        buttonCreateEmail.click();
        this.inputReciver = driver.findElement(By.name("toFieldInput"));
        this.inputSubject =  driver.findElement(By.cssSelector("input.input[name='subject']"));
       //        this.bodyMessage = bodyMessage;
        this.buttonSend =  driver.findElement(By.xpath("//*[@id='screens']/div/div[1]/div/button"));
        this.buttonFileAttach = driver.findElement(By.xpath("//*[@id=\"screens\"]/div/div[2]/section[2]/div[2]/label/button"));
    }

    public void sendEmail(String reciver, String subject,String message, String pathAttachFile){
     inputReciver.sendKeys(reciver);
     inputSubject.sendKeys(subject);
     buttonSend.click();

    }

}
//todo Command or Builder for dif variants send(simple,with  attach ...) ????????
//todo normalisation all selector !!!!!