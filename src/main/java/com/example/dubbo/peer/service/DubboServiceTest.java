package com.example.dubbo.peer.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class DubboServiceTest {
    public static void main( String[] args ) throws IOException {
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/provider.xml");
        context.start();
        System.in.read(); // 按任意键退出
    }

}
