package ostadbank.com.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

//import org.apache.commons.lang.StringEscapeUtils;

/**
 * Created by mostafavi on 1/10/2016.
 */
public class ReportUtils {


    public static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE: //is API >= 9
                return "xlarge";
            default:
                return "undefined";
        }
    }

    /**
     * Gets the height percent.
     *
     * @param context the context
     * @param percent the percent
     * @return the height percent
     */
    public static int getHeightPercent(Context context, int percent) {
        return (int) ((percent * getHeight(context)) / 100);
    }

    /**
     * Gets the width percent.
     *
     * @param context the context
     * @param percent the percent
     * @return the width percent
     */
    public static int getWidthPercent(Context context, float percent) {
        return (int) ((percent * getWidth(context)) / 100);
    }

    /**
     * Gets the height.
     *
     * @param context the context
     * @return the height
     */
    public static int getHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * Gets the width.
     *
     * @param context the context
     * @return the width
     */
    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int pxToDP(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((px / displayMetrics.density) + 0.5);
    }

    /**
     * Dp to px.
     *
     * @param context the context
     * @param dp      the dp
     * @return the int
     */
    public static int dpToPX(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    /**
     * To persian digit.
     *
     * @param number the number
     * @return the string
     */
    public static String toPersianDigit(Object number) {
        String res = String.valueOf(number);
        res = res.replace('0', '۰').replace('1', '۱').replace('2', '۲').replace('3', '۳').replace('4', '۴').replace('5', '۵').replace('6', '۶').replace('7', '۷').replace('8', '۸').replace('9', '۹');
        return res;
    }

    /**
     * To english digit.
     *
     * @param number the number
     * @return the string
     */
    public static String toEnglishDigitU(Object number) {
        String res = String.valueOf(number);
        res = res.replace('۰', '0').replace('۱', '1').replace('۲', '2').replace('۳', '3').replace('۴', '4').replace('۵', '5').replace('۶', '6').replace('۷', '7').replace('۸', '8').replace('۹', '9');
        return res;
    }

   /* public static String toEnglishDigit(String number) {
        String res = StringEscapeUtils.escapeJava(number);
        res = res.replace("\\u06F0", "0").replace("\\u06F1", "1").replace("\\u06F2", "2").replace("\\u06F3", "3").replace("\\u06F4", "4").replace("\\u06F5", "5").replace("\\u06F6", "6").replace("\\u06F7", "7").replace("\\u06F8", "8").replace("\\u06F9", "9");
        res = res.replace("\\u0660", "0").replace("\\u0661", "1").replace("\\u0662", "2").replace("\\u0663", "3").replace("\\u0664", "4").replace("\\u0665", "5").replace("\\u0666", "6").replace("\\u0667", "7").replace("\\u0668", "8").replace("\\u0669", "9");
        return StringEscapeUtils.unescapeJava(res);
    }*/

    public static void setClickableView(View view, Drawable backPress) {
        try {
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_pressed}, backPress);
            states.addState(new int[]{android.R.attr.state_focused}, backPress);
            states.addState(new int[]{}, view.getBackground());
            view.setBackgroundDrawable(states);
        } catch (Exception e) {
        }
    }

    public static void setClickableView(View view, String color) {
        try {
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor(color)));
//            states.addState(new int[]{android.R.attr.state_focused}, backPress);
            states.addState(new int[]{}, view.getBackground());
            view.setBackgroundDrawable(states);
        } catch (Exception e) {
        }
    }

    public static void setClickableView(View view, Drawable backgrund, String color) {
        try {
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor(color)));
//            states.addState(new int[]{android.R.attr.state_focused}, backPress);
            states.addState(new int[]{}, backgrund);
            view.setBackgroundDrawable(states);
        } catch (Exception e) {
        }
    }

    public static void setClickableView(ImageView view, Drawable backPress) {
        try {
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_pressed}, backPress);
            states.addState(new int[]{android.R.attr.state_focused}, backPress);
            states.addState(new int[]{}, view.getDrawable());
            view.setImageDrawable(states);
        } catch (Exception e) {
        }
    }

    public static Drawable getBackgroundFromColor(String color, String colorPress) {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.parseColor(colorPress)));
        states.addState(new int[]{android.R.attr.state_focused}, new ColorDrawable(Color.parseColor(color)));
        states.addState(new int[]{}, new ColorDrawable(Color.parseColor(color)));
        return states;
    }
}
