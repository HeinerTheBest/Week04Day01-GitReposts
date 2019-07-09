package com.mobileapps.week04day01getgitwithinternet.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mobileapps.week04day01getgitwithinternet.R;
import com.mobileapps.week04day01getgitwithinternet.adapters.RepostListAdapter;
import com.mobileapps.week04day01getgitwithinternet.model.gitresponse.GitResponse;

public class RepostListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repost_list);
        recyclerView = findViewById(R.id.rvRepostList);

        Intent intent = getIntent();

        String json = intent.getStringExtra("json");
        GitResponse gitResponse = new Gson().fromJson(json, GitResponse.class);
        RepostListAdapter repostListAdapter = new RepostListAdapter(gitResponse.getItems());
        recyclerView.setAdapter(repostListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
