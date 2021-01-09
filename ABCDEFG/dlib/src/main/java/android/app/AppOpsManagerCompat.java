package android.app;

import com.support.util.ReflectUtil;

import java.lang.reflect.InvocationTargetException;

public class AppOpsManagerCompat {
    public static final String TAG = "AppOpsManagerCompat";

    /**
     * Result from checkOp, noteOp, startOp: this is only used
     * for get suggest mode, when suggest mode is unknown.
     */
    public static final int MODE_UNKNOWN = -1;
    /**
     * Result from checkOp, noteOp, startOp: the given caller is
     * allowed to perform the given operation.
     */
    public static final int MODE_ALLOWED = 0;
    /**
     * Result from checkOp, noteOp, startOp: the given caller is
     * not allowed to perform the given operation, and this attempt should
     * <em>silently fail</em> (it should not cause the app to crash).
     */
    public static final int MODE_IGNORED = 1;
    /**
     * Result from checkOpNoThrow, noteOpNoThrow, startOpNoThrow: the
     * given caller is not allowed to perform the given operation, and this attempt should
     * cause it to have a fatal error, typically a {@link SecurityException}.
     */
    public static final int MODE_ERRORED = 2;
    /**
     * Result from checkOp, noteOp, startOp: the given caller should
     * use its default security check.  This mode is not normally used; it should only be used
     * with appop permissions, and callers must explicitly check for it and deal with it.
     */
    public static final int MODE_DEFAULT = 3;
    /**
     * Special mode that means "allow only when app is in foreground."  This is <b>not</b>
     * returned from unsafeCheckOp, noteOp, startOp.  Rather,
     * unsafeCheckOp will always return MODE_ALLOWED (because it is always
     * possible for it to be ultimately allowed, depending on the app's background state),
     * and noteOp and startOp will return MODE_ALLOWED when the app
     * being checked is currently in the foreground, otherwise MODE_IGNORED.
     *
     * <p>The only place you will this normally see this value is through
     * unsafeCheckOpRaw, which returns the actual raw mode of the op.  Note that because
     * you can't know the current state of the app being checked (and it can change at any
     * point), you can only treat the result here as an indication that it will vary between
     * MODE_ALLOWED and MODE_IGNORED depending on changes in the background
     * state of the app.  You thus must always use noteOp or startOp to do
     * the actual check for access to the op.</p>
     */
    public static final int MODE_FOREGROUND = 4;
    /**
     * @hide Result from checkOp, noteOp, startOp:
     * AppOps Service should show a dialog box on screen to get user
     * permission.
     */
    public static final int MODE_ASK = 5;

    // when adding one of these:
    //  - increment _NUM_OP
    //  - define an OPSTR_* constant (marked as @SystemApi)
    //  - add rows to sOpToSwitch, sOpToString, sOpNames, sOpToPerms, sOpDefault
    //  - add descriptive strings to Settings/res/values/arrays.xml
    //  - add the op to the appropriate template in AppOpsState.OpsTemplate (settings app)

