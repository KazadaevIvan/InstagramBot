package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void userShouldBeLoggedInWithEmail() throws IOException {
        loginSteps
                .login(email, password);
    }
}
