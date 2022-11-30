package com.example.todolist;

public class ListItem {
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    private int userID;
    private int taskID;
    private String taskTitle;
    private Boolean done;

    public ListItem(int userID, int taskID, String taskTitle, Boolean done) {
        this.userID = userID;
        this.taskID = taskID;
        this.taskTitle = taskTitle;
        this.done = done;
    }
}
