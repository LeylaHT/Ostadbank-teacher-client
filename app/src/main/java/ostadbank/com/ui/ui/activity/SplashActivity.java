package ostadbank.com.ui.ui.activity;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import ostadbank.com.R;
import ostadbank.com.utils.ViewAnimationUtils;

import static ostadbank.com.Application.OstadBankApplication.getMediumFont;

public class SplashActivity extends AppCompatActivity {
    TextView tvSubTitle;
    ImageView ivOstadBankLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        fillData();
        startMainActivity();

    }


    private void initViews() {
        tvSubTitle = (TextView) findViewById(R.id.tv_sub_title);
        tvSubTitle.setTypeface(getMediumFont());
        ivOstadBankLogo = (ImageView) findViewById(R.id.iv_logo);


    }

    private void fillData() {
        ivOstadBankLogo.setBackgroundResource(R.drawable.anim_list_splash_logo);

    }

    private void startMainActivity() {
        final AnimationDrawable logoAnim = (AnimationDrawable) ivOstadBankLogo.getBackground();
        logoAnim.start();


        tvSubTitle.post(new Runnable() {
            @Override
            public void run() {
                tvSubTitle.setPivotX(tvSubTitle.getWidth());
            }
        });
        final int timeBetweenChecks = 200;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (logoAnim.getCurrent() != logoAnim.getFrame(logoAnim.getNumberOfFrames() - 1)) {
                    handler.postDelayed(this, timeBetweenChecks);
                } else {
                    ViewAnimationUtils.showViewWithFadeAnimation(tvSubTitle, 200);
                    ObjectAnimator.ofFloat(tvSubTitle, "translationY", tvSubTitle.getHeight() / 2, 0).setDuration(500).start();
                }
            }
        }, timeBetweenChecks);


        //age load nashode bod mire load mikone
        //age shode bod ke mirebe page asli


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }
}
