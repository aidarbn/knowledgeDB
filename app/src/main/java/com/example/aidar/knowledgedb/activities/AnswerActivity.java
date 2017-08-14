package com.example.aidar.knowledgedb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.aidar.knowledgedb.R;

public class AnswerActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TextView answerTextView;
    NestedScrollView scrollView;

    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean controlsHidden = false;

//    Button helpedButton;
//    Button notHelpedButton;
//    Button startNewSearchButton;
//    Button dontStartnewSearchButton;
//    Button continueSearchButton;
//    Button dontContinueSearchButton;
//    ConstraintLayout footerHelpedConstraintlayout;
//    ConstraintLayout footerNewSearchConstraintlayout;
//    ConstraintLayout footerContiuneSearchConstraintlayout;
//    FrameLayout footerFrameLayout;
//    long fadeInDuration = 150;
//    long fadeOutDuration  = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //setTitle(getString(R.string.app_name));
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        answerTextView = (TextView) findViewById(R.id.answerTextView);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int dx=oldScrollX-scrollX;
                int dy=oldScrollY-scrollY;
                
                if (scrolledDistance > HIDE_THRESHOLD && controlsHidden) {
                    onShow();
                    controlsHidden = false;
                    scrolledDistance = 0;
                } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsHidden) {
                    onHide();
                    controlsHidden = true;
                    scrolledDistance = 0;
                }

                if((controlsHidden && dy>0) || (!controlsHidden && dy<0)) {
                    scrolledDistance += dy;
                }
            }
            public void onHide(){hideViews();}
            public void onShow(){showViews();}
        });
//        helpedButton = (Button) findViewById(R.id.helped_button);
//        notHelpedButton = (Button) findViewById(R.id.not_helped_button);
//        startNewSearchButton = (Button) findViewById(R.id.start_new_search);
//        dontStartnewSearchButton = (Button) findViewById(R.id.dont_start_new_search);
//        continueSearchButton = (Button) findViewById(R.id.continue_search);
//        dontContinueSearchButton = (Button) findViewById(R.id.dont_continue_search);
//
//        footerFrameLayout = (FrameLayout) findViewById(R.id.footer);
//        footerHelpedConstraintlayout = (ConstraintLayout) findViewById(R.id.footer_helped);
//        footerNewSearchConstraintlayout = (ConstraintLayout) findViewById(R.id.footer_start_new_search);
//        footerContiuneSearchConstraintlayout = (ConstraintLayout) findViewById(R.id.footer_continue_search);

//        final Animation fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator());
//        fadeIn.setStartOffset(fadeOutDuration);
//        fadeIn.setDuration(fadeInDuration);

//        final Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator());
//        fadeOut.setDuration(fadeOutDuration);

        String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        answerTextView.setText(text);
        answerTextView.setMovementMethod(new ScrollingMovementMethod());
//
//        helpedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                footerHelpedConstraintlayout.startAnimation(fadeOut);
//                footerHelpedConstraintlayout.setVisibility(View.INVISIBLE);
//
//                footerNewSearchConstraintlayout.startAnimation(fadeIn);
//                footerNewSearchConstraintlayout.setVisibility(View.VISIBLE);
//
//            }
//        });
//
//        notHelpedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                footerHelpedConstraintlayout.startAnimation(fadeOut);
//                footerHelpedConstraintlayout.setVisibility(View.INVISIBLE);
//
//                footerContiuneSearchConstraintlayout.startAnimation(fadeIn);
//                footerContiuneSearchConstraintlayout.setVisibility(View.VISIBLE);
//            }
//        });
//
//        startNewSearchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startNewMainActivity();
//            }
//        });
//
//        dontStartnewSearchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                footerFrameLayout.animate().translationY(96);
//                footerFrameLayout.startAnimation(fadeOut);
//                footerFrameLayout.setVisibility(View.INVISIBLE);
//            }
//        });
//
//        continueSearchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        dontContinueSearchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                footerFrameLayout.animate().translationY(96);
//                footerFrameLayout.startAnimation(fadeOut);
//                footerFrameLayout.setVisibility(View.INVISIBLE);
//            }
//        });
//    }
//
//    void startNewMainActivity() {
//        Intent intentToStartSolveIssueActivity = new Intent(this, MainActivity.class);
//        startActivity(intentToStartSolveIssueActivity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return(super.onOptionsItemSelected(item));
    }

    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
//        int fabBottomMargin = lp.bottomMargin;
//        mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//        mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }
}
