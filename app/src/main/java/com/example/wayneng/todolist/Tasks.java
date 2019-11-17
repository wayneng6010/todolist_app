package com.example.wayneng.todolist;

public class Tasks {
    public String mTitle;
    public String mDescription;
    public String mPriority;
    public boolean mIsDone;

    public Tasks(String title, String description, String priority, boolean isDone){
        mTitle = title;
        mDescription = description;
        mPriority = priority;
        mIsDone = isDone;
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

    public boolean getIsDone() { return mIsDone; }

    public void setIsDone(boolean done) { mIsDone = done; }
}
