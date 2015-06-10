package de.jeha.fwj;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.net.whois.WhoisClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jenshadlich@googlemail.com
 */
public class Whois {

    public static void main(String... args) throws IOException {
        WhoisClient whoisClient = new WhoisClient();
        whoisClient.connect(WhoisClient.DEFAULT_HOST); // .com .net .edu
        //whoisClient.connect("whois.denic.de");

        String whois = whoisClient.query("spreadshirt.com");

        WhoisRecord record = parse(whois);

        System.out.println(whois);
        System.out.println(record);

        whoisClient.disconnect();
    }

    public static WhoisRecord parse(String whois) {
        Scanner scanner = new Scanner(whois);

        WhoisRecord record = new WhoisRecord();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            String[] parts = line.split(":");
            if (parts.length == 2) {
                final String key = parts[0].trim();
                final String value = parts[1].trim();
                switch (key) {
                    case "Server Name":
                        record.serverName = value;
                        break;
                    case "Domain Name":
                        record.domainName = value;
                        break;
                    case "Registrar":
                        record.registrar = value;
                        break;
                    case "Name Server":
                        record.nameServer.add(value);
                        break;
                    case "Sponsoring Registrar IANA ID":
                    case "Whois Server":
                    case "Referral URL":
                    case "Status:":
                    case "Updated Date":
                    case "Creation Date":
                    case "Expiration Date":
                        break;


                    default:
                        break;
                }
            }
        }

        return record;
    }

    public static class WhoisRecord {
        String serverName;
        String domainName;
        String registrar;
        List<String> nameServer = new ArrayList<>();

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
        }
    }

}
