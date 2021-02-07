package com.pradyotprkshprksh.credassignment.screen.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.pradyotprkshprksh.credassignment.R;

/**
 * <h>MainActivity</h>
 * <p>Initial Page of the Screen</p>
 */
public class MainActivity extends AppCompatActivity {

    // Different views
    CardView cvFirstView;
    CardView cvSecondView;
    CardView cvThirdView;
    Button btViewChanger;
    TextView tvFirst;
    TextView tvSecond;

    // Max height of the views
    int maxFirstViewHeight = 0;
    int maxSecondViewHeight = 0;
    int maxThirdViewHeight = 0;

    // If the animations is still going
    boolean isAnimationOnGoing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvFirstView = findViewById(R.id.cvFirstView);
        cvSecondView = findViewById(R.id.cvSecondView);
        cvThirdView = findViewById(R.id.cvThirdView);
        btViewChanger = findViewById(R.id.btViewChanger);
        tvFirst = findViewById(R.id.tvFirst);
        tvSecond = findViewById(R.id.tvSecond);

        initUi();
        onClickListener();
    }

    /**
     * <h>On Click Listener</h>
     * <p>Different on click listener in the screen</p>
     */
    private void onClickListener() {
        cvFirstView.setOnClickListener(v -> {
            if (isAnimationOnGoing) return;
            firstViewChanges();
        });

        cvSecondView.setOnClickListener(v -> {
            if (isAnimationOnGoing) return;
            secondViewChanges();
        });

        btViewChanger.setOnClickListener(v -> {
            if (isAnimationOnGoing) return;
            if (cvSecondView.getVisibility() == View.VISIBLE) {
                if (cvThirdView.getVisibility() == View.GONE) {
                    thirdViewChanges();
                } else {
                    firstViewChanges();
                }
            } else if (cvSecondView.getVisibility() == View.GONE) {
                slideUp(cvSecondView, maxSecondViewHeight);
                secondViewChanges();
            }
        });
    }

    /**
     * <h>Third View Changes</h>
     * <p>When third view is visible the following changes will occur.</p>
     */
    private void thirdViewChanges() {
        slideUp(cvThirdView, maxThirdViewHeight);
        btViewChanger.setText(getString(R.string.tap_for_1_click_kyc));
        tvSecond.setText(getString(R.string.emi));
    }

    /**
     * <h>Second View Changes</h>
     * <p>When second view is visible the following changes will occur.</p>
     */
    private void secondViewChanges() {
        if (cvThirdView.getVisibility() == View.VISIBLE) {
            slideDown(cvThirdView, maxThirdViewHeight);
        }
        btViewChanger.setText(getString(R.string.select_your_bank_account));
        tvSecond.setText(getString(R.string.how_do_you_wish_to_repay));
        tvFirst.setText(getString(R.string.amount));
    }

    /**
     * <h>First View Changes</h>
     * <p>When first view is visible the following changes will occur.</p>
     */
    private void firstViewChanges() {
        btViewChanger.setText(getString(R.string.proceed_to_emi_selection));
        tvFirst.setText(getString(R.string.nikunj_how_much_do_you_need));
        if (cvSecondView.getVisibility() == View.VISIBLE) {
            slideDown(cvSecondView, maxSecondViewHeight);
        }
        if (cvThirdView.getVisibility() == View.VISIBLE) {
            slideDown(cvThirdView, maxThirdViewHeight);
        }
    }

    /**
     * <h>Slide Up</h>
     * <p>Slide up the view</p>
     * @param view The view which needs to be slided up.
     * @param height The height till which the view has to be slided up.
     */
    public void slideUp(View view, int height) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                height,
                0);
        animate.setDuration(500);
        animate.setFillAfter(true);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimationOnGoing = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimationOnGoing = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animate);
    }

    /**
     * <h>Slide Down</h>
     * <p>Slide down the view</p>
     * @param view The view which needs to be slided down.
     * @param height The height till which the view has to be slided down.
     */
    public void slideDown(View view, int height) {
        TranslateAnimation animate = new TranslateAnimation(
                0,
                0,
                0,
                height);
        animate.setDuration(500);
        animate.setFillAfter(true);
        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimationOnGoing = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimationOnGoing = false;
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animate);
    }

    /**
     * <h>Initialize UI</h>
     * <p>Initialize the UI.</p>
     */
    private void initUi() {
        cvFirstView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                cvFirstView.getViewTreeObserver().removeOnPreDrawListener(this);
                maxFirstViewHeight = cvFirstView.getHeight();
                ViewGroup.LayoutParams layoutParams = cvFirstView.getLayoutParams();
                layoutParams.height = maxFirstViewHeight;
                cvFirstView.setLayoutParams(layoutParams);
                return true;
            }
        });
        cvSecondView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                cvSecondView.getViewTreeObserver().removeOnPreDrawListener(this);
                maxSecondViewHeight = cvSecondView.getHeight();
                ViewGroup.LayoutParams layoutParams = cvSecondView.getLayoutParams();
                layoutParams.height = maxSecondViewHeight;
                cvSecondView.setLayoutParams(layoutParams);
                cvSecondView.setVisibility(View.GONE);
                return true;
            }
        });
        cvThirdView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                cvThirdView.getViewTreeObserver().removeOnPreDrawListener(this);
                maxThirdViewHeight = cvThirdView.getHeight();
                ViewGroup.LayoutParams layoutParams = cvThirdView.getLayoutParams();
                layoutParams.height = maxThirdViewHeight;
                cvThirdView.setLayoutParams(layoutParams);
                cvThirdView.setVisibility(View.GONE);
                return true;
            }
        });
    }
}