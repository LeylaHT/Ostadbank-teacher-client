package ostadbank.com.utils;

import android.app.NotificationManager;
import android.content.Context;

/**
 * Created by Amir on 4/8/2017.
 */

public class NotificationUtils {

    public static void clearNotification(Context mContext, int id) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(id);
        }
    }

}
