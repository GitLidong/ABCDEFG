package com.support.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;

import java.lang.reflect.Constructor;

public class UserUtil {
    private static final String TAG = UserUtil.class.getSimpleName();

    public static final int PER_USER_RANGE = 100000;

    private static int sUserId = 0;

    public static final int USER_XSPACE = 999;
    public static final int USER_OWNER = 0;
    public static final int USER_NULL = -10000;

    public static final int USER_ALL = -1;

    public static final int USER_CURRENT = -2;

    private UserUtil() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                Integer id = (Integer) ReflectUtil.callStaticObjectMethod(UserHandle.class, "myUserId", null);
                if (id != null) {
                    sUserId = id;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final int myUserId() {
        return sUserId;
    }

    /**
     * Returns true if this UserHandle refers to the owner user; false otherwise.
     *
     * @return true if this UserHandle refers to the owner user; false otherwise.
     */
    public static final boolean isOwner() {
        return myUserId() == USER_OWNER;
    }

    public static final boolean isXspace() {
        return myUserId() == USER_XSPACE;
    }

    public static UserHandle newUserHandle(int userId) {
        Class<?>[] parameterTypes = new Class[]{
                int.class
        };
        try {
            Constructor<UserHandle> constructor = UserHandle.class.getConstructor(parameterTypes);
            return constructor.newInstance(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getUserId(int uid) {
        return uid / PER_USER_RANGE;
    }

    public static int getCurrentUserId() {
        // return miui.securityspace.CrossUserUtils.getCurrentUserId();
        return ReflectBuilderUtil.ReflAgent
                .getClass("miui.securityspace.CrossUserUtils")
                .callStatic("getCurrentUserId", null)
                .intResult();
    }

    public static final int getUid(int userId, int appId) {
        // return UserHandle.getUid(userId,appId);
        return ReflectBuilderUtil.ReflAgent
                .getClass("android.os.UserHandle")
                .callStatic("getUid", new Class[]{
                        Integer.TYPE, Integer.TYPE
                }, userId, appId)
                .intResult();
    }

    public static UserHandle ownerUser() {
        // return UserHandle.OWNER;
        return newUserHandle(USER_OWNER);
    }

    public static UserHandle getCurrentUser() {
        // return UserHandle.CURRENT;
        return newUserHandle(USER_CURRENT);
    }

    public static UserHandle getUserAll() {
        // return UserHandle.ALL;
        return newUserHandle(USER_ALL);
    }

    public static boolean isSecondUser() {
        return getCurrentUserId() != USER_OWNER;
    }

    public static boolean isOwnerUserApp(int uid) {
        return getUserId(uid) == 0;
    }

    public static int getTransUserId(int uid) {
        return uid == USER_OWNER ? 0 : 1;
    }

    public static int getCallingUserId() {
        // return UserHandle.getCallingUserId();
        return ReflectBuilderUtil.ReflAgent
                .getClass("android.os.UserHandle")
                .callStatic("getCallingUserId", null)
                .intResult();
    }

    public static final int getAppId(int uid) {
        // return UserHandle.getAppId(uid);
        return ReflectBuilderUtil.ReflAgent
                .getClass("android.os.UserHandle")
                .callStatic("getAppId", new Class[]{
                        Integer.TYPE
                }, uid)
                .intResult();
    }

    public static int getUserNull() {
        return USER_NULL;
    }

    public static boolean isSupportSecuritySpace() {
        //return miui.securityspace.ConfigUtils.isSupportSecuritySpace();
        return ReflectBuilderUtil.ReflAgent
                .getClass("miui.securityspace.ConfigUtils")
                .callStatic("isSupportSecuritySpace", null)
                .booleanResult();
    }

    /**
     * 第二空间是否已经开启 . return　true第二空间已开启
     */
    public static boolean isSecondSpaceOn(Context context) {
        return getIntForUser(context.getContentResolver(),
                "second_user_id", USER_NULL, USER_OWNER) != USER_NULL;
    }

    public static int getIntForUser(ContentResolver cr, String name, int def, int userHandle) {
        return ReflectBuilderUtil.ReflAgent
                .getClass("android.provider.Settings$Secure")
                .callStatic("getIntForUser",
                        new Class[]{ContentResolver.class, String.class, int.class, int.class},
                        cr, name, def, userHandle)
                .intResult();
    }

    public static boolean isManagedProfile(Context context, int userId) {
        UserManager um = (UserManager) context.getSystemService(Context.USER_SERVICE);
        boolean isFile = false;
        try {
            isFile = ReflectBuilderUtil.ReflAgent
                    .getObject(um)
                    .call("getUserInfo", new Class[]{int.class}, userId)
                    .setResultToSelf()
                    .call("isManagedProfile", null)
                    .booleanResult();
        } catch (Exception e) {
            Log.e(TAG, "isManagedProfile exception: ", e);
        }
        return isFile;
    }

    public static UserHandle getUserHandle(int uid) {
        return newUserHandle(getUserId(uid));
    }

    public static Uri addUserIdForUri(Uri uri, int userId) {
        return (Uri) ReflectBuilderUtil.ReflAgent
                .getClass("miui.securityspace.CrossUserUtils")
                .callStatic("addUserIdForUri", new Class[]{Uri.class, int.class}, uri, userId)
                .resultObject();
    }
}