    /**
     * @hide No operation specified.
     */
    public static final int OP_NONE = -1;
    /**
     * @hide Access to coarse location information.
     */
    public static final int OP_COARSE_LOCATION = 0;
    /**
     * @hide Access to fine location information.
     */
    public static final int OP_FINE_LOCATION = 1;
    /**
     * @hide Causing GPS to run.
     */
    public static final int OP_GPS = 2;
    /**
     * @hide
     */
    public static final int OP_VIBRATE = 3;
    /**
     * @hide
     */
    public static final int OP_READ_CONTACTS = 4;
    /**
     * @hide
     */
    public static final int OP_WRITE_CONTACTS = 5;
    /**
     * @hide
     */
    public static final int OP_READ_CALL_LOG = 6;
    /**
     * @hide
     */
    public static final int OP_WRITE_CALL_LOG = 7;
    /**
     * @hide
     */
    public static final int OP_READ_CALENDAR = 8;
    /**
     * @hide
     */
    public static final int OP_WRITE_CALENDAR = 9;
    /**
     * @hide
     */
    public static final int OP_WIFI_SCAN = 10;
    public static final int OP_POST_NOTIFICATION = 11;
    /**
     * @hide
     */
    public static final int OP_NEIGHBORING_CELLS = 12;
    /**
     * @hide
     */
    public static final int OP_CALL_PHONE = 13;
    /**
     * @hide
     */
    public static final int OP_READ_SMS = 14;
    /**
     * @hide
     */
    public static final int OP_WRITE_SMS = 15;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_SMS = 16;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_EMERGECY_SMS = 17;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_MMS = 18;
    /**
     * @hide
     */
    public static final int OP_RECEIVE_WAP_PUSH = 19;
    /**
     * @hide
     */
    public static final int OP_SEND_SMS = 20;
    /**
     * @hide
     */
    public static final int OP_READ_ICC_SMS = 21;
    /**
     * @hide
     */
    public static final int OP_WRITE_ICC_SMS = 22;
    /**
     * @hide
     */
    public static final int OP_WRITE_SETTINGS = 23;
    /**
     * @hide Required to draw on top of other apps.
     */
    public static final int OP_SYSTEM_ALERT_WINDOW = 24;
    /**
     * @hide
     */
    public static final int OP_ACCESS_NOTIFICATIONS = 25;
    /**
     * @hide
     */
    public static final int OP_CAMERA = 26;
    /**
     * @hide
     */
    public static final int OP_RECORD_AUDIO = 27;
    /**
     * @hide
     */
    public static final int OP_PLAY_AUDIO = 28;
    /**
     * @hide
     */
    public static final int OP_READ_CLIPBOARD = 29;
    /**
     * @hide
     */
    public static final int OP_WRITE_CLIPBOARD = 30;
    /**
     * @hide
     */
    public static final int OP_TAKE_MEDIA_BUTTONS = 31;
    /**
     * @hide
     */
    public static final int OP_TAKE_AUDIO_FOCUS = 32;
    /**
     * @hide
     */
    public static final int OP_AUDIO_MASTER_VOLUME = 33;
    /**
     * @hide
     */
    public static final int OP_AUDIO_VOICE_VOLUME = 34;
    /**
     * @hide
     */
    public static final int OP_AUDIO_RING_VOLUME = 35;
    /**
     * @hide
     */
    public static final int OP_AUDIO_MEDIA_VOLUME = 36;
    /**
     * @hide
     */
    public static final int OP_AUDIO_ALARM_VOLUME = 37;
    /**
     * @hide
     */
    public static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
    /**
     * @hide
     */
    public static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
    /**
     * @hide
     */
    public static final int OP_WAKE_LOCK = 40;
    /**
     * @hide Continually monitoring location data.
     */
    public static final int OP_MONITOR_LOCATION = 41;
    /**
     * @hide Continually monitoring location data with a relatively high power request.
     */
    public static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;
    /**
     * @hide Retrieve current usage stats via UsageStatsManager.
     */
    public static final int OP_GET_USAGE_STATS = 43;
    /**
     * @hide
     */
    public static final int OP_MUTE_MICROPHONE = 44;
    /**
     * @hide
     */
    public static final int OP_TOAST_WINDOW = 45;
    /**
     * @hide Capture the device's display contents and/or audio
     */
    public static final int OP_PROJECT_MEDIA = 46;
    /**
     * @hide Activate a VPN connection without user intervention.
     */
    public static final int OP_ACTIVATE_VPN = 47;
    /**
     * @hide Access the WallpaperManagerAPI to write wallpapers.
     */
    public static final int OP_WRITE_WALLPAPER = 48;
    /**
     * @hide Received the assist structure from an app.
     */
    public static final int OP_ASSIST_STRUCTURE = 49;
    /**
     * @hide Received a screenshot from assist.
     */
    public static final int OP_ASSIST_SCREENSHOT = 50;
    /**
     * @hide Read the phone state.
     */
    public static final int OP_READ_PHONE_STATE = 51;
    /**
     * @hide Add voicemail messages to the voicemail content provider.
     */
    public static final int OP_ADD_VOICEMAIL = 52;
    /**
     * @hide Access APIs for SIP calling over VOIP or WiFi.
     */
    public static final int OP_USE_SIP = 53;
    /**
     * @hide Intercept outgoing calls.
     */
    public static final int OP_PROCESS_OUTGOING_CALLS = 54;
    /**
     * @hide User the fingerprint API.
     */
    public static final int OP_USE_FINGERPRINT = 55;
    /**
     * @hide Access to body sensors such as heart rate, etc.
     */
    public static final int OP_BODY_SENSORS = 56;
    /**
     * @hide Read previously received cell broadcast messages.
     */
    public static final int OP_READ_CELL_BROADCASTS = 57;
    /**
     * @hide Inject mock location into the system.
     */
    public static final int OP_MOCK_LOCATION = 58;
    /**
     * @hide Read external storage.
     */
    public static final int OP_READ_EXTERNAL_STORAGE = 59;
    /**
     * @hide Write external storage.
     */
    public static final int OP_WRITE_EXTERNAL_STORAGE = 60;
    /**
     * @hide Turned on the screen.
     */
    public static final int OP_TURN_SCREEN_ON = 61;
    /**
     * @hide Get device accounts.
     */
    public static final int OP_GET_ACCOUNTS = 62;
    /**
     * @hide Control whether an application is allowed to run in the background.
     */
    public static final int OP_RUN_IN_BACKGROUND = 63;
    /**
     * @hide
     */
    public static final int OP_AUDIO_ACCESSIBILITY_VOLUME = 64;
    /**
     * @hide Read the phone number.
     */
    public static final int OP_READ_PHONE_NUMBERS = 65;
    /**
     * @hide Request package installs through package installer
     */
    public static final int OP_REQUEST_INSTALL_PACKAGES = 66;
    /**
     * @hide Enter picture-in-picture.
     */
    public static final int OP_PICTURE_IN_PICTURE = 67;
    /**
     * @hide Instant app start foreground service.
     */
    public static final int OP_INSTANT_APP_START_FOREGROUND = 68;
    /**
     * @hide Answer incoming phone calls
     */
    public static final int OP_ANSWER_PHONE_CALLS = 69;
    /**
     * @hide Run jobs when in background
     */
    public static final int OP_RUN_ANY_IN_BACKGROUND = 70;
    /**
     * @hide Change Wi-Fi connectivity state
     */
    public static final int OP_CHANGE_WIFI_STATE = 71;
    /**
     * @hide Request package deletion through package installer
     */
    public static final int OP_REQUEST_DELETE_PACKAGES = 72;
    /**
     * @hide Bind an accessibility service.
     */
    public static final int OP_BIND_ACCESSIBILITY_SERVICE = 73;
    /**
     * @hide Continue handover of a call from another app
     */
    public static final int OP_ACCEPT_HANDOVER = 74;
    /**
     * @hide Create and Manage IPsec Tunnels
     */
    public static final int OP_MANAGE_IPSEC_TUNNELS = 75;
    /**
     * @hide Any app start foreground service.
     */
    public static final int OP_START_FOREGROUND = 76;
    /**
     * @hide
     */
    public static final int OP_BLUETOOTH_SCAN = 77;
    /**
     * @hide Use the BiometricPrompt/BiometricManager APIs.
     */
    public static final int OP_USE_BIOMETRIC = 78;
    /**
     * @hide Physical activity recognition.
     */
    public static final int OP_ACTIVITY_RECOGNITION = 79;
    /**
     * @hide Financial app sms read.
     */
    public static final int OP_SMS_FINANCIAL_TRANSACTIONS = 80;
    /**
     * @hide Read media of audio type.
     */
    public static final int OP_READ_MEDIA_AUDIO = 81;
    /**
     * @hide Write media of audio type.
     */
    public static final int OP_WRITE_MEDIA_AUDIO = 82;
    /**
     * @hide Read media of video type.
     */
    public static final int OP_READ_MEDIA_VIDEO = 83;
    /**
     * @hide Write media of video type.
     */
    public static final int OP_WRITE_MEDIA_VIDEO = 84;
    /**
     * @hide Read media of image type.
     */
    public static final int OP_READ_MEDIA_IMAGES = 85;
    /**
     * @hide Write media of image type.
     */
    public static final int OP_WRITE_MEDIA_IMAGES = 86;
    /**
     * @hide Has a legacy (non-isolated) view of storage.
     */
    public static final int OP_LEGACY_STORAGE = 87;
    /**
     * @hide Accessing accessibility features
     */
    public static final int OP_ACCESS_ACCESSIBILITY = 88;
    /**
     * @hide Read the device identifiers (IMEI / MEID, IMSI, SIM / Build serial)
     */
    public static final int OP_READ_DEVICE_IDENTIFIERS = 89;
    /**
     * @hide Read location metadata from media
     */
    public static final int OP_ACCESS_MEDIA_LOCATION = 90;
    /**
     * @hide
     */
    public static final int _NUM_OP = 91;

