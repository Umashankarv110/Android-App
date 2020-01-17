package com.example.myprogramming;

public class QuizInterviewData {
    private String title, answer, example, quizkey;

    public QuizInterviewData() {
    }

    public QuizInterviewData(String title, String answer, String example) {
        this.title = title;
        this.answer = answer;
        this.example = example;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }


    public String getQuizkey() {
        return quizkey;
    }

    public void setQuizkey(String quizkey) {
        this.quizkey = quizkey;
    }

}
