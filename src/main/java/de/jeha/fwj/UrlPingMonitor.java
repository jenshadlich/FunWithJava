package de.jeha.fwj;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class UrlPingMonitor implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(UrlPingMonitor.class);
    private final String url;

    private final static int THREAD_POOL_SIZE = 8;
    private final static ExecutorService EXECUTOR_SERVICE;

    static {
        ThreadFactory threadFactory = new BasicThreadFactory.Builder()
                .namingPattern("worker-%02d")
                .priority(Thread.MAX_PRIORITY)
                .build();
        EXECUTOR_SERVICE = Executors.newFixedThreadPool(THREAD_POOL_SIZE, threadFactory);
    }

    public UrlPingMonitor(String url) {
        this.url = url;
    }

    public static void main(String... args) throws IOException, InterruptedException {
        for (int i = 0; i < 180; i++) {
            EXECUTOR_SERVICE.submit(new UrlPingMonitor("http://hasenfarm.spreadshirt.de"));

            LOG.info("sleep 10 seconds");
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        }

        EXECUTOR_SERVICE.shutdown();
        EXECUTOR_SERVICE.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }

    public void run() {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(url + "/?" + System.currentTimeMillis());
        //HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //LOG.info(response.getStatusLine().toString());
        //LOG.info(response.getFirstHeader("X-Server-Name").getValue());

        stopWatch.stop();

        //LOG.info("tt = {}", String.format(Locale.ENGLISH, "%.1f", (stopWatch.getTime() / 1000.0)));
        System.out.println(response.getFirstHeader("X-Server-Name").getValue()
                        + "\t"
                        + response.getStatusLine().getStatusCode()
                        + "\t"
                        + String.format(Locale.ENGLISH, "%.1f", (stopWatch.getTime() / 1000.0))
        );

        httpGet.releaseConnection();
    }

}