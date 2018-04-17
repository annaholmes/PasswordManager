package Encryption;

import Unsorted.Tuple;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static final String ALGORITHM = "AES";

    public static Tuple<String> encrypt(Tuple<String> tuple, String masterPassword) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String combined = tuple.getSecond();
        SecretKeySpec secretKey = new SecretKeySpec(masterPassword.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        Tuple<String> output = new Tuple<>();
        String username = cipher.doFinal(tuple.getFirst().getBytes()).toString();
        String password = cipher.doFinal(tuple.getSecond().getBytes()).toString();
        output.setFirst(username);
        output.setSecond(password);
        return output;

    }
    public static Tuple<String> decrypt(Tuple<String> tuple, String masterPassword) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        String username = tuple.getFirst();
        String password = tuple.getSecond();

        SecretKeySpec secretKey = new SecretKeySpec(masterPassword, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        username = cipher.doFinal(username.getBytes()).toString();
        password = cipher.doFinal(password.getBytes()).toString();
        Tuple output = new Tuple();
        output.setFirst(username);
        output.setSecond(password);
        return output;

    }
}
