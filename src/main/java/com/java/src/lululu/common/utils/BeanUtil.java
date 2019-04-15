package com.java.src.lululu.common.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 对象copy
 * Created by jimmy on 16/8/14.
 */
public class BeanUtil {
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = Sets.newHashSet();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * copy 对象属性, 忽列属性为NULL,
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));

    }

    /**
     * copy 数组
     *
     * @param src
     * @param classz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List copyListPropertiesIgnoreNull(List<?> src, Class<?> classz) {
        List list = Lists.newArrayList();
        if (Collections3.isEmpty(src)) {
            return list;
        }
        src.forEach(object -> {
            Object instantiate = BeanUtils.instantiate(classz);
            copyPropertiesIgnoreNull(object, instantiate);
            list.add(instantiate);
        });
        return list;
    }

    /**
     * Bean转Map<String, String>, 忽略null值
     *
     * @param source
     * @param target
     */
    public static void copyBeanTransToMap(Object source, Map<String, String> target) throws IllegalAccessException {
        if (source == null || target == null) {
            return;
        }
        Class souceClass = source.getClass();
        do {
            for (Field field : souceClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(source);
                if (value == null) {
                    continue;
                }
                target.put(field.getName(), String.valueOf(value));
            }
            souceClass = souceClass.getSuperclass();
        } while (!souceClass.equals(Object.class));
    }

    /**
     * Bean转Map<String, String>,
     *
     * @param source
     * @param
     */
    public static Map<String, String> copyBeanToMap(Object source) {
        Map<String, String> target = Maps.newHashMap();
        try {
            Class souceClass = source.getClass();
            do {
                for (Field field : souceClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = field.get(source);
                    if (value == null) {
                        target.put(field.getName(), "");
                    } else {
                        target.put(field.getName(), String.valueOf(value));
                    }

                }
                souceClass = souceClass.getSuperclass();
            } while (!souceClass.equals(Object.class));
            return target;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }


    public static void transMap2Bean(Map<String, Object> map, Object obj) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            foeachPropertys(map, obj, propertyDescriptors);
        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }
        return;

    }

    private static void foeachPropertys(Map map, Object obj,
                                        PropertyDescriptor[] propertyDescriptors) throws IllegalAccessException, InvocationTargetException {
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (map.containsKey(key)) {
                Object value = map.get(key);
                // 得到property对应的setter方法
                Method setter = property.getWriteMethod();
                setter.invoke(obj, value);
            }

        }
    }

    public static Object transMap2Bean(Map<String, String> map, Class classz) {
        try {
            Object obj = classz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(classz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            foeachPropertys(map, obj, propertyDescriptors);
            return obj;
        } catch (Exception e) {
            System.out.println("transMap2Bean Error " + e);
        }
        return null;

    }


//    public static void main(String[] args) {
//
//        // System.out.println(DateTools.getCurrentDateTime());
//    }
}