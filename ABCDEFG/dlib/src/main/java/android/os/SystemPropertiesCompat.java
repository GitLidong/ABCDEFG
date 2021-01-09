package android.os;

import com.support.util.ReflectUtil;

public class SystemPropertiesCompat {
    private static Class<?> getSystemClass() throws ClassNotFoundException {
        Class<?> forName = Class.forName("android.os.SystemProperties");
        return forName;
    }

    /**
     * Get the value for the given key.
     *
     * @return an empty string if the key isn't found
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    public static String get(String key) {
        try {
            Class<?>[] parameterTypes = new Class[]{
                    String.class
            };
            return ReflectUtil.callStaticObjectMethod(getSystemClass(), String.class, "get", parameterTypes, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Get the value for the given key.
     *
     * @return if the key isn't found, return def if it isn't null, or an empty string otherwise
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    public static String get(String key, String def) {
        try {
            Class<?>[] parameterTypes = new Class[]{
                    String.class, String.class
            };
            return ReflectUtil.callStaticObjectMethod(getSystemClass(), String.class, "get", parameterTypes, key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Get the value for the given key, and return as an integer.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as an integer, or def if the key isn't found or cannot be parsed
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    public static int getInt(String key, int def) {
        try {
            Class<?>[] parameterTypes = new Class[]{
                    String.class, int.class
            };
            return ReflectUtil.callStaticObjectMethod(getSystemClass(), int.class, "getInt", parameterTypes, key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * Get the value for the given key, returned as a boolean. Values 'n', 'no', '0', 'false' or 'off' are considered false. Values 'y',
     * 'yes', '1', 'true' or 'on' are considered true. (case sensitive). If the key does not exist, or has any other value, then the default
     * result is returned.
     *
     * @param key the key to lookup
     * @param def a default value to return
     * @return the key parsed as a boolean, or def if the key isn't found or is not able to be parsed as a boolean.
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    public static boolean getBoolean(String key, boolean def) {
        try {
            Class<?>[] parameterTypes = new Class[]{
                    String.class, boolean.class
            };
            return ReflectUtil.callStaticObjectMethod(getSystemClass(), boolean.class, "getBoolean", parameterTypes, key, def);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return def;

    }

    /**
     * Set the value for the given key.
     *
     * @throws IllegalArgumentException if the key exceeds 32 characters
     * @throws IllegalArgumentException if the value exceeds 92 characters
     */
    public static void set(String key, String val) {
        try {
            Class<?>[] parameterTypes = new Class[]{
                    String.class, String.class
            };
            ReflectUtil.callStaticObjectMethod(getSystemClass(), String.class, "set", parameterTypes, key, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
