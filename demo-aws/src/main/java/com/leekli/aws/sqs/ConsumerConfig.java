package com.leekli.aws.sqs;

public class ConsumerConfig {
    private String queueName;
    private int consumerNum;
    public String getQueueName() {
        return queueName;
    }
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
    public int getConsumerNum() {
        return consumerNum;
    }
    public void setConsumerNum(int consumerNum) {
        this.consumerNum = consumerNum;
    }

    
    
}
