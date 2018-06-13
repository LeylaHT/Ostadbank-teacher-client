package ostadbank.com.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.Semaphore;

import ostadbank.com.R;


/**
 * Created by mostafavi on 3/7/2016.
 */
public class Utils {

    static Semaphore waitForWaritingFile = new Semaphore(1);

    public interface OnDialogClick {
        void onOkClick(AlertDialog alertDialog);

        void onCancelClick(AlertDialog alertDialog);
    }

    public static boolean IsNullString(String data) {
        if (data == null || data.equals("") || data.toUpperCase().equals("NULL"))
            return true;
        return false;
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static String getDeviceID(Context ctx) {
        String DeviceId = "000000000000000";
        try {
            DeviceId = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
            if (!telephonyManager.getDeviceId().toString().equals("000000000000000"))
                DeviceId = telephonyManager.getDeviceId().toString();
        } catch (Exception e) {
        }
        return DeviceId;
    }

    //region convert values
    public static int toInt(String txt) {
        try {
            return Integer.parseInt(txt);
        } catch (Exception e) {
            return 0;
        }
    }

    public static double toDouble(String txt) {
        try {
            return Double.parseDouble(txt);
        } catch (Exception e) {
            return 0;
        }
    }

    //endregion

    //region Internet & download
    public static boolean appInstalledOrNot(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(uri, 0);

            app_installed = ai.enabled;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
    //endregion

    //region DB & Share preference
    public static void setKeyInSharedPreferences(Context context, String key, String value) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        if (sharedpreferences == null)
            return;
        SharedPreferences.Editor edit = sharedpreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static String getKeyFromSharedPreferences(Context context, String key) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        if (sharedpreferences != null && sharedpreferences.contains(key)) {
            return sharedpreferences.getString(key, "");
        }
        return "";
    }

    public static String correctFarsiText(String str) {
        if (str == null)
            return "";
        return str.replace('ي', 'ی').replace('ي', 'ى').replace('ك', 'ک');
    }
    //endregion

    //region View Size & Pictures & colors
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(Context activity,View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.

        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int dpToPX(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static int getHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static Bitmap getScaledBitmap(String picturePath, int width, int height) {
        BitmapFactory.Options sizeOptions = new BitmapFactory.Options();
        sizeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, sizeOptions);

        int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

        sizeOptions.inJustDecodeBounds = false;
        sizeOptions.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static int getThemeColor(Context mContext) {
        TypedValue typedValue = new TypedValue();

        TypedArray a = mContext.obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorAccent});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    public static Drawable changeDrawableColor(Drawable drawable, int color) {
        Drawable nDrawable = drawable.mutate();
        nDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        return nDrawable;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static Bitmap createTransparentImage(Bitmap image, int size, int padding) {
        Bitmap bmOverlay = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);//bMap.getConfig());

        Canvas canvas = new Canvas(bmOverlay);
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        paint.setAlpha(90);
        Paint pp = new Paint();
        pp.setColor(Color.WHITE);
        canvas.drawCircle(size / 2, size / 2, size / 2, pp);
        image = Bitmap.createScaledBitmap(image, size - padding, size - padding, true);
        int padding2 = padding / 2;
        for (int i = 0; i < image.getWidth(); i++) {
            for (int i1 = 0; i1 < image.getHeight(); i1++) {
                int pixel = image.getPixel(i, i1);
                if (pixel != 0) {
                    bmOverlay.setPixel(i + padding2, i1 + padding2, Color.TRANSPARENT);
                }
            }
        }
        image.recycle();
        image = null;
        return bmOverlay;
    }
    //endregion

    //region read & write files
    public static void writeStringIntoFile(final File file, final String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitForWaritingFile.acquire();
                    file.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(file);
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut, "UTF-8");
//                    OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                    myOutWriter.append(data);
                    myOutWriter.close();
                    fOut.close();
                    waitForWaritingFile.release();
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
//            writer.write(data, 0, data.length());
//            writer.flush();
//            writer.close();
                } catch (Exception e) {

                }
            }
        }).start();

    }

    public static String readStringFromFile(Context context, String path) {
        InputStream json = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                return "";
            }
            json = new FileInputStream(file);
            return getStringFromInputStream(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @NonNull
    public static String getStringFromInputStream(InputStream json) throws IOException {
        StringBuilder buf = new StringBuilder();
        BufferedReader in =
                new BufferedReader(new InputStreamReader(json, "UTF-8"));
        String str;

        while ((str = in.readLine()) != null) {
            buf.append(str);
        }

        in.close();
        return buf.toString();
    }

    public void writeInputStringToFile(byte[] data, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }

    public static String getExternalCache(Context context) {

        File externalCacheDir = Environment.getExternalStorageDirectory();
        if (externalCacheDir != null)
            return externalCacheDir.getPath() + "/LifeBadget";
        else
            return context.getCacheDir().getPath();
    }

    public static boolean isOnline(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    //endregion

    //region animation
//    public static void setAnimationToDialog(final AlertDialog dialog, final View viewCoverDialog) {
//        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface tmpdialog) {
//                viewCoverDialog.setVisibility(View.INVISIBLE);
//                dialog.getWindow().getDecorView().setScaleX(0);
//                dialog.getWindow().getDecorView().setScaleY(0.05f);
//                ValueAnimator anim = ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "scaleX", 0, 1);
//                anim.setDuration(400);
//                anim.addListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        ValueAnimator anim = ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "scaleY", 0.05f, 1);
//                        anim.setDuration(250);
//                        anim.addListener(new Animator.AnimatorListener() {
//                            @Override
//                            public void onAnimationStart(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationEnd(Animator animation) {
//                                viewCoverDialog.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void onAnimationCancel(Animator animation) {
//
//                            }
//
//                            @Override
//                            public void onAnimationRepeat(Animator animation) {
//
//                            }
//                        });
//                        anim.start();
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                });
//                anim.start();
//            }
//        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface tmpdialog) {
////                ValueAnimator anim = ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "scaleY", 1, 0);
////                anim.setDuration(400);
////                anim.start();
//            }
//        });
//
//    }
//
//    public static void setAnimationToCloseDialog(final AlertDialog dialog, final View content) {
//        content.setVisibility(View.INVISIBLE);
//        ValueAnimator anim = ObjectAnimator.ofFloat(dialog.getWindow().getDecorView(), "scaleY", 1, 0);
//        anim.setDuration(400);
//        anim.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                content.setVisibility(View.VISIBLE);
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        anim.start();
//
//    }
//endregion

    //region Date
