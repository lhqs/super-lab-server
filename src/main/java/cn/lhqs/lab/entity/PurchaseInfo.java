package cn.lhqs.lab.entity;

import java.util.Date;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-11-08 2:15
 * description : PurchaseInfo.class
 * version : 1.0
 */
public class PurchaseInfo {
    private String purchaseId;
    private String userId;
    private String username;
    private String projectIllustration;
    private String purchaseType;
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
    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
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

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
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
                "purchaseId='" + purchaseId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectIllustration='" + projectIllustration + '\'' +
                ", purchaseType='" + purchaseType + '\'' +
                ", amount=" + amount +
                ", incident='" + incident + '\'' +
                ", takeOver='" + takeOver + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
