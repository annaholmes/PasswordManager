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
    }

    public void addPassword(Tuple<String> labelAndPassword) throws Exception {
        Tuple<String> encrypted = Encryption.encryptTuple(labelAndPassword, masterPassword);
        stat.execute("INSERT INTO Passwords VALUES ('" + encrypted.getFirst() + ", '" + encrypted.getSecond() + "')");
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
        stat.execute("SELECT * FROM Passwords WHERE Label = '" + oldLabelAndPassword.getFirst() + "'");
        ResultSet results = stat.getResultSet();
        if (results.next()) {
        	stat.execute("UPDATE Passwords SET Label = '" + newLabelAndPassword.getFirst() + "', Password = '" + newLabelAndPassword.getSecond() + "' WHERE Label = '" + oldLabelAndPassword.getFirst() + "'");
        }
    }

    public void deletePassword(Tuple<String> labelAndPassword) throws Exception {
    	labelAndPassword = Encryption.encryptTuple(labelAndPassword, masterPassword);
    	stat.execute("SELECT * FROM Passwords WHERE Label = '" + labelAndPassword.getFirst() + "'");
    	ResultSet results = stat.getResultSet();
    	if (results.next()) {
    		stat.execute("DELETE FROM Passwords WHERE Label = '" + labelAndPassword.getFirst() + "'");
    	}
    	results.close();
    }
}
