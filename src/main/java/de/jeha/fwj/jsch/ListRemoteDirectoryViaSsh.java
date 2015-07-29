package de.jeha.fwj.jsch;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author jenshadlich@googlemail.com
 */
public class ListRemoteDirectoryViaSsh {

    public static void main(String... args) throws JSchException, IOException {

        Scanner cmdLineScanner = new Scanner(System.in);

        System.out.println("Enter host:");
        String host = cmdLineScanner.nextLine();

        System.out.println("Enter user:");
        String user = cmdLineScanner.nextLine();

        System.out.println("Enter password:");
        String password = cmdLineScanner.nextLine();

        JSch jsch = new JSch();
        jsch.setKnownHosts("/Users/" + user + "/.ssh/known_hosts");
        jsch.addIdentity("/Users/" + user + "/.ssh/id_dsa", password);

        Session session = jsch.getSession(user, host, 22);

        session.connect(1000);

        Channel channel = session.openChannel("exec");
        ChannelExec.class.cast(channel).setCommand("ls -las");

        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();

        channel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
                System.out.print(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) {
                    continue;
                }
                System.out.println("exit-status: " + channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        channel.disconnect();
        session.disconnect();
    }

}
