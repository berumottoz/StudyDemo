package com.example.dubbo.zookeeper.consumer;

import com.example.dubbo.zookeeper.service.ProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboConsumerTest {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo.zookeeper/consumer.xml");
        context.start();
        ProviderService providerService = (ProviderService) context.getBean("providerService");
        String str = providerService.SayHello("hello");
        System.out.println(str);
        System.in.read();

    }
}
