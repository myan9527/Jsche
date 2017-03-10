/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author myan
 */
@Entity
@Table(name = "tasks")
public class Task implements Serializable, Comparable<Task> {
	private static final long serialVersionUID = -6215223549526696658L;

	public enum TaskType {
		FAMILY_ISSUE("Family Issue"), SELF_IMPROVEMENT("Self Improvement"), SOCIAL_ACTIVITY(
		        "Social Activity"), WORK_TASK("Work Task"), OTHER_ISSUR("Other Issue");

		private String typeName;

		TaskType(String name) {
			this.typeName = name;
		}

		public String getTypeName() {
			return typeName;
		}

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String taskName;
	private TaskType taskType;
	private int status;
	private int priority;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date startDate;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date endDate;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(Task t) {
		return this.startDate.before(t.getStartDate()) ? 0 : -1;
	}

}
