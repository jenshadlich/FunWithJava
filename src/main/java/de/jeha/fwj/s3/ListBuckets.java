package de.jeha.fwj.s3;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 *
 * Tested with a local minio server.
 */
public class ListBuckets {

    public static void main(String[] args) throws IOException {

        final AWSCredentials credentials = new BasicAWSCredentials("39409N89UL22IS1HSOTC", "mNSTRF2vOTpkvgXkR68LSFl0sSAF8k130bLE70sY");

        final ClientConfiguration clientConfiguration = new ClientConfiguration()
                .withProtocol(Protocol.HTTP)
                .withUserAgent("list-bucket")
                .withSignerOverride("AWSS3V4SignerType");

        AmazonS3 s3Client = new AmazonS3Client(credentials, clientConfiguration);

        s3Client.setEndpoint("http://localhost:9000");

        s3Client.setS3ClientOptions(new S3ClientOptions().withPathStyleAccess(true));

        s3Client.listBuckets().forEach((bucket) -> {
            System.out.println(bucket.getName());
        });
    }

}