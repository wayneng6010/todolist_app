package com.example.wayneng.todolist;

public class Tasks {
    private String mTitle;
    private String mDescription;
    private String mPriority;

    public Tasks(String title, String description, String priority){
        mTitle = title;
        mDescription = description;
        mPriority = priority;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setPriority(String priority) {
        mPriority = priority;
    }
}
