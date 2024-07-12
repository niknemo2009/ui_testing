package org.example;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.zoom;


@Listeners({ScreenShooter.class})
public class MainTest {
    @BeforeClass
    public void setUp() {
        Configuration.reportsFolder = "../Screenshots";

    }

    @Test(groups = {"fast"})
    public void aFastTest() {

        open("http://google.com33");
        zoom(10);

    }

    @Test(groups = {"slow"})
    public void aSlowTest() {
        System.out.println("Slow test");
    }
}