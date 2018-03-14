package com.leekli.javase.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.math.RandomUtils;

/**
 * 4线程并发消费
 * 创建4个队列，存储并行任务，每组以token为key的数据hash后add到相应的队列中
 * 执行队列任务
 * 当发现队列内的任务数据非常不平衡时，停止向队列提交任务，等待执行结束后，重新hash
 * 
 * @author media-liwei
 *
 */
public class KinesisConsumer {

    public static void main(String[] args) {
        
     
        
        

        
        
    }
    
    void init(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(RandomUtils.nextInt(10));
        list1.add(RandomUtils.nextInt(10));
        list1.add(RandomUtils.nextInt(10));
        list1.add(RandomUtils.nextInt(10));
        List<Integer> list2 = new ArrayList<>();
        list2.add(RandomUtils.nextInt(10));
        list2.add(RandomUtils.nextInt(10));
        list2.add(RandomUtils.nextInt(10));
        list2.add(RandomUtils.nextInt(10));
        List<Integer> list3 = new ArrayList<>();
        list3.add(RandomUtils.nextInt(10));
        list3.add(RandomUtils.nextInt(10));
        list3.add(RandomUtils.nextInt(10));
        list3.add(RandomUtils.nextInt(10));
        List<Integer> list4 = new ArrayList<>();
        list4.add(RandomUtils.nextInt(10));
        list4.add(RandomUtils.nextInt(10));
        list4.add(RandomUtils.nextInt(10));
        list4.add(RandomUtils.nextInt(10));
        List<Integer> list5 = new ArrayList<>();
        list5.add(RandomUtils.nextInt(10));
        list5.add(RandomUtils.nextInt(10));
        list5.add(RandomUtils.nextInt(10));
        list5.add(RandomUtils.nextInt(10));
        List<Integer> list6 = new ArrayList<>();
        list6.add(RandomUtils.nextInt(10));
        list6.add(RandomUtils.nextInt(10));
        list6.add(RandomUtils.nextInt(10));
        list6.add(RandomUtils.nextInt(10));
        
    }
}
