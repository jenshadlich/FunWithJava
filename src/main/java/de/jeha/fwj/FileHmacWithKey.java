package de.jeha.fwj;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author jenshadlich@googlemail.com
 */
public class FileHmacWithKey {

    public static void main(String... args) throws NoSuchAlgorithmException, IOException, InvalidKeyException {

        FileInputStream fis = new FileInputStream("./src/main/java/de/jeha/fwj/FileHmacWithKey.java");
        byte[] buffer = new byte[4096];

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKey = keyGenerator.generateKey();

        Mac m = Mac.getInstance("HmacSHA1");
        m.init(secretKey);

        int n;
        while ((n = fis.read(buffer)) != -1) {
            m.update(buffer, 0, n);
        }
        byte[] mac = m.doFinal();


        System.out.println(Hex.encodeHexString(mac));
    }
}
