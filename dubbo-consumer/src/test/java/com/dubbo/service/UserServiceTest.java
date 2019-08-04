package com.dubbo.service;

import com.dubbo.model.User;
import com.dubbo.service.DemoService;
import com.dubbo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import test.BaseTest;

import javax.annotation.Resource;

/**
 * 消费者调用
 *
 * @author Trimer
 * @date 2019年08月03日
 */
public class UserServiceTest extends BaseTest {
    @Resource
    UserService userService;
    @Resource
    DemoService demoService;

    @Test
    public void getUserName() throws Exception {
        String userId = "1";
        User user = userService.getUser(userId);
        Assert.assertNotNull(user);
        System.out.println(demoService.sayHello(user.getUserName()));
        String userName = userService.getUserName(user.getUserId());
        Assert.assertEquals(userName, user.getUserName());
    }
}
