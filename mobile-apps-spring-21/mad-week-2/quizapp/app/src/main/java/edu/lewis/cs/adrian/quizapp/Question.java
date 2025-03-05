package edu.lewis.cs.adrian.quizapp;

public class Question {

    int testResId; // this var is ging ti strof th eid to the question in the string .xml
    boolean answer;// answer to thw question (T/F)

    //constructor class for the var
    public Question(int testResId, boolean answer) {
        this.testResId = testResId;
        this.answer = answer;
    }

    //getters
    public int getTestResId() {
        return testResId;
    }

    public boolean isAnswer() {
        return answer;
    }

    //setter
    public void setTestResId(int testResId) {
        this.testResId = testResId;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

}//end of class
