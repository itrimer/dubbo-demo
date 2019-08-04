package com.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 服务启动
 *
 * @author Trimer
 * @Description: 服务启动
 * @date 2019年08月03日
 */
public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class);
        logger.info(">>>>> provider-biz 正在启动 <<<<<");
        try {
            ClassPathXmlApplicationContext applicationContext =
                    new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
            logger.info(">>>>> provider-biz 启动完成 <<<<<");
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(">>>>> provider-biz 启动失败!! <<<<<", e);
        }
    }
}
