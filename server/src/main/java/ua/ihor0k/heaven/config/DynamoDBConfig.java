package ua.ihor0k.heaven.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

//    @Bean
//    public DynamoDBMapper dynamoDBMapper() {
//        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
//        return new DynamoDBMapper(client);
//    }
}
