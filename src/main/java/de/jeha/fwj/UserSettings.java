package de.jeha.fwj;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jenshadlich@googlemail.com
 */
public class UserSettings {

    private static final Logger LOG = LoggerFactory.getLogger(UrlPingMonitor.class);

    public static Map<String, String> read(String context) throws IOException {
        Map<String, String> settings = new HashMap<>();

        final String userHome = System.getProperty("user.home");
        LOG.info("user.home = '{}'", userHome);

        final String cfgFileName = userHome + File.separator + "." + context + ".cfg";
        final File cfgFile = new File(cfgFileName);

        if (cfgFile.exists()) {
            for (String line : FileUtils.readLines(cfgFile)) {
                String parts[] = StringUtils.split(line.trim(), "=", 2);
                if (parts.length != 2) {
                    LOG.warn("Ignore line '{}', invalid format", line);
                    continue;
                }
                final String key = parts[0];
                final String value = parts[1];
                settings.put(key, value);
                LOG.info("found setting for key ='{}', value = '{}'", key, value);
            }
        } else {
            LOG.warn("File '{}' does not exist", cfgFileName);
        }

        return settings;
    }

    public static void main(String... args) throws IOException {
        read("s3pt");
    }

}
