package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void userShouldBeLoggedInWithEmail() {
        loginSteps
                .login(email, password);
    }
}
