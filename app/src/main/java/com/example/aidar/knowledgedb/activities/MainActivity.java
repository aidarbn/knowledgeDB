package com.example.aidar.knowledgedb.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.aidar.knowledgedb.DatabaseManager2;
import com.example.aidar.knowledgedb.R;
import com.example.aidar.knowledgedb.RecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener, RecyclerViewAdapter.RecyclerViewAdapterOnClickHandler {

    MaterialSearchBar searchBar;
    RecyclerView popularCompaniesRV;
    RecyclerViewAdapter rvAdapter;
    List<String> popularCompaniesList;
    DatabaseManager2 databaseManager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseManager2 = DatabaseManager2.getInstance();

        setContentView(R.layout.activity_main);
        searchBar = (MaterialSearchBar) findViewById(R.id.search_company_searchBar);
        searchBar.setOnSearchActionListener(this);
        searchBar.setElevation(0);
        popularCompaniesRV = (RecyclerView) findViewById(R.id.popular_companies_recyclerView);
        popularCompaniesRV.setHasFixedSize(true);
        popularCompaniesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        popularCompaniesList = new ArrayList<String>();
        for (int i = 0; i < 5; i++) popularCompaniesList.add("Казахтелеком");
        rvAdapter = new RecyclerViewAdapter(popularCompaniesList, this);
        popularCompaniesRV.setAdapter(rvAdapter);
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {
//        String s = enabled ? "enabled" : "disabled";
//        Toast.makeText(MainActivity.this, "Search " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        DataSnapshot companySnapshot = databaseManager2.findCompanyByName(text.toString());
        startIssueActivity(companySnapshot);
    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }

    @Override
    public void onClick(String listItemName) {
        DataSnapshot companySnapshot = databaseManager2.findCompanyByName(listItemName);
        startIssueActivity(companySnapshot);
    }

    private void startIssueActivity(DataSnapshot dataSnapshot) {
        Intent intentToStartIssueActivity = new Intent(this, IssueActivity.class);
        databaseManager2.setTransferSnapshot(dataSnapshot);
        startActivity(intentToStartIssueActivity);

    }
}
