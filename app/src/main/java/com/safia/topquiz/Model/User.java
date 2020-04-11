package com.safia.topquiz.Model;

public class User {
    private String mFirstName;
    private int mScore;

    public String getFirstName() {
        return mFirstName;
    }
    public int getScore(){
        return mScore;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }
    public void setScore(int score) {
        mScore = score;
    }


    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mScore=" + mScore +
                '}';
    }
}
