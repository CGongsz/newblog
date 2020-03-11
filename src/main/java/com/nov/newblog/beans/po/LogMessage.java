package com.nov.newblog.beans.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 16:09
 * @Version: 1.0
 */
public class LogMessage implements Serializable {
    private Integer id;
    private String operation;
    private Integer success;
    private String reason;
    private Date createDate;
    private String ipAddress;
    private String createBy;


    @Override
    public String toString() {
        return "LogMessage{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", success=" + success +
                ", reason='" + reason + '\'' +
                ", createDate=" + createDate +
                ", ipAddress='" + ipAddress + '\'' +
                ", createBy='" + createBy + '\'' +
                '}';
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
