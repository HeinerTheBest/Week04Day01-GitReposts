package com.mobileapps.week04day01getgitwithinternet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mobileapps.week04day01getgitwithinternet.R;
import com.mobileapps.week04day01getgitwithinternet.model.datasource.remote.httpurlconnection.HttpURLConnectionHelper;
import com.mobileapps.week04day01getgitwithinternet.model.gitresponse.GitResponse;

public class MainActivity extends AppCompatActivity
{
    String jsonResult = "";
    GitResponse gitResponse;
    ImageView imgAvatar;
    TextView tvName, tvUserId,tvRepo;
    EditText etUserToFind;
    Context context;
    ProgressBar progressBar;
    String userToFind = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAvatar = findViewById(R.id.imgAvatar);
        tvName = findViewById(R.id.tvName);
        tvUserId = findViewById(R.id.tvUserId);
        tvRepo = findViewById(R.id.tvRepo);
        etUserToFind = findViewById(R.id.etUserToFind);
        context = this;
        progressBar = findViewById(R.id.progress);

        searUserInGIt();
    }

    private void searUserInGIt()
    {
        new HttpURLConnectionAsyncTask().execute();
    }

    public void search(View view)
    {
        searUserInGIt();
    }

    public void goTORepost(View view)
    {
        Intent intent = new Intent(context,RepostListActivity.class);
        intent.putExtra("json",jsonResult);
        startActivity(intent);
    }


    public class HttpURLConnectionAsyncTask extends AsyncTask<Void, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            userToFind = etUserToFind.getText().toString();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String response = "";
            try {
                response = HttpURLConnectionHelper.getGitResponse(userToFind);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            jsonResult = s;
            gitResponse = new Gson().fromJson(jsonResult, GitResponse.class);

            if(gitResponse != null) {

                Glide
                        .with(context)
                        .load(gitResponse.getItems().get(0).getOwner().getAvatarUrl())
                        .into(imgAvatar);
                String name = gitResponse.getItems().get(0).getOwner().getLogin();
                tvName.setText(name);
                String userId = String.valueOf(gitResponse.getItems().get(0).getOwner().getId());
                tvUserId.setText(userId);
                String text = gitResponse.getTotalCount() + "\nRepositories";
                tvRepo.setText(text);
            }
            Toast.makeText(context, "User Don't Found", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);

        }
    }


}


