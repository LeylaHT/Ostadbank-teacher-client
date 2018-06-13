package ostadbank.com.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;


/**
 * Created by Amir on 6/23/2017.
 */

public class ViewAnimationUtils {

    private static final int TRANSLATE_DURATION_MILLIS = 100;
    public static final int TRANSLATE_TO_TOP = 1;
    public static final int TRANSLATE_TO_BOTTOM = 2;


    public static void hideView(View view,int translateType)
    {
        toggleView(view,false,true,true,translateType);
    }

    public static void hideViewWithFadeAnimation(final View view, final int duration)
    {
        view.setAlpha(1f);
        view.animate()
                .alpha(0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                       view.setVisibility(View.GONE);
                    }
                });

    }

    public static void hideViewWithFadeAnimation(final View view, final int duration, final int hideType)
    {
        view.setAlpha(1f);
        view.animate()
                .alpha(0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(hideType);
                    }
                });

    }

    public static void showViewWithFadeAnimation(final View view, final int duration)
    {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animate()
                .alpha(1f)
                .setDuration(duration);
    }
    

    public static void showView(View view,int translateType)
    {
        toggleView(view,true,true,true,translateType);
    }
    static int animatedItemCount = 0;
    private static final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private static void toggleView(final View view, final boolean visible, final boolean animate, boolean force,int translateType) {

        if (force) {
            int height = view.getHeight();
            int margin = 0;
            if(translateType == TRANSLATE_TO_TOP)
                margin = (visible ? 0 : height + getMarginTop(view))*-1;
            else if(translateType == TRANSLATE_TO_BOTTOM)
                margin = visible ? 0 : height + getMarginBottom(view);
            int translationY = margin;
            if (animate) {
               // Log.d("Animate", "Animating");
                if (animatedItemCount < 2)
                    view.animate().setInterpolator(mInterpolator)
                            .setDuration(TRANSLATE_DURATION_MILLIS)
                            .setListener(new Animator.AnimatorListener() {

                                @Override
                                public void onAnimationStart(Animator animation) {
                                    animatedItemCount++;
                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    animatedItemCount--;
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .translationY(translationY);
            } else {
                ViewCompat.setTranslationY(view, translationY);
            }

        }
    }

    private static int getMarginBottom(View view) {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }
    private static int getMarginTop(View view) {
        int marginTop = 0;
        final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginTop = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        }
        return marginTop;
    }
}
