package Unsorted;
import Encryption.Encryption;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Database {
	private static Connection con;
	private static Statement stat;

    public static String getMasterPassword() {
        return masterPassword;
    }

    private static String masterPassword;

	public Database() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:Passwords");
    }
	
    public static void createTables() throws SQLException, ClassNotFoundException {
        stat = con.createStatement();
        stat.execute("CREATE TABLE IF NOT EXISTS Passwords (Label TEXT, Password TEXT)");
        stat.execute("CREATE TABLE IF NOT EXISTS Master (Password TEXT, Tries INTEGER, Next Attempt TIMESTAMP)");
    }
    
    public static void setMasterPassword(String password) {
    	masterPassword = password;
    }
    
    public static void setMasterTable(int tries) throws SQLException {
    	PreparedStatement add = con.prepareStatement("INSERT INTO Master VALUES (?, ?, ?)");
    	add.setString(1, masterPassword);
    	add.setInt(2, tries);
    	Calendar calendar = Calendar.getInstance();
    	add.setTimestamp(3, new java.sql.Timestamp(calendar.getTimeInMillis()));
    	add.execute();
    }
    
    public static int attemptedTries() throws SQLException {
    	stat.execute("SELECT Tries FROM Master");
    	ResultSet results = stat.getResultSet();
    	int tries = results.getInt(1);
    	results.close();
    	return tries;
    }
    
    public static void triesMaxed() throws SQLException {
    	PreparedStatement update = con.prepareStatement("UPDATE Master SET Tries = ?, Next Attempt = ?");
    	update.setInt(1, 0);
    	Calendar calendar = Calendar.getInstance();
    	calendar.add(30, Calendar.MINUTE);
    	update.setTimestamp(2, new java.sql.Timestamp(calendar.getTimeInMillis()));
    	update.execute();
    }
    
    public static LocalDateTime nextLoginTime() throws SQLException {
    	stat.execute("SELECT Next Attempt FROM Master");
    	ResultSet results = stat.getResultSet();
    	Timestamp date = results.getTimestamp(1);
    	results.close();
    	return date.toLocalDateTime();
    }

    public static void addPassword(Tuple<String> labelAndPassword) throws Exception {
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

    public static ArrayList<Tuple<String>> getAllPasswords() throws SQLException, Exception {
        ArrayList<Tuple<String>> passwords = new ArrayList<Tuple<String>>();
        stat.execute("SELECT * FROM Passwords");
        ResultSet results = stat.getResultSet();
        while (results.next()) {
            Tuple<String> encrypted = new Tuple(results.getString("Label"), results.getString("Password"));
            passwords.add(Encryption.decryptTuple(encrypted, masterPassword));
        }
        return passwords;
    }
    
    public static ArrayList<Tuple<String>> searchPasswords(String label) throws Exception {
    	ArrayList<Tuple<String>> passwords = new ArrayList<Tuple<String>>();
    	PreparedStatement search = con.prepareStatement("SELECT * FROM Passwords WHERE Label = ?");
    	search.setString(1, label);
        ResultSet results = search.executeQuery();
        while (results.next()) {
            Tuple<String> encrypted = new Tuple(results.getString("Label"), results.getString("Password"));
        	passwords.add(Encryption.decryptTuple(encrypted, masterPassword));
        }
        return passwords;
    }

    public static void editData(Tuple<String> oldLabelAndPassword, Tuple<String> newLabelAndPassword) throws Exception {
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

    public static void deletePassword(Tuple<String> labelAndPassword) throws Exception {
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
    
    public static void dropDatabase() throws SQLException {
    	stat.execute("DROP DATABASE Passwords");
    	stat.close();
    	con.close();
    }
}