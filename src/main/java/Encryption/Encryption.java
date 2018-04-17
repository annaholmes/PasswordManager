package Encryption;

import Unsorted.Tuple;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static IvParameterSpec ivspec = new IvParameterSpec(iv);
    public static Tuple<String> encrypt(Tuple<String> tuple, String masterPassword) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InvalidAlgorithmParameterException {



        //Thing
        String combined = tuple.getSecond();
        SecretKeySpec secretKey = new SecretKeySpec(masterPassword.getBytes(), "AES");
        System.out.println(secretKey.getAlgorithm());
        System.out.println(secretKey);
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivspec); //Illegal key size
        Tuple<String> output = new Tuple<>();
        String username = cipher.doFinal(tuple.getFirst().getBytes("UTF-8")).toString();
        String password = cipher.doFinal(tuple.getSecond().getBytes("UTF-8")).toString();
        output.setFirst(username);
        output.setSecond(password);
        return output;

    }
    public static Tuple<String> decrypt(Tuple<String> tuple, String masterPassword) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, InvalidAlgorithmParameterException {


        String username = tuple.getFirst();
        String password = tuple.getSecond();

        SecretKeySpec secretKey = new SecretKeySpec(masterPassword.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey,ivspec);
        username = cipher.doFinal(username.getBytes("UTF-8")).toString();
        password = cipher.doFinal(password.getBytes("UTF-8")).toString();
        Tuple output = new Tuple();
        output.setFirst(username);
        output.setSecond(password);
        return output;

    }
}
