package com.leekli.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Demo implements Callable<Void>{

	@Override
	public Void call() throws Exception {
		System.out.println("2222222222222");
		Thread.sleep(10000);
		System.out.println("dddd");
		return null;
	}
	
	public static void main(String[] args) {
		Demo de = new Demo();
		
		ExecutorService es = Executors.newSingleThreadExecutor();
		
		Future<Void> f = es.submit(de);
		
		try {
			f.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
		while(f.isDone()){
			System.out.println("aaaaa");
		}
	}

	
}
