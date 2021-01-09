
package com.support.util;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;

/**
 * 最终调用ReflectUtil
 */
public class ReflectNothrowsUtil {

    private static final String NO_SUCH_FIELD_EXCEPTION = "NoSuchFieldException";
    private static final String INVOCATION_TARGET_EXCEPTION = "InvocationTargetException";
    private static final String ILLEGAL_ARGUMENT_EXCEPTION = "IllegalArgumentException";
    private static final String ILLEGAL_ACCESS_EXCEPTION = "IllegalAccessException";
    private static final String SECURITY_EXCEPTION = "SecurityException";
    private static final String NO_SUCH_METHOD_EXCEPTION = "NoSuchMethodException";

    public static <T> T callObjectMethod(String tag, Object target, Class<T> returnType, String method, Class<?>[] parameterTypes,
            Object... values) {
        try {
            return ReflectUtil.callObjectMethod(target, returnType, method, parameterTypes, values);
        } catch (NoSuchMethodException e) {
            Log.e(tag, NO_SUCH_METHOD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (InvocationTargetException e) {
            Log.e(tag, INVOCATION_TARGET_EXCEPTION, e);
        }
        return null;
    }

    public static <T> T callObjectMethod2(String tag, Object target, Class<T> returnType, String method, Class<?>[] parameterTypes,
            Object... values) {
        try {
            return ReflectUtil.callObjectMethod2(target, returnType, method, parameterTypes, values);
        } catch (NoSuchMethodException e) {
            Log.e(tag, NO_SUCH_METHOD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (InvocationTargetException e) {
            Log.e(tag, INVOCATION_TARGET_EXCEPTION, e);
        }
        return null;
    }

    public static Object callObjectMethod(String tag, Object target, String method, Class<?>[] parameterTypes, Object... values) {
        try {
            return ReflectUtil.callObjectMethod(target, method, parameterTypes, values);
        } catch (NoSuchMethodException e) {
            Log.e(tag, NO_SUCH_METHOD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (InvocationTargetException e) {
            Log.e(tag, INVOCATION_TARGET_EXCEPTION, e);
        }
        return null;
    }

    public static Object callObjectMethod2(String tag, Object target, String method, Class<?>[] parameterTypes, Object... values) {
        try {
            return ReflectUtil.callObjectMethod2(target, method, parameterTypes, values);
        } catch (NoSuchMethodException e) {
            Log.e(tag, NO_SUCH_METHOD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (InvocationTargetException e) {
            Log.e(tag, INVOCATION_TARGET_EXCEPTION, e);
        }
        return null;
    }

    public static Object callObjectMethod(String tag, Object target, String method, Class<?> clazz, Class<?>[] parameterTypes,
            Object... values) {
        try {
            return ReflectUtil.callObjectMethod(target, method, clazz, parameterTypes, values);
        } catch (NoSuchMethodException e) {
            Log.e(tag, NO_SUCH_METHOD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (InvocationTargetException e) {
            Log.e(tag, INVOCATION_TARGET_EXCEPTION, e);
        }
        return null;
    }

    public static <T> T callStaticObjectMethod(String tag, Class<?> clazz, Class<T> returnType, String method, Class<?>[] parameterTypes,
            Object... values) {
        try {
            return ReflectUtil.callStaticObjectMethod(clazz, returnType, method, parameterTypes, values);
        } catch (NoSuchMethodException e) {
            Log.e(tag, NO_SUCH_METHOD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (InvocationTargetException e) {
            Log.e(tag, INVOCATION_TARGET_EXCEPTION, e);
        }
        return null;
    }

    public static Object callStaticObjectMethod(String tag, Class<?> clazz, String method, Class<?>[] parameterTypes, Object... values) {
        try {
            return ReflectUtil.callStaticObjectMethod(clazz, method, parameterTypes, values);
        } catch (NoSuchMethodException e) {
            Log.e(tag, NO_SUCH_METHOD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (InvocationTargetException e) {
            Log.e(tag, INVOCATION_TARGET_EXCEPTION, e);
        }
        return null;
    }

    public static void setObjectField(String tag, Object target, String field, Object value) {

        try {
            ReflectUtil.setObjectField(target, field, value);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }

    }

    public static void setObjectField(String tag, Object target, Class<?> clazz, String field, Object value) {

        try {
            ReflectUtil.setObjectField(target, clazz, field, value);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }

    }

    public static Object getObjectField(String tag, Object target, String field) {
        try {
            return ReflectUtil.getObjectField(target, field);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }
        return null;
    }

    public static <T> T getObjectField(String tag, Object target, String field, Class<T> returnType) {
        try {
            return ReflectUtil.getObjectField(target, field, returnType);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }
        return null;
    }

    public static Object getObjectField(String tag, Object target, Class<?> clazz, String field) {
        try {
            return ReflectUtil.getObjectField(target, clazz, field);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }
        return null;

    }

    public static void setStaticObjectField(String tag, Class<?> clazz, String field, Object value) {
        try {
            ReflectUtil.setStaticObjectField(clazz, field, value);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }
    }

    public static Object getStaticObjectField(String tag, Class<?> clazz, String field) {
        try {
            return ReflectUtil.getStaticObjectField(clazz, field);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }
        return null;
    }

    public static <T> T getStaticObjectField(String tag, Class<?> clazz, String field, Class<T> returnType) {
        try {
            return ReflectUtil.getStaticObjectField(clazz, field, returnType);
        } catch (NoSuchFieldException e) {
            Log.e(tag, NO_SUCH_FIELD_EXCEPTION, e);
        } catch (SecurityException e) {
            Log.e(tag, SECURITY_EXCEPTION, e);
        } catch (IllegalArgumentException e) {
            Log.e(tag, ILLEGAL_ARGUMENT_EXCEPTION, e);
        } catch (IllegalAccessException e) {
            Log.e(tag, ILLEGAL_ACCESS_EXCEPTION, e);
        }
        return null;

    }

}
