package ostadbank.com.ui.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ostadbank.com.R;

import static ostadbank.com.Application.OstadBankApplication.getBaseFont;

public class MainActivity extends AppCompatActivity {
    TextView tvCredit, tvCreditLabel, tvStatus, tvStatusLabel, tvRate, tvRateLabel, tvNoTime;
    TextView tvNewRequest, tvRegisteredSessions, tvRegisterNewSession, tvSessionHandling, tvSupportTicket;
    com.suke.widget.SwitchButton sbNoTime;
    ImageView ivMenu;
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fillData();
        handleToggleButton();
        initListener();
    }

    private void handleToggleButton() {


//        sbNoTime.setChecked(true);
//        sbNoTime.isChecked();
//        //switch state
//        sbNoTime.toggle(false);//switch without animation
//        sbNoTime.setShadowEffect(true);//disable shadow effect
//        sbNoTime.setEnabled(false);//disable button
//        sbNoTime.setEnableEffect(false);//disable the switch animation
//        sbNoTime.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
//                sbNoTime.toggle();
//                //TODO do your job
//            }
//        });
//

    }


    private void initViews() {
        tvCredit = (TextView) findViewById(R.id.tv_credit);
        tvCreditLabel = (TextView) findViewById(R.id.tv_credit_label);
        tvStatus = (TextView) findViewById(R.id.tv_status);
        tvStatusLabel = (TextView) findViewById(R.id.tv_status_label);
        tvRate = (TextView) findViewById(R.id.tv_rate);
        tvRateLabel = (TextView) findViewById(R.id.tv_rate_label);
        tvNoTime = (TextView) findViewById(R.id.tv_no_time);
        sbNoTime = (com.suke.widget.SwitchButton)
                findViewById(R.id.sb_no_time);
        ivMenu = (ImageView) findViewById(R.id.iv_menu);
        mDrawerLayout = findViewById(R.id.drwer_layout);
        tvNewRequest = findViewById(R.id.tv_new_request);
        tvRegisteredSessions = findViewById(R.id.tv_registered_sessions);
        tvRegisterNewSession = findViewById(R.id.tv_registering_new_session);
        tvSessionHandling = findViewById(R.id.tv_session_hadling);
        tvSupportTicket = findViewById(R.id.tv_support_ticket);


        tvCredit.setTypeface(getBaseFont());
        tvCreditLabel.setTypeface(getBaseFont());
        tvStatus.setTypeface(getBaseFont());
        tvStatusLabel.setTypeface(getBaseFont());
        tvRate.setTypeface(getBaseFont());
        tvRateLabel.setTypeface(getBaseFont());
        tvNoTime.setTypeface(getBaseFont());

        tvNewRequest.setTypeface(getBaseFont());
        tvRegisteredSessions.setTypeface(getBaseFont());
        tvRegisterNewSession.setTypeface(getBaseFont());
        tvSessionHandling.setTypeface(getBaseFont());
        tvSupportTicket.setTypeface(getBaseFont());


    }

    private void fillData() {

    }

    private void initListener() {
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDrawerLayout.isDrawerVisible(Gravity.RIGHT)) {
                    mDrawerLayout.closeDrawer(Gravity.RIGHT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.RIGHT);
                }


            }
        });

    }


}
