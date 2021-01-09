
package android.os;

public final class UserHandle implements Parcelable {

    public static final int USER_ALL = -1;

    public static final int USER_OWNER = 0;

    public static final UserHandle CURRENT_OR_SELF = null;

    public static final int USER_CURRENT = -2;

    public static final int USER_NULL = -10000;

    public static final UserHandle OWNER = null;
    public static final UserHandle CURRENT = null;
    public static final UserHandle ALL = null;

    public UserHandle(int h) {
    }

    /**
     * Returns the user id for a given uid.
     */
    public static int getUserId(int uid) {
        return 0;
    }

    public static int getAppId(int uid) {
        return 0;
    }

    public static int myUserId() {
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public int getIdentifier() {
        return 0;
    }

    public static int getCallingUserId() {
        return 0;
    }

    public static UserHandle getUserHandleForUid(int uid) {
        return null;
    }
}
