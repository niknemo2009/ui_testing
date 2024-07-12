package net.ukr;


import net.ukr.base.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

public class ProbaTest extends BaseTest {


    @BeforeEach
    public void beforeEach() {
        driver.get("https://accounts.ukr.net/login");


    }


    @RepeatedTest(3)
    public void sendValidEmail() {


    }

}
