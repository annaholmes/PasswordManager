import java.util.ArrayList;

public class Database {

    public Database(){

    }

    public void addPassword(Tuple<String> labelAndPassword) {
        // TODO add new passwords
        // encrypt password
        // store in database
    }

    public ArrayList<Tuple<String>> getPasswords() {
        // TODO get all passwords
        return null;
    }

    public void editData(Tuple<String> oldLabelAndPassword, Tuple<String> newLabelAndPassword) {
        // TODO remove old label & add new label
        //
    }

    public void deletePassword(Tuple<String> labelAndPassword) {
        // TODO delete old password
    }
}
