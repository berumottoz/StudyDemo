package com.example.dubbo.zookeeper.service;

/**
 * xml方式服务提供者实现类
 */

public class ProviderServiceImpl implements ProviderService {
    public String SayHello(String word) {
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        word = word.concat(" Zookeeper!!!");
        return word;
    }
}
