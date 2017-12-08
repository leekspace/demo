package com.leekli.aws.sqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.ClientConfigurationFactory;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
/**
 * 
 * @author media-liwei
 *
 */
public class AmazonSQSConfig {    

    private Logger LOGGER = LoggerFactory.getLogger(AmazonSQSConfig.class);
    private String region;

    private AmazonSQS cofnig() throws Exception {
        LOGGER.info("AWS_SQS_REGION:"+region);
        return AmazonSQSClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(region)
                .withClientConfiguration(getClientConfig(new ClientConfigurationFactory().getConfig()))
                .build();
    }

    private ClientConfiguration getClientConfig(ClientConfiguration config) {
        LOGGER.info("AmazonSQS ClientConfiguration:{}",JSONObject.toJSONString(config));
        return config;
    }
}
