package com.example.aidar.knowledgedb.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.aidar.knowledgedb.CheckDatabaseConnectionAsyncTask;
import com.example.aidar.knowledgedb.DatabaseManager;
import com.example.aidar.knowledgedb.R;

import java.util.Timer;
import java.util.TimerTask;

public class StartupActivityActivity extends AppCompatActivity implements CheckDatabaseConnectionAsyncTask.CheckDatabaseResponces {

    DatabaseManager databaseManager;
    TextView textView;
    CheckDatabaseConnectionAsyncTask checkDatabaseConnectionAsyncTask;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_activity);
        databaseManager = DatabaseManager.getInstance();
        textView = (TextView) findViewById(R.id.network_info);
        checkDatabaseConnectionAsyncTask = new CheckDatabaseConnectionAsyncTask(this, this, this);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                checkDatabaseConnectionAsyncTask.execute();
            }
        }, 1000);
    }


    @Override
    public void alertNoInternet() {
        textView.setText("Подключитесь к интернету...");
    }

    @Override
    public void alertDownloading() {
        textView.setText("Загрузка...");
    }

    @Override
    public void onComplete() {
        startApp();
    }

    public void startApp() {
        Intent intentToStartSolveIssueActivity = new Intent(this, Main2Activity.class);
        startActivity(intentToStartSolveIssueActivity);
    }
}
