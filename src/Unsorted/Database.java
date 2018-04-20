package Unsorted;
import Encryption.Encryption;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection con;
    private Statement stat;
    private String masterPassword;

    public Database(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public void createDatabase() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:Passwords");
        stat = con.createStatement();
        stat.execute("CREATE TABLE IF NOT EXISTS Passwords (Label TEXT, Password TEXT)");
        stat.execute("CREATE TABLE IF NOT EXISTS Master (Password TEXT)");
    }

    public void addPassword(Tuple<String> labelAndPassword) throws Exception {
        Tuple<String> encrypted = Encryption.encryptTuple(labelAndPassword, masterPassword);
        PreparedStatement search = con.prepareStatement("SELECT * FROM Passwords WHERE Label = ?");
        search.setString(1, encrypted.getLabel());
        ResultSet results = search.executeQuery();
        if (!results.next()) {
            PreparedStatement add = con.prepareStatement("INSERT INTO Passwords VALUES (?, ?)");
            add.setString(1, encrypted.getLabel());
            add.setString(2, encrypted.getPassword());
            add.execute();
        }
        results.close();
    }

    public ArrayList<Tuple<String>> getPasswords() throws SQLException, Exception {
        ArrayList<Tuple<String>> passwords = new ArrayList<Tuple<String>>();
        stat.execute("SELECT * FROM Passwords");
        ResultSet results = stat.getResultSet();
        while (results.next()) {
            Tuple<String> encrypted = new Tuple(results.getString("Label"), results.getString("Password"));
            passwords.add(Encryption.decryptTuple(encrypted, masterPassword));
        }
        return passwords;
    }

    public void editData(Tuple<String> oldLabelAndPassword, Tuple<String> newLabelAndPassword) throws Exception {
        oldLabelAndPassword = Encryption.encryptTuple(oldLabelAndPassword, masterPassword);
        newLabelAndPassword = Encryption.encryptTuple(newLabelAndPassword, masterPassword);
        PreparedStatement edit = con.prepareStatement("SELECT * FROM Passwords WHERE Label = ?");
        edit.setString(1, oldLabelAndPassword.getLabel());
        ResultSet results = edit.executeQuery();
        if (results.next()) {
            PreparedStatement update = con.prepareStatement("UPDATE Passwords SET Label = ?, Password = ? WHERE Label = ?");
            update.setString(1, newLabelAndPassword.getLabel());
            update.setString(2, newLabelAndPassword.getPassword());
            update.setString(3, oldLabelAndPassword.getLabel());
            update.execute();
        }
    }

    public void deletePassword(Tuple<String> labelAndPassword) throws Exception {
        labelAndPassword = Encryption.encryptTuple(labelAndPassword, masterPassword);
        PreparedStatement search = con.prepareStatement("SELECT * FROM Passwords WHERE Label = ?");
        search.setString(1, labelAndPassword.getLabel());
        ResultSet results = search.executeQuery();
        if (results.next()) {
            PreparedStatement delete = con.prepareStatement("DELETE FROM Passwords WHERE Label = ?");
            delete.setString(1, labelAndPassword.getLabel());
            delete.execute();
        }
        results.close();
    }
}