package cn.lhqs.lab.entity;

import java.util.Arrays;
import java.util.Date;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-09-05 0:11
 * description : TaskListVO.class
 * version : 1.0
 */
public class TaskListVO {
    private String taskName;
    private String taskType;
    private String[] memberName;
    private Date startDate;
    private Date endDate;
    private String describe;

    private String producer;
    private String address;
    private double budget;

    private int isEmergency;

    public int getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(int isEmergency) {
        this.isEmergency = isEmergency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String[] getMemberName() {
        return memberName;
    }

    public void setMemberName(String[] memberName) {
        this.memberName = memberName;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "TaskListVO{" +
                "taskName='" + taskName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", memberName=" + Arrays.toString(memberName) +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", describe='" + describe + '\'' +
                ", producer='" + producer + '\'' +
                ", address='" + address + '\'' +
                ", budget=" + budget +
                '}';
    }
}
