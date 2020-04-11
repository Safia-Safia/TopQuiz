package com.safia.topquiz.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.safia.topquiz.Model.User;
import com.safia.topquiz.Model.UserList;
import com.safia.topquiz.R;

public class MainActivity extends AppCompatActivity {


    private TextView mGreetingTxt;
    private EditText mNameInput;
    private Button mPlayButton, mPlayButton2;

    public UserList mUserList;
    public User mUser;

    private SharedPreferences mPreferences;

    public static final String PREFERENCE_KEY_NAME = "PREFERENCE_KEY_NAME";
    public static final String PREFERENCE_KEY_SCORE = "REFERENCE_KEY_SCORE";
    public static final String PREFERENCE_KEY_USER= "PREFERENCE_KEY_USER";

    public static final int GAME_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity :: onCreate()");

        mPreferences = getSharedPreferences("Top_Quiz", MODE_PRIVATE);


        mGreetingTxt = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activiy_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);
        mPlayButton2 = findViewById(R.id.activity_main_play_btn2);
        mPlayButton.setEnabled(false);


        greetUser();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mNameInput.getText().toString();
                mUser = new User();
                mUser.setFirstName(firstName);

                mPreferences.edit().putString(PREFERENCE_KEY_NAME, mUser.getFirstName()).apply();

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        mPlayButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreActivityIntent = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(scoreActivityIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREFERENCE_KEY_SCORE, mUser.getScore()).apply();
            greetUser();
            mUser.setScore(score);
            saveData();
        }
    }

    public void saveData(){
        //SAUVEGARDE DES PREFERENCES POUR LA USERLIST
        Gson gson = new Gson();
        String json = mPreferences.getString(PREFERENCE_KEY_USER, "");

        mUserList = gson.fromJson(json, UserList.class);

        mUserList.addUser(mUser);
        json = gson.toJson(mUserList);
        mPreferences.edit().putString(PREFERENCE_KEY_USER, json).apply();

    }

    private void greetUser() {

        String firstname = mPreferences.getString(PREFERENCE_KEY_NAME, "Unknown");
        int score = mPreferences.getInt(PREFERENCE_KEY_SCORE, 0);

        String welcomeBack = "\rWelcome back, " + firstname + " !" +
                "\r\nYour last score was " + score +
                ", will you do better this time ?";
        mGreetingTxt.setText(welcomeBack);
        mNameInput.setText(firstname);
        mPlayButton.setEnabled(true);
    }
}
