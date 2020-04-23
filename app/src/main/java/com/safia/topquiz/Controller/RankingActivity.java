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
import com.safia.topquiz.Model.User;
import com.safia.topquiz.Model.UserList;
import com.safia.topquiz.R;
import com.safia.topquiz.View.RankingAdapter;

import java.util.Collections;
import java.util.Comparator;

public class RankingActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;
    public UserList mUserList;
    public SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        loadData();
        displayRV();

        //ascendingSort Button
        Button ascendingSort = findViewById(R.id.activity_asc_btn);
        ascendingSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compareScore(new ScoreComparator());
                displayRV();
            }
        });

        //PlayAgain Button
        Button mPlayAgainButton = findViewById(R.id.activity_az_btn);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replayIntent = new Intent(RankingActivity.this, MainActivity.class);
                startActivityForResult(replayIntent, MainActivity.GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    public void displayRV() {
        RecyclerView rv = findViewById(R.id.recyclerview);

        //Binding the adapter to the rv
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new RankingAdapter(this.mUserList.getUserList());
        rv.setAdapter(mAdapter);
    }

    public void loadData (){
        mPreferences = getSharedPreferences(MainActivity.PREFERENCE_APP_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPreferences.getString(MainActivity.PREFERENCE_KEY_USER_LIST, "");
        mUserList = gson.fromJson(json, UserList.class);
    }

    public class ScoreComparator implements Comparator {

        @Override
        public int compare(Object s1, Object s2) {
            User u1 = (User) s1;
            User u2 = (User) s2;
            if (u2.getScore() > u1.getScore()){
                return 1;
            }else
                return -1;
        }
    }

    public void compareScore(Comparator<User> userComparator) {
        Collections.sort(mUserList.getUserList(), userComparator);
    }

}
