package ostadbank.com.utils;

import android.widget.EditText;

import java.util.Locale;


/**
 * Created by mi1s0n on 6/5/2015.
 */
public class TextUtils {


    public static String convertPersianToLatinDigit(String bodyItem) {
        if (bodyItem.contains("۰")) {
            bodyItem = bodyItem.replace("۰", "0");
        }
        if (bodyItem.contains("۱")) {
            bodyItem = bodyItem.replace("۱", "1");
        }
        if (bodyItem.contains("۲")) {
            bodyItem = bodyItem.replace("۲", "2");
        }
        if (bodyItem.contains("۳")) {
            bodyItem = bodyItem.replace("۳", "3");
        }
        if (bodyItem.contains("۴")) {
            bodyItem = bodyItem.replace("۴", "4");
        }
        if (bodyItem.contains("۵")) {
            bodyItem = bodyItem.replace("۵", "5");
        }
        if (bodyItem.contains("۶")) {
            bodyItem = bodyItem.replace("۶", "6");
        }
        if (bodyItem.contains("۷")) {
            bodyItem = bodyItem.replace("۷", "7");
        }
        if (bodyItem.contains("۸")) {
            bodyItem = bodyItem.replace("۸", "8");
        }
        if (bodyItem.contains("۹")) {
            bodyItem = bodyItem.replace("۹", "9");
        }

        return bodyItem;
    }

    public static String toPersianDigits(String bodyItem) {
        if (bodyItem == null) return null;

        if (bodyItem.contains("0")) {
            bodyItem = bodyItem.replace("0", "۰");
        }
        if (bodyItem.contains("1")) {
            bodyItem = bodyItem.replace("1", "۱");
        }
        if (bodyItem.contains("2")) {
            bodyItem = bodyItem.replace("2", "۲");
        }
        if (bodyItem.contains("3")) {
            bodyItem = bodyItem.replace("3", "۳");
        }
        if (bodyItem.contains("4")) {
            bodyItem = bodyItem.replace("4", "۴");
        }
        if (bodyItem.contains("5")) {
            bodyItem = bodyItem.replace("5", "۵");
        }
        if (bodyItem.contains("6")) {
            bodyItem = bodyItem.replace("6", "۶");
        }
        if (bodyItem.contains("7")) {
            bodyItem = bodyItem.replace("7", "۷");
        }
        if (bodyItem.contains("8")) {
            bodyItem = bodyItem.replace("8", "۸");
        }
        if (bodyItem.contains("9")) {
            bodyItem = bodyItem.replace("9", "۹");
        }

        return bodyItem;
    }

    public static String getCurrencyFormattedNumber(long number) {
        return String.format(Locale.US, "%,d", number);
    }

    public static boolean isValidInput(EditText editText) {
        if (!editText.getText().toString().equals("") &&
                !((editText.getText().toString().length()) < 1) &&
                !editText.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

}
