package de.jeha.fwj;

import org.apache.commons.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author jenshadlich@googlemail.com
 */
public class UUIDBase64 {

    public static void main(String... args) {
        final UUID uuid = UUID.randomUUID();

        System.out.println(uuid.toString());
        System.out.println(uuidToBase64(uuid));
    }

    private static String uuidToBase64(UUID uuid) {
        final ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return Base64.encodeBase64URLSafeString(bb.array());
    }

}
