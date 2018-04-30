package LoginValidation;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class LoginTests {
    @Test
    public void testWorkingPassword() throws SQLException, ClassNotFoundException {
        LoginValidation lv = new LoginValidation();
        lv.setUpPassword("abc");
        Assert.assertTrue(lv.validate("abc"));
    }

    // this works after deleting the Passwords file
    // TODO make a drop method to perform this test programatically
    @Test
    public void testPasswordCheck() throws SQLException, ClassNotFoundException {
        Assert.assertFalse(LoginValidation.doesPasswordExist());
        //Assert.assertFalse(lv.doesPasswordExist());
    }
}