//    public static String getHourTime(long date) {
//        SimpleDateFormat month_date = new SimpleDateFormat("HH:mm");
//        return month_date.format(new Date(date));
//    }
//
//    public static String getShortDate(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            SimpleDateFormat month_date = new SimpleDateFormat("dd MMM");
//            return month_date.format(new Date(date));
//        } else {
//            return "";
//        }
//    }
//
//    public static String getMonthName(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            SimpleDateFormat month_date = new SimpleDateFormat("MMM");
//            return month_date.format(new Date(date));
//        } else {
//            return "";
//        }
//    }
//
//    public static String getYearName(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            return String.valueOf(new DateTime(date).getYear());
//        } else {
//            return String.valueOf(JalaliCalendar.getJalaliDate(date).getYear());
//        }
//    }
//
//    public static String getLongMonthName(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
//            return month_date.format(new Date(date));
//        } else {
//            return JalaliCalendar.getJalaliDate(date).toLongMonth();
//        }
//    }
//
//    public static String getFullDayName(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            SimpleDateFormat month_date = new SimpleDateFormat("EEEE dd MMMM yyyy");
//            return month_date.format(new Date(date));
//        } else {
//            return JalaliCalendar.getJalaliDate(date).toFullDay();
//        }
//    }
//
//    public static String getDateString(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            SimpleDateFormat month_date = new SimpleDateFormat("dd MMMM yyyy");
//            return month_date.format(new Date(date));
//        } else {
//            return JalaliCalendar.getJalaliDate(date).toCompleteDate();
//        }
//    }
//
//    public static String getShortDayWeekName(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            SimpleDateFormat month_date = new SimpleDateFormat("EEE");
//            return month_date.format(new Date(date));
//        } else {
//            return JalaliCalendar.getJalaliDate(date).toFullDay();
//        }
//    }
//
//    public static String getDayName(Context context, long date) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            SimpleDateFormat month_date = new SimpleDateFormat("dd");
//            return month_date.format(new Date(date));
//        } else {
//            return String.valueOf(JalaliCalendar.getJalaliDate(date).getDate());
//        }
//    }
//
//    public static long addMonth(Context context, Calendar calendar) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            calendar.add(Calendar.MONTH, 1);
//            return calendar.getTimeInMillis();
//        } else {
//            JalaliCalendar.YearMonthDate jalaliDate = JalaliCalendar.getJalaliDate(calendar.getTimeInMillis());
//            jalaliDate.setDate(1);
//            return jalaliDate.addMonth();
//        }
//    }
//
//    public static long addYear(Context context, Calendar calendar) {
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            calendar.add(Calendar.YEAR, 1);
//            return calendar.getTimeInMillis();
//        } else {
//            JalaliCalendar.YearMonthDate jalaliDate = JalaliCalendar.getJalaliDate(calendar.getTimeInMillis());
//            jalaliDate.setDate(1);
//            jalaliDate.setMonth(0);
//            return jalaliDate.addYear();
//        }
//    }
//
//    public static long[] getCurrentStartEndWeek(Context context) {
//        Calendar calendar = Calendar.getInstance();
//        int firstDayWeek = BaseActivity.getInFirstDayOfWeek(context);
//
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.set(Calendar.AM_PM, Calendar.AM);
//
//        int d_o_w = calendar.get(Calendar.DAY_OF_WEEK);
//        d_o_w = d_o_w - firstDayWeek;
//        if (d_o_w == 7)
//            d_o_w = 0;
//        int temp_d_o_w = d_o_w;
//        calendar.add(Calendar.DAY_OF_YEAR, -1 * d_o_w);
//
//        long startDate = calendar.getTimeInMillis();
//
//        calendar.add(Calendar.DAY_OF_MONTH, 7);
//        long endDate = calendar.getTimeInMillis();
//
//        return new long[]{startDate, endDate};
//    }
//
//    public static long[] getCurrentStatEndMonth(Context context) {
//        Calendar calendar = Calendar.getInstance();
//
//        long endDate;
//        long startDate;
//        if (BaseActivity.getDefaultCalendar(context).equals(Cal.CalendarGregorain)) {
//            calendar.set(Calendar.HOUR, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//            calendar.set(Calendar.AM_PM, Calendar.AM);
//            calendar.set(Calendar.DAY_OF_MONTH, 1);
//            startDate = calendar.getTimeInMillis();
//            calendar.add(Calendar.MONTH, 1);
//            endDate = calendar.getTimeInMillis();
//        } else {
//            JalaliCalendar.YearMonthDate tmp = JalaliCalendar.getJalaliDate(Calendar.getInstance().getTimeInMillis());
//            int month = tmp.getMonth();
//            int year = tmp.getYear();
//            JalaliCalendar jalaliCalendar = new JalaliCalendar();
//            startDate = jalaliCalendar.getGregorianDate(year + "/" + month + "/1").getTime();
//            if (month == 12) {
//                month = 1;
//                year++;
//            }
//            endDate = jalaliCalendar.getGregorianDate(year + "/" + month + "/1").getTime();
//        }
//        return new long[]{startDate, endDate};
//    }
//endregion

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }


}
