package org.dubbo.server.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;


public class DubboServer {
	
	public static void main(String[] args) throws Exception {
		CountDownLatch cnl = new CountDownLatch(1);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});  
        context.start();  
        ExportService exportService = context.getBean("exportService", ExportService.class);
        exportService.export();
        System.out.println("provider started!");
        cnl.await();
        //System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }  
}
