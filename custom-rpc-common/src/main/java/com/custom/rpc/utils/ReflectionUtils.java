package com.custom.rpc.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReflectionUtils
 * @Description 反射工具类
 * @Author peco
 * @Date 2022/9/26 14:47
 */
public class ReflectionUtils {

    /**
     * 创建对象
     * @param clazz 类
     * @return
     */
    public static <T> T newInstance(Class<T> clazz){
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    /**
     * 获取class 的公共方法
     * @param clazz 类
     * @return
     */
    public static Method[] getPublicMethods(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> pmethods = new ArrayList<>();
        for (Method method : methods) {
            if (Modifier.isPublic(method.getModifiers())){
              pmethods.add(method);
            }

        }
        return pmethods.toArray(new Method[0]);

    }

    /**
     * 调用方法
     * @param target 目标对象
     * @param method 方法
     * @param args 参数
     */
    public static Object invoke(Object target, Method method, Object... args) {
        try {
            return method.invoke(target, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
