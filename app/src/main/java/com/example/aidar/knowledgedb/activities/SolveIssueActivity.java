package com.example.aidar.knowledgedb.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.aidar.knowledgedb.DatabaseManager;
import com.example.aidar.knowledgedb.Question;
import com.example.aidar.knowledgedb.R;
import com.example.aidar.knowledgedb.SwipeStackAdapter;

import link.fls.swipestack.SwipeStack;

public class SolveIssueActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener, SwipeStackAdapter.ButtonSwipeOnClickHandler {

    SwipeStack swipeStack;
    SwipeStackAdapter swipeStackAdapter;
    DatabaseManager databaseManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_issue);

        swipeStack = (SwipeStack) findViewById(R.id.swipeStack);
        swipeStack.setListener(this);
        String companyName = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        String issue = getIntent().getStringExtra("ISSUE");
        if(issue==null)issue="";
        databaseManager = new DatabaseManager();
        swipeStackAdapter = new SwipeStackAdapter(this, this);
        databaseManager.findAllQuestionsInCompany(companyName, swipeStackAdapter, issue);
        swipeStack.setAdapter(swipeStackAdapter);
    }



    @Override
    public void onViewSwipedToLeft(int position) {
        Log.i("SWIPED LEFT", String.valueOf(position));
    }

    @Override
    public void onViewSwipedToRight(int position) {
        Log.i("SWIPED RIGHT", String.valueOf(position));
        startAnswerActivity(position);
    }

    @Override
    public void onStackEmpty() {
    }

    private void startAnswerActivity(int position){
        Intent intentToStartSolveIssueActivity = new Intent(this, AnswerActivity.class);
        Question question = (Question) swipeStackAdapter.getItem(position);
        intentToStartSolveIssueActivity.putExtra(Intent.EXTRA_TEXT, question.getAnswer());
        startActivity(intentToStartSolveIssueActivity);
    }


    @Override
    public void yesButtonSwiper() {
        swipeStack.swipeTopViewToRight();
    }

    @Override
    public void noButtonSwiper() {
        swipeStack.swipeTopViewToLeft();
    }
}