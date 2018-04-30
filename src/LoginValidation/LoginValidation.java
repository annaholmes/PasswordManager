package LoginValidation;
import Unsorted.Database;

import java.io.File;
import java.security.SecureRandom;
import java.sql.SQLException;


public class LoginValidation {

    private Database database;
    private int numTries = 6;


    public LoginValidation() throws SQLException, ClassNotFoundException {
        database = new Database();
    }

    public Boolean validate(String passwordAttempt) throws SQLException {
        String correctHashedPassword = database.getMasterPassword();
        return BCrypt.checkpw(passwordAttempt, correctHashedPassword);
    }


    public void setUpPassword(String password) throws SQLException {
        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(password, salt);
        database.setMasterPassword(hashed);
        database.setMasterTable(numTries);
    }

    public static Boolean doesPasswordExist() throws SQLException, ClassNotFoundException {
        File file = new File("Passwords");
        return file.exists();
    }

    public void resetPassword(String oldPassword, String newPassword) throws SQLException {
        // TODO drop old password from database
        setUpPassword(newPassword);
    }



}
