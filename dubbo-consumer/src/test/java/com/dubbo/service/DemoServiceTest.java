package com.dubbo.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import test.BaseTest;

import javax.annotation.Resource;

/**
 * 消费者调用
 *
 * @author Trimer
 * @date 2019年08月03日
 */
public class DemoServiceTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DemoServiceTest.class);
    @Resource
    DemoService demoService;

    @Test
    public void sayHello() {
        String userName = "Jay";
        String result = demoService.sayHello(userName);
        logger.info(result);
    }
}
