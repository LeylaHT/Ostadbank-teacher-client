package ostadbank.com.Application;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import java.util.Locale;

public class OstadBankApplication extends Application {

    private static Typeface BASE_FONT;
    private static Typeface MEDIUM_FONT;
    private static Typeface LIGHT_FONT;
    private static Typeface BYEKAN_FONT;
    private static Typeface BYEKAN_FONT1;
    private static Typeface IRANIAN_SANS_FONT;


    @Override
    public void onCreate() {
        super.onCreate();

        // in bekhatere ine ke betone vectorha ro bekhone
        //dar android 4 be payin vector khonde nemishe
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //UserPrefrences.init(getApplicationContext());
        initTypefaces(getApplicationContext());



    }

    public static void initTypefaces(Context context) {
        try {
            if (BASE_FONT == null)
                BASE_FONT = Typeface.createFromAsset(context.getAssets(), String.format(Locale.US, "fonts/%s", "IRANSansMobile.ttf"));
            if (MEDIUM_FONT == null)
                MEDIUM_FONT = Typeface.createFromAsset(context.getAssets(), String.format(Locale.US, "fonts/%s", "IRANSansMobile_Medium.ttf"));
            if (LIGHT_FONT == null)
                LIGHT_FONT = Typeface.createFromAsset(context.getAssets(), String.format(Locale.US, "fonts/%s", "IRANSansMobile_Light.ttf"));
            if (BYEKAN_FONT == null)
                BYEKAN_FONT = Typeface.createFromAsset(context.getAssets(), String.format(Locale.US, "fonts/%s", "Weblogma_Yekan.ttf"));
            if (BYEKAN_FONT1 == null)
                BYEKAN_FONT1 = Typeface.createFromAsset(context.getAssets(), String.format(Locale.US, "fonts/%s", "WeblogmaYekan1.ttf"));
            if (IRANIAN_SANS_FONT == null)
                IRANIAN_SANS_FONT = Typeface.createFromAsset(context.getAssets(), String.format(Locale.US, "fonts/%s", "IranianSans.ttf"));
        } catch (Exception e) {
            e.printStackTrace();
            //Logger.e(e);
        }

    }

    //fonts
    public static Typeface getBaseFont() {

        return BASE_FONT;
    }

    public static Typeface getMediumFont() {

        return MEDIUM_FONT;
    }

    public static Typeface getLightFont() {

        return LIGHT_FONT;
    }

    public static Typeface getByekanFont() {

        return BYEKAN_FONT;
    }

    public static Typeface getByekanFont1() {

        return BYEKAN_FONT1;
    }

    public static Typeface getIranianSansFont() {

        return IRANIAN_SANS_FONT;
    }




}
