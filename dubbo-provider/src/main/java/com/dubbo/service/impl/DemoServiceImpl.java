package com.dubbo.service.impl;

import com.dubbo.service.DemoService;

/**
 * @author Trimer
 * @Description: DemoService接口的实现
 * @date 2019年08月03日
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String userName) {
        return "Hello " + userName;
    }
}
