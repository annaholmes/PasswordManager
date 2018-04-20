package LoginValidation;
import java.security.SecureRandom;


public class LoginValidation {

    public Boolean validate(String passwordAttempt, String correctHashedPassword) {
        return BCrypt.checkpw(passwordAttempt, correctHashedPassword);
    }


    public void setUpPassword(String password) {
        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(password, salt);
        // TODO store password in database
    }

    public Boolean doesPasswordExist() {
        // check database for existing password info
         return false;
    }

    public void resetPassword(String oldPassword, String newPassword) {
        // TODO drop old password from database
        setUpPassword(newPassword);
    }

}
