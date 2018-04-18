package Encryption;

import Unsorted.Tuple;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class Encryption {
    //Modified code from https://gist.github.com/SimoneStefani/99052e8ce0550eb7725ca8681e4225c5
    private static final String ALGO = "AES";
    public static Tuple<String> encryptTuple(Tuple<String> tuple, String masterPassword) throws Exception {



        //Thing
        String username = tuple.getFirst();
        String password = tuple.getSecond();

        username = encrypt(username, masterPassword);
        password = encrypt(password,masterPassword);

        Tuple output = new Tuple();

        output.setFirst(username);
        output.setSecond(password);
        //Hopefully this will GC our passwords so someone can't just read our memory as easily.
        return output;

    }
    public static Tuple<String> decryptTuple(Tuple<String> tuple, String masterPassword) throws Exception {


        String username = tuple.getFirst();
        String password = tuple.getSecond();

        username = decrypt(username, masterPassword);
        password = decrypt(password, masterPassword);

        Tuple output = new Tuple();

        output.setFirst(username);
        output.setSecond(password);
        return output;

    }
    public static String encrypt(String data, String masterPassword) throws Exception {
        Key key = generateKey(masterPassword);
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(data.getBytes());
        System.gc();
        return Base64.getEncoder().encodeToString(encVal);
    }
    public static String decrypt(String encryptedData,String masterPassword) throws Exception {
        try {
            Key key = generateKey(masterPassword);
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            System.gc();
            return new String(decValue);
        }catch (BadPaddingException e){
            throw new WrongPasswordException();
        }
    }

    private static Key generateKey(String masterPassword) throws Exception {
        byte[] keyValue = masterPassword.getBytes();
        System.gc();
        return new SecretKeySpec(keyValue, ALGO);
    }
}