    // MIUI ADD: START
    /**
     * @hide
     */
    public static final int MIUI_OP_START = 10000;
    /**
     * @hide
     */
    public static final int OP_WIFI_CHANGE = 10001;
    /**
     * @hide
     */
    public static final int OP_BLUETOOTH_CHANGE = 10002;
    /**
     * @hide
     */
    public static final int OP_DATA_CONNECT_CHANGE = 10003;
    /**
     * @hide
     */
    public static final int OP_SEND_MMS = 10004;
    /**
     * @hide
     */
    public static final int OP_READ_MMS = 10005;
    /**
     * @hide
     */
    public static final int OP_WRITE_MMS = 10006;
    /**
     * @hide
     */
    public static final int OP_BOOT_COMPLETED = 10007;
    /**
     * @hide MIUI ADD: if the application is permitted to auto start
     */
    public static final int OP_AUTO_START = 10008;
    /**
     * @hide
     */
    public static final int OP_NFC_CHANGE = 10009;
    /**
     * @hide
     */
    public static final int OP_DELETE_SMS = 10010;
    /**
     * @hide
     */
    public static final int OP_DELETE_MMS = 10011;
    /**
     * @hide
     */
    public static final int OP_DELETE_CONTACTS = 10012;
    /**
     * @hide
     */
    public static final int OP_DELETE_CALL_LOG = 10013;
    /**
     * @hide
     */
    public static final int OP_EXACT_ALARM = 10014;
    /**
     * @hide
     */
    public static final int OP_ACCESS_XIAOMI_ACCOUNT = 10015;
    /**
     * @hide {@link android.Manifest.permission#NFC}
     */
    public static final int OP_NFC = 10016;
    /**
     * @hide com.android.launcher.permission.INSTALL_SHORTCUT
     */
    public static final int OP_INSTALL_SHORTCUT = 10017;
    /**
     * @hide read notification sms
     */
    public static final int OP_READ_NOTIFICATION_SMS = 10018;
    /**
     * @hide {@link android.Manifest.permission#GET_TASKS}
     */
    public static final int OP_GET_TASKS = 10019;
    /**
     * @hide {@link android.view.WindowManager.LayoutParams#FLAG_SHOW_WHEN_LOCKED}
     */
    public static final int OP_SHOW_WHEN_LOCKED = 10020;
    /**
     * @hide Background start Activity
     */
    public static final int OP_BACKGROUND_START_ACTIVITY = 10021;
    /**
     * @hide get installed applications
     */
    public static final int OP_GET_INSTALLED_APPS = 10022;
    /**
     * @hide set service foreground
     */
    public static final int OP_SERVICE_FOREGROUND = 10023;
    /**
     * @hide
     */
    public static final int OP_GET_ANONYMOUS_ID = 10024;
    /**
     * @hide
     */
    public static final int OP_GET_UDEVICE_ID = 10025;
    /**
     * @hide
     */
    public static final int OP_SHOW_DEAMON_NOTIFICATION = 10026;
    /**
     * @hide background location
     */
    public static final int OP_BACKGROUND_LOCATION = 10027;
    /**
     * @hide read sms real
     */
    public static final int OP_READ_SMS_REAL = 10028;
    /**
     * @hide read contacts real
     */
    public static final int OP_READ_CONTACTS_REAL = 10029;
    /**
     * @hide read calendar real
     */
    public static final int OP_READ_CALENDAR_REAL = 10030;
    /**
     * @hide read call log real
     */
    public static final int OP_READ_CALL_LOG_REAL = 10031;
    /**
     * @hide read phone state, unused on Q
     */
    public static final int OP_READ_PHONE_STATE_REAL = 10032;
    /**
     * @hide
     */
    public static final int MIUI_OP_END = 10033;
    // END

    public static final String OPSTR_LEGACY_STORAGE = "android:legacy_storage";

    public static int permissionToOpCode(String permission) {
        int result = OP_NONE;
        try {
            result = (int) ReflectUtil.callStaticObjectMethod(AppOpsManager.class, "permissionToOpCode", new Class[]{String.class}, permission);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int checkOpNoThrow(AppOpsManager manager, String op, int uid, String packageName) {
        int result = MODE_DEFAULT;
        try {
            result = (int) ReflectUtil.callObjectMethod(manager,
                    "checkOpNoThrow",
                    new Class[]{String.class, int.class, String.class},
                    op, uid, packageName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int checkOpNoThrow(AppOpsManager manager, int op, int uid, String packageName) {
        int result = MODE_DEFAULT;
        try {
            result = (int) ReflectUtil.callObjectMethod(manager,
                    "checkOpNoThrow",
                    new Class[]{int.class, int.class, String.class},
                    op, uid, packageName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

}
