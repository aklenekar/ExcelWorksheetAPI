package com.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Component
//@PropertySource("classpath:application.properties")
public class AWSUtil {

	//@Value("${aws.accessKey}")
	private String accessKey;
	
	//@Value("${aws.secretKey}")
	private String secretKey;
	
	//@Value("${aws.bucketName}")
	public String bucketName;
	
	public AmazonS3 getS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withClientConfiguration(new ClientConfiguration()
						.withMaxConnections(100)
						.withConnectionTimeout(120 * 1000)
						.withMaxErrorRetry(10))
				.withRegion(Regions.US_EAST_2).build();
	}
}
