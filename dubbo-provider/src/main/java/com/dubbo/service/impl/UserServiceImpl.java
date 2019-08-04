package com.dubbo.service.impl;

import com.dubbo.model.User;
import com.dubbo.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Trimer
 * @Description: UserService接口的实现
 * @date 2019年08月03日
 */
public class UserServiceImpl implements UserService {
    private static Map<String, User> userMap = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            String userId = String.valueOf(i);
            userMap.put(userId, new User(userId, "userName" + i));
        }
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    @Override
    public String getUserName(String userId) {
        User user = userMap.get(userId);
        return user == null ? null : user.getUserName();
    }
}
