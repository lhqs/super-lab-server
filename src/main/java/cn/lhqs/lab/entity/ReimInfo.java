package cn.lhqs.lab.entity;

import java.util.Date;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-08 2:15
 * description : ReimInfo.class
 * version : 1.0
 */
public class ReimInfo {
    private String reimId;
    private String userId;
    private String username;
    private String projectIllustration;
    private String reimbursementType;
    private double amount;
    private String incident;
    private String takeOver;
    private Date createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReimId() {
        return reimId;
    }

    public void setReimId(String reimId) {
        this.reimId = reimId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectIllustration() {
        return projectIllustration;
    }

    public void setProjectIllustration(String projectIllustration) {
        this.projectIllustration = projectIllustration;
    }

    public String getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(String reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getTakeOver() {
        return takeOver;
    }

    public void setTakeOver(String takeOver) {
        this.takeOver = takeOver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ReimInfo{" +
                "reimId='" + reimId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectIllustration='" + projectIllustration + '\'' +
                ", reimbursementType='" + reimbursementType + '\'' +
                ", amount=" + amount +
                ", incident='" + incident + '\'' +
                ", takeOver='" + takeOver + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
