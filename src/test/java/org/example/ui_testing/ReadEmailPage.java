package org.example.ui_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ReadEmailPage {
    private WebDriver driver;

    public ReadEmailPage(WebDriver driver) {

    }

    public boolean findLetterInInbox(Letter letter){
        return getAllOnFirstPage().contains(letter);
    }

    public List<Letter>  getAllOnFirstPage() {
        List<Letter> result=new ArrayList<>();
        return result;
    }

    public static void main(String[] args) {
        List<Letter> list=List.of(new Letter("1","1","1",List.of("qwe")));
        System.out.println(list.contains(new Letter("1","1","1",List.of("qwe1"))));
    }
}
