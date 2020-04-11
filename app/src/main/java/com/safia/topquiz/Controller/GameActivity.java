package com.safia.topquiz.Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.safia.topquiz.Model.Question;
import com.safia.topquiz.Model.QuestionBank;
import com.safia.topquiz.R;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mQuestionTxt;
    private Button mAnswer1btn,mAnswer2btn,mAnswer3btn, mAnswer4btn;
    boolean mEnableTouchEvent = true;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    public static int mScore;

    private int mNumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "current_score";
    public static final String BUNDLE_STATE_QUESTION = "current_question";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        System.out.println("GameActivity :: onCeate()");
        mScore= 0;
        mNumberOfQuestions = 5;

        mQuestionTxt =findViewById(R.id.activity_game_question_text);
        mAnswer1btn =findViewById(R.id.activity_game_answer1_btn);
        mAnswer2btn =findViewById(R.id.activity_game_answer2_btn);
        mAnswer3btn =findViewById(R.id.activity_game_answer3_btn);
        mAnswer4btn =findViewById(R.id.activity_game_answer4_btn);

        mAnswer1btn.setOnClickListener(this);
        mAnswer2btn.setOnClickListener(this);
        mAnswer3btn.setOnClickListener(this);
        mAnswer4btn.setOnClickListener(this);

        mAnswer1btn.setTag(0);
        mAnswer2btn.setTag(1);
        mAnswer3btn.setTag(2);
        mAnswer4btn.setTag(3);

        mQuestionBank= this.generateQuestion();
        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 5;
        }
        mCurrentQuestion =mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if(responseIndex == mCurrentQuestion.getAnswerIndex()){
            //Good Answer
            Toast.makeText(this,"Correct!",Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            //Wrong Answer
            Toast.makeText(this,"Nope !",Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvent = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvent = true;
                if(--mNumberOfQuestions == 0){
                    endGame();
                }else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        },100);


    }
    private void endGame () {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Finish!")
                .setMessage("Your score is "+mScore+ " !")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE,mScore);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .create()
                .show();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvent && super.dispatchTouchEvent(ev);
    }

    private void displayQuestion(final Question question) {
        // Set the text for the question text view and the four buttons
        mQuestionTxt.setText(question.getQuestion());
        mAnswer1btn.setText(question.getChoiceList().get(0));
        mAnswer2btn.setText(question.getChoiceList().get(1));
        mAnswer3btn.setText(question.getChoiceList().get(2));
        mAnswer4btn.setText(question.getChoiceList().get(3));

    }
    private QuestionBank generateQuestion() {
        Question question1 = new Question("Who is the 10 doctor in the tvshow Doctor Who?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "David Tenant", "Matt Smith"), 3);

        Question question2 = new Question("Who was the first one to kill Krilin?",
                Arrays.asList("Piccolo", "Daimaou Piccolo", "Freezer", "Vegeta"), 1);

        Question question3 = new Question("What is the color of the whit horse of Henry IV?",
                Arrays.asList("Red", "Wait","White", "Wouayt"), 2);

        Question question4 = new Question("1+1 =",
                Arrays.asList("1", "3","2", "Yellow"), 2);

        Question question5 = new Question("Who's the 6th Hokage in Konoha",
                Arrays.asList("Danzo", "Kakashi","Naruto", "Tsunade"), 1);
        return new QuestionBank(Arrays.asList(question1,question2, question3,question4,question5));
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity :: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity :: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity :: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity :: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity :: onDestroy");
    }


}
