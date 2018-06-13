package ostadbank.com.classes.tools;

/**
 * Created by Home on 7/5/2017.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class CrossFader {
    private View mView1, mView2;
    private int mDuration;

    /***
     * Instantiate a new CrossFader object.
     * @param view2 the view to fade in
     * @param fadeDuration the duration in milliseconds for each fade to last
     */
    public CrossFader(View view2, int fadeDuration) {
        mView2 = view2;
        mDuration = fadeDuration;
    }

    /***
     * Start the cross-fade animation.
     */
    public void startWhenViewIsInvisible() {
        mView2.setAlpha(0f);
        mView2.setVisibility(View.VISIBLE);
        mView2.animate()
                .alpha(0f)
                .setDuration(mDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mView2.animate()
                                .alpha(1f)
                                .setDuration(mDuration)
                                .setListener(null);
                    }
                });
    }



    public void startWhenViewIsVisible() {

        mView2.setAlpha(0f);
        mView2.animate()
                .alpha(0f)
                .setDuration(mDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mView2.animate()
                                .alpha(1f)
                                .setDuration(mDuration)
                                .setListener(null);
                    }
                });

    }
}