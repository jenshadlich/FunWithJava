package de.jeha.fwj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.Security;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DnsCache {

    public static void main(String[] args) {

        final String hostname = "www.spreadshirt.com";
        final int maxLookups = 1000;
        final long intervalSeconds = 2;

        // set to "no cache"
        java.security.Security.setProperty("networkaddress.cache.ttl", "0");
        java.security.Security.setProperty("networkaddress.cache.negative.ttl", "0");
        System.setProperty("networkaddress.cache.ttl", "0");
        System.setProperty("networkaddress.cache.negative.ttl", "0");

        System.out.println(Security.getProperty("networkaddress.cache.ttl"));
        System.out.println(System.getProperty("networkaddress.cache.ttl"));
        System.out.println(Security.getProperty("networkaddress.cache.negative.ttl"));
        System.out.println(System.getProperty("networkaddress.cache.negative.ttl"));
        System.out.println();

        for (int i = 0; i < maxLookups; i++) {
            try {
                makeRequest(hostname, false);
                System.out.println(new Date());
                InetAddress inetAddress = InetAddress.getByName(hostname);

                System.out.println("Host:\t\t\t\t\t" + hostname);
                System.out.println("Canonical host name:\t" + inetAddress.getCanonicalHostName());
                System.out.println("Host name:\t\t\t\t" + inetAddress.getHostName());
                System.out.println("Host address:\t\t\t" + inetAddress.getHostAddress());
                System.out.println();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(intervalSeconds));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void makeRequest(String hostname, boolean printContent) {
        try {
            final URL url = new URL("http://" + hostname + "/");

            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String res;

            while ((res = reader.readLine()) != null) {
                if (printContent) {
                    System.out.println(res);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}