package com.dubbo.service;

import com.dubbo.model.User;
import org.junit.Assert;
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
public class UserServiceTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    @Resource
    UserService userService;
    @Resource
    DemoService demoService;

    @Test
    public void getUserName() throws Exception {
        String userId = "1";
        User user = userService.getUser(userId);
        Assert.assertNotNull(user);
        logger.info(demoService.sayHello(user.getUserName()));
        String userName = userService.getUserName(user.getUserId());
        Assert.assertEquals(userName, user.getUserName());
    }
}
