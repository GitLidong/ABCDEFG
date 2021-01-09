
package com.support.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类，简化反射的调用，如果循环多次调用请谨慎调用
 *
 * @author liuhaitao
 */
@SuppressWarnings("unchecked")
public class ReflectUtil {

    /**
     * 对象方法调用
     *
     * @param target 调用目标对象
     * @param returnType 返回类型
     * @param method 方法名称
     * @param parameterTypes 方法参数类型
     * @param values 参数
     * @return 反射调用返回值
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> T callObjectMethod(Object target, Class<T> returnType, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends Object> clazz = target.getClass();
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return (T) declaredMethod.invoke(target, values);
    }

    public static <T> T callObjectMethod2(Object target, Class<T> returnType, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends Object> clazz = target.getClass();
        Method declaredMethod = clazz.getMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return (T) declaredMethod.invoke(target, values);
    }

    public static Object callObjectMethod(Object target, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends Object> clazz = target.getClass();
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        return declaredMethod.invoke(target, values);
    }

    /**
     * 使用getMethod而不是getDeclaredMethod<br>
     * getDeclaredMethod*()获取的是类自身声明的所有方法，包含public、protected和private方法<br>
     * getMethod*()获取的是类的所有共有方法，这就包括自身的所有public方法，和从基类继承的、从接口实现的所有public方法。
     */
    public static Object callObjectMethod2(Object target, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends Object> clazz = target.getClass();
        Method getMethod = clazz.getMethod(method, parameterTypes);
        return getMethod.invoke(target, values);
    }

    public static Object callObjectMethod(Object target, String method, Class<?> clazz, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(target, values);
    }

    /**
     * 静态方法调用
     *
     * @param clazz
     * @param returnType
     * @param method
     * @param parameterTypes
     * @param values
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static <T> T callStaticObjectMethod(Class<?> clazz, Class<T> returnType, String method, Class<?>[] parameterTypes,
            Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return (T) declaredMethod.invoke(null, values);
    }

    public static Object callStaticObjectMethod(Class<?> clazz, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(null, values);
    }

    /**
     * 对象设置值
     *
     * @param target
     * @param field
     * @param value
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setObjectField(Object target, String field, Object value) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> clazz = target.getClass();
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        declaredField.set(target, value);
    }

    public static void setObjectField(Object target, Class<?> clazz, String field, Object value) throws NoSuchFieldException,
            SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        declaredField.set(target, value);
    }

    /**
     * 对象获取值
     * getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段
     *
     * @param target
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getObjectField(Object target, String field) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> clazz = target.getClass();
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return declaredField.get(target);
    }

    /**
     * getField 只能获取public的，包括从父类继承来的字段
     * @param target
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getObjectField2(Object target, String field) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> clazz = target.getClass();
        Field declaredField = clazz.getField(field);
        declaredField.setAccessible(true);
        return declaredField.get(target);
    }

    public static <T> T getObjectField(Object target, String field, Class<T> returnType) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> clazz = target.getClass();
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return (T) declaredField.get(target);
    }

    public static Object getObjectField(Object target, Class<?> clazz, String field) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return declaredField.get(target);
    }

    /**
     * 静态变量设置值
     *
     * @param clazz
     * @param field
     * @param value
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void setStaticObjectField(Class<?> clazz, String field, Object value) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        declaredField.set(null, value);
    }

    /**
     * 静态变量获取值
     *
     * @param clazz
     * @param field
     * @return
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static Object getStaticObjectField(Class<?> clazz, String field) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return declaredField.get(null);
    }

    public static <T> T getStaticObjectField(Class<?> clazz, String field, Class<T> returnType) throws NoSuchFieldException,
            SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return (T) declaredField.get(null);
    }

    /**
     * 调动父类方法
     *
     * @param clazz　父类
     * @param target
     * @param method
     * @param parameterTypes
     * @param values
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object callAnyObjectMethod(Class<? extends Object> clazz, Object target, String method, Class<?>[] parameterTypes,
            Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(target, values);
    }

}
