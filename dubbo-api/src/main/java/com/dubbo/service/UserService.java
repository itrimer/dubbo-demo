package com.dubbo.service;

import com.dubbo.model.User;

/**
 * UserService
 *
 * @author trimer
 * @date 2019/08/03
 */
public interface UserService {

    /**
     * 根据用户id获取用户
     *
     * @param userId
     * @return
     */
    User getUser(String userId) throws Exception;

    /**
     * 根据用户id获取用户名
     *
     * @param userId
     * @return
     */
    String getUserName(String userId) throws Exception;
}
