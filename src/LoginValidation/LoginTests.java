package LoginValidation;

import org.junit.Assert;
import org.junit.Test;

public class LoginTests {
    @Test
    public void testWorkingPassword(){
        LoginValidation lv = new LoginValidation();
        lv.setUpPassword("abc");
        Assert.assertTrue(lv.validate("abc", "$2a$10$PP7vsL//D2fDvbrH5cgc0.Ud6uXxvZBTuX7YpYpM6MqsQ3XQwwVXi"));

    }
}
