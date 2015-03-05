package de.jeha.fwj;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class DigestWithRIPEMD160 {

    public static void main(String... args) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());

        for (String message : new String[]{"aaa", "aab", "aac"}) {
            MessageDigest md = MessageDigest.getInstance("RIPEMD160");
            md.update(message.getBytes(Charsets.UTF_8));
            byte digest[] = md.digest();

            System.out.println(Hex.encodeHexString(digest));
        }
    }
}
