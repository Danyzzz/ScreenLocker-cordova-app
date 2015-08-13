import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;

import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScreenLocker extends CordovaPlugin {
    public static final String TAG = "ScreenLocker";
    public static final String ACTION_UNLOCK = "unlock";
    public static final String ACTION_LOCK = "lock";

    /**
     * Constructor.
     */
    public ScreenLocker() {
    }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Log.v(TAG, "Init ScreenLocker");
    }

    public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        final int duration = Toast.LENGTH_SHORT;
// Shows a toast
        Log.v(TAG, "ScreenLocker received:" + action);

        try {
            JSONObject arg_object = args.getJSONObject(0);
//                this.cordova.getActivity().startActivity(calIntent);
//                Get the window from the context

            if (ACTION_LOCK.equals(action)) {
//                Lock device
                WindowManager wm = this.cordova.getActivity().getSystemService(Context.WINDOW_SERVICE);
                DevicePolicyManager mDPM;
                mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
                callbackContext.success();
                return true;
            } else if (ACTION_UNLOCK.equals(action)) {
//                Unlock
//                http://developer.android.com/reference/android/app/Activity.html#getWindow()
                WindowManager wm = this.cordova.getActivity().getSystemService(Context.WINDOW_SERVICE);
                Window window = getWindow();
                window.addFlags(LayoutParams.FLAG_DISMISS_KEYGUARD);
                window.addFlags(LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                window.addFlags(LayoutParams.FLAG_TURN_SCREEN_ON);
                callbackContext.success();
                return true;
            }
            callbackContext.error("Invalid action");
            return false;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        }

//        cordova.getActivity().runOnUiThread(new Runnable() {
//            public void run() {
//                Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), action, duration);
//                toast.show();
//            }
//        });
        return true;
    }
}