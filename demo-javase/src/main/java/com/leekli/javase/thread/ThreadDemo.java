package com.leekli.javase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {

    
    
    public static void main(String[] args) {
        
        Semaphore semp = new Semaphore(5);
        
        ExecutorService es = Executors.newFixedThreadPool(200);
        
        
        
        AtomicBoolean run = new AtomicBoolean(true);
        AtomicInteger  count = new AtomicInteger(1);
        
        
        es.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                
                while(true){
                    try {
                        semp.acquire(); 
                        System.out.println(111);  
                        if( isEmpty() ){
                            break;
                        }
                    } catch (Exception e) {
                        
                    }finally{
                        semp.release();  
                    }
                }
                
                
                return null;
            }
            
            private boolean isEmpty() {
                
                return false;
            }

            }
        );
        
        
    }


 
}
