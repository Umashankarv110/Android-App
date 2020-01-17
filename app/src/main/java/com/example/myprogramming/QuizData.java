package com.example.myprogramming;

public class QuizData {

    private String title,defination,code,output;
    //delete
    private String key;


    public QuizData(){
    }

    public QuizData(String title, String defination, String code, String output) {
        this.title = title;
        this.defination = defination;
        this.code = code;
        this.output = output;
    }

    public String getTitle() {
        return title;
    }

    public String getDefination() {
        return defination;
    }

    public String getCode() {
        return code;
    }

    public String getOutput() {
        return output;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
