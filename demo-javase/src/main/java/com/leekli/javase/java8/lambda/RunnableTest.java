package com.leekli.javase.java8.lambda;
/**
 * http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html#section2
 * @author media-liwei
 *
 *  注意：没有传递和返回参数
 */

/**
 * 
 * Runnable Lambda
 * 
 * @author media-liwei
 *
 */
public class RunnableTest {
    public static void main(String[] args) {
        System.out.println("=== RunnableTest ===");
        String msg = "hello world";
        
     //注意：没有传递和返回参数
        
     // Anonymous Runnable
     Runnable r1 = new Runnable(){
        @Override
        public void run() {
            System.out.println("Hello world one!");
        }
     };
     
     // Lambda Runnable
     Runnable r2 = () -> System.out.println("Hello world two!");

     
     Runnable r3 = () -> RunnableTest.print(msg);
     r1.run();
     r2.run();
     r3.run();
    }
    
    public static void print(final String msg){
        System.out.println("print:" + msg);
        
        
    }
    
}
