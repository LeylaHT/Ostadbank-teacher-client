package ostadbank.com.utils;

/**
 * Created by Amir on 8/9/2017.
 */

public class ColorUtils {

    public static int lighterColor(int color, float factor) {
        int red = (int) ((android.graphics.Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((android.graphics.Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((android.graphics.Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return android.graphics.Color.argb(android.graphics.Color.alpha(color), red, green, blue);
    }

    public static int darkerColor(int color, float factor) {
        int a = android.graphics.Color.alpha(color);
        int r = android.graphics.Color.red(color);
        int g = android.graphics.Color.green(color);
        int b = android.graphics.Color.blue(color);

        return android.graphics.Color.argb(a,
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
    }
}
