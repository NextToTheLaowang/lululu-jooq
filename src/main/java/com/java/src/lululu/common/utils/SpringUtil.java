package com.java.src.lululu.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Created by jimmy on 2017/2/17.
 */
@Component
@Slf4j
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }


    public static String messageSource(String code) {
        String temp = "";
        try {
            temp = SpringUtil.getBean(MessageSource.class).getMessage(code, null, null);
        } catch (Exception e) {
//            log.error("找不到对应的 messageSource ", e);
            temp = code;
        }
        return temp;
    }

    public static String messageSensitive(String code) {
        String temp = "";
        try {
            temp = SpringUtil.getBean(MessageSource.class).getMessage(code, null, null);
        } catch (Exception e) {
//            log.error("找不到对应的 messageSource ", e);
            temp = code;
        }
        return temp;
    }

    /**
     * 获取messageSource的消息
     *
     * @param code
     * @return
     */
    public static String messageSource(String code, Object[] objects) {
        String temp = "";
        try {
            temp = SpringUtil.getBean(MessageSource.class).getMessage(code, objects, null);
        } catch (Exception e) {
//            log.error("找不到对应的 messageSource ", e);
            temp = code;
        }
        return temp;
    }
}
