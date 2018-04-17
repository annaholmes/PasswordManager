package Encryption;

import Unsorted.Tuple;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptionTest {
    @Test
    public void TestEncryption() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, UnsupportedEncodingException, InvalidAlgorithmParameterException {

        Tuple<String> user = new Tuple<>();
        user.setFirst("Hello");
        user.setSecond("World");
        Tuple<String> encrypted = Encryption.encrypt(user, "A948AB9C57030E9FF7035EFFD4071DAA");
        Tuple<String> decrypted = Encryption.decrypt(encrypted, "A948AB9C57030E9FF7035EFFD4071DAA");
        Assert.assertTrue(decrypted.getFirst().equals("Hello"));


    }
}
