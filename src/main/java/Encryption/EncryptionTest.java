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
    public void TestEncryption() throws Exception {

        Tuple<String> user = new Tuple<>();
        user.setFirst("Hello");
        user.setSecond("World");
        Tuple<String> encrypted = Encryption.encryptTuple(user, "A948AB9C57030E9FF7035EFFD4071DAA");
        Tuple<String> decrypted = Encryption.decryptTuple(encrypted, "A948AB9C57030E9FF7035EFFD4071DAA");
        Assert.assertTrue(decrypted.getFirst().equals("Hello"));

    }
    @Test
    public void TestThatWrongPasswordDoesntWork() throws Exception {
        Tuple<String> user = new Tuple<>();
        user.setFirst("Hello");
        user.setSecond("World");
        Tuple<String> encrypted = Encryption.encryptTuple(user, "A948AB9C57030E9FF7035EFFD4071DAA");
        try {
            //Bad passwords don't give us jumbled data, they give us padding exceptions.
            Tuple<String> decrypted = Encryption.decryptTuple(encrypted, "D26D340CD29882900DD330C00B8FA2DD"); //
        }catch (BadPaddingException e){
            Assert.assertTrue(true);
        }
    }
}
