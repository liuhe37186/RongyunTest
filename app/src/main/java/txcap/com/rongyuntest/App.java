package txcap.com.rongyuntest;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import io.rong.imkit.RongIM;

/**
 * 文件名:txcap.com.rongyuntest.App
 * 描 述:
 * 作 者:liuhe
 * 时 间:2017-10-10 10:20
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
//        RongIM.registerMessageType(NewFriendsMessage.class);
//        RongIM.registerMessageTemplate(new NewFriendMessageProvider());
    }


    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
