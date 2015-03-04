package de.jeha.fwj;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class DigestRIPEMD160 {
    public static void main(String... args) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());

        String message = "abc";
        MessageDigest md = MessageDigest.getInstance("RIPEMD160");
        md.update(message.getBytes(Charset.forName("UTF-8")));
        byte digest[] = md.digest();

        System.out.println(Hex.encodeHexString(digest));
    }
}
