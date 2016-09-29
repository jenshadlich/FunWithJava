package de.jeha.fwj;

import java.io.UnsupportedEncodingException;

/**
 * @author jenshadlich@googlemail.com
 */
public class UTF8 {

    public static void main(String ... args) throws UnsupportedEncodingException {
        String oldString = "\uD800";
        String newString = new String(oldString.getBytes("UTF-8"), "UTF-8");

        System.out.println(newString.equals(oldString));
        System.out.println(oldString);
        System.out.println(newString);
    }
}
