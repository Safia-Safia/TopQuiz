package com.safia.topquiz.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.safia.topquiz.Model.UserList;
import com.safia.topquiz.R;
import com.safia.topquiz.View.RankingAdapter;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;
    public UserList mUserList;
    public SharedPreferences mPreferences;
    private Button mBetterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        RecyclerView rv = findViewById(R.id.recyclerview);

        loadData();
        //Binding the adapter to the rv
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new RankingAdapter(this.mUserList.getUserList());
        rv.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mBetterButton = findViewById(R.id.activity_better_btn);
        mBetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreActivityIntent = new Intent(RankingActivity.this, GameActivity.class);
                startActivity(scoreActivityIntent);
            }
        });
    }


    public void loadData (){
        mPreferences = getSharedPreferences("Top_Quiz", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPreferences.getString(MainActivity.PREFERENCE_KEY_USER, "");
        mUserList = gson.fromJson(json, UserList.class);
    }

}
