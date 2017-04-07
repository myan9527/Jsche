/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.entity;

import org.jsche.common.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author myan
 */
public class Task implements Serializable {

    private static final long serialVersionUID = -6215223549526696658L;

    public enum TaskType {
        FAMILY_ISSUE("Family Issue"), SELF_IMPROVEMENT("Self Improvement"), SOCIAL_ACTIVITY(
                "Social Activity"), WORK_TASK("Work Staff"), OTHER_ISSUE("Other Issue");

        private final String typeName;

        TaskType(String name) {
            this.typeName = name;
        }

        public String getTypeName() {
            return typeName;
        }

    }

    private int id;
    @NotEmpty
    private String taskName;
    private String description;
    private TaskType taskType;
    private int status;
    private int priority;
    @NotEmpty
    private Date startDate;
    private int duration;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
