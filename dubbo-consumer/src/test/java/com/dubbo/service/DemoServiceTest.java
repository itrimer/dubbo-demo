package com.dubbo.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dubbo.service.DemoService;
import test.BaseTest;

import javax.annotation.Resource;

/**
 * 消费者调用
 *
 * @author Trimer
 * @date 2019年08月03日
 */
public class DemoServiceTest extends BaseTest {
    @Resource
    DemoService demoService;

    @Test
    public void sayHello() {
        String userName = "Jay";
        String result = demoService.sayHello(userName);
        System.out.println(result);
    }
}
