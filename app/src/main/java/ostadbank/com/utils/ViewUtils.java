package ostadbank.com.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.widget.ImageView;

/**
 * Created by Amir on 5/7/2017.
 */

public class ViewUtils {


    public static int getScreenWidth(Activity activity)
    {
        Resources res = activity.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity)
    {
        Resources res = activity.getResources();
        DisplayMetrics metrics = res.getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static void fixBackgroundRepeat(ImageView view) {
        Drawable bg = view.getDrawable();
        if (bg != null) {
            if (bg instanceof BitmapDrawable) {
                BitmapDrawable bmp = (BitmapDrawable) bg;
                bmp.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
                view.setImageDrawable(bmp);
            }
        }
    }


}
