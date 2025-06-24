//Anthony Mozloom
package edu.elon.amozloom.Project1_Mozloom.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

@Configuration
public class AWSConfig {

    @Value("${aws_access_key_id}")
    private String accessKey;

    @Value("${aws_secret_access_key}")
    private String secretKey;

    @Value("${aws_session_token}")
    private String sessionToken;

    @Value("${aws_region}")
    private String region;

    @Bean
    public Ec2Client ec2Client() {
        AwsSessionCredentials credentials = AwsSessionCredentials.create(accessKey, secretKey, sessionToken);

        return Ec2Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}