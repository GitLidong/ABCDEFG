package com.support.util;

import android.content.Intent;
import android.util.Pair;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectBuilderUtil {

    public static class ReflAgent {

        private Class mClass;

        private Object mObject;

        private Object mResult;

        private ReflAgent() {

        }

        public static ReflAgent getClass(String clsStr) {
            ReflAgent reflAgent = new ReflAgent();
            try {
                reflAgent.mClass = Class.forName(clsStr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return reflAgent;
        }

        public static ReflAgent getObject(Object obj) {
            ReflAgent reflAgent = new ReflAgent();
            if (obj != null) {
                reflAgent.mObject = obj;
                reflAgent.mClass = obj.getClass();
            }
            return reflAgent;
        }

        public ReflAgent newObject(Class<?>[] parameterTypes, Object... values) {
            if (mClass != null) {
                try {
                    mObject = mClass.getConstructor(parameterTypes).newInstance(values);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public ReflAgent call(String method, Class<?>[] parameterTypes, Object... values) {
            if (mObject != null) {
                try {
                    mResult = callObjectMethod(mObject, method, parameterTypes, values);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public ReflAgent callStatic(String method, Class<?>[] parameterTypes, Object... values) {
            if (mClass != null) {
                try {
                    mResult = callStaticObjectMethod(mClass, method, parameterTypes, values);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public ReflAgent getStaticFiled(String field) {
            if (mClass != null) {
                try {
                    mResult = getStaticObjectField(mClass, field);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public ReflAgent getObjectFiled(String field) {
            if (mObject != null) {
                try {
                    mResult = getObjectField(mObject, field);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }


        public ReflAgent setResultToSelf() {
            mObject = mResult;
            mResult = null;
            return this;
        }

        public String stringResult() {
            if (mResult == null) {
                return null;
            }
            return mResult.toString();
        }

        public boolean booleanResult() {
            if (mResult == null) {
                return false;
            }
            return (Boolean) mResult;
        }

        public int intResult() {
            if (mResult == null) {
                return 0;
            }
            return (Integer) mResult;
        }

        public long longResult() {
            if (mResult == null) {
                return 0;
            }
            return (Long) mResult;
        }

        public Intent intentResult() {
            if (mResult == null) {
                return null;
            }
            return (Intent) mResult;
        }

        public Pair pairResult() {
            if (mResult == null) {
                return null;
            }
            return (Pair) mResult;
        }

        public Object resultObject() {
            if (mResult == null) {
                return null;
            }
            return mResult;
        }

        public Object getObject() {
            return mObject;
        }
    }


    /**
     * 对象方法调用
     *
     * @param target         调用目标对象
     * @param returnType     返回类型
     * @param method         方法名称
     * @param parameterTypes 方法参数类型
     * @param values         参数
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

    public static Object callObjectMethod(Object target, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends Object> clazz = target.getClass();
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(target, values);
    }

    public static Object callObjectMethod(Class cls, Object target, String method, Class<?>[] parameterTypes, Object... values)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends Object> clazz = cls;
        Method declaredMethod = clazz.getDeclaredMethod(method, parameterTypes);
        declaredMethod.setAccessible(true);
        return declaredMethod.invoke(target, values);
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

    /**
     * 对象获取值
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

    public static <T> T getObjectField(Object target, String field, Class<T> returnType) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Class<? extends Object> clazz = target.getClass();
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return (T) declaredField.get(target);
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
     * 获取构造器对象
     *
     * @param clazz
     * @param parameterTypes
     * @param values
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static Object newObject(Class<?> clazz, Class<?>[] parameterTypes, Object... values) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (clazz != null) {
            return clazz.getConstructor(parameterTypes).newInstance(values);
        }
        return null;
    }
}
