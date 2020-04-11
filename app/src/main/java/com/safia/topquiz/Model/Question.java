package com.safia.topquiz.Model;

import java.util.List;

public class Question {
    private String mQuestion; //Question
    private List<String> mChoiceList; //List of question
    private int mAnswerIndex; //Response's index from the previous list

    public String getQuestion() {
        return mQuestion;
    } //get Question
    public List<String> getChoiceList() {
        return mChoiceList;
    } //Listes Questions
    public int getAnswerIndex() {
        return mAnswerIndex;
    } //Index de la réonse

    public void setQuestion(String question) {
        mQuestion = question;
    }
    public void setChoiceList(List<String> choiceList) {
        mChoiceList = choiceList;
    }
    public void setAnswerIndex(int answerIndex) {
        mAnswerIndex = answerIndex;
    }

    public Question (String question,List<String>mChoice,int answerIndex){
        this.setQuestion(question);
        this.setChoiceList(mChoice);
        this.setAnswerIndex(answerIndex);

    }

}
