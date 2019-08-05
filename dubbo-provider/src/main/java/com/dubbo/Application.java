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
        logger.info(">>>>> dubbo-provider starting <<<<<");
        try {
            String configLocation = "classpath:applicationContext.xml";
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
            logger.info(">>>>> dubbo-provider started <<<<<");
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            logger.error(">>>>> dubbo-provider start failed!! <<<<<", e);
        }
    }
}
