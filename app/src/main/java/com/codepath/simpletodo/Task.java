package com.codepath.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by Saranu on 2/6/17.
 */
@Table(database = MyDatabase.class)
public class Task extends BaseModel implements Serializable {


    @Column
    @PrimaryKey
    private int id;
    @Column
    private String taskName;
    @Column
    private String taskDate;
    @Column
    private String taskDetail;
    @Column
    private String taskPriority;
    @Column
    private String taskStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }


    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public String getTaskPriority() { return taskPriority;}

    public void setTaskPriority(String taskPriority) {this.taskPriority = taskPriority;}

    public String getTaskStatus() { return taskStatus;  }

    public void setTaskStatus(String taskStatus) { this.taskStatus = taskStatus; }
}



