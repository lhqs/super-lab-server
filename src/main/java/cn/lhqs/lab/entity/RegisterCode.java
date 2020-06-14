package cn.lhqs.lab.entity;

import java.util.Date;

/**
 * author : lhqs
 * email : lhqs1314@gmail.com
 * createTime : 2018-10-25 14:43
 * description : RegisterCode.class
 * version : 1.0
 */
public class RegisterCode {
    private String registerId;
    private String registerCode;
    private String registerName;
    private String requestIp;
    private String requestAddress;
    private Date createTime;

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestAddress() {
        return requestAddress;
    }

    public void setRequestAddress(String requestAddress) {
        this.requestAddress = requestAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RegisterCode{" +
                "registerId='" + registerId + '\'' +
                ", registerCode='" + registerCode + '\'' +
                ", registerName='" + registerName + '\'' +
                ", requestIp='" + requestIp + '\'' +
                ", requestAddress='" + requestAddress + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
