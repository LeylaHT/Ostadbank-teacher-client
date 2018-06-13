package ostadbank.com.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SystemUtils {
    public static int getScreenOrientation() {
        return Resources.getSystem().getConfiguration().orientation;
    }

    public static void showSoftInput(EditText editText, Context context)
    {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideSoftInput(EditText editText,Context context)
    {
        editText.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }
}
