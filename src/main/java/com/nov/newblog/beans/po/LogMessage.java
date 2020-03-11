package com.nov.newblog.beans.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 16:09
 * @Version: 1.0
 */
public class LogMessage implements Serializable {
    private Long id;
    private String operation;
    private Integer success;
    private String reason;
    private Date date;
    private String ipAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", success=" + success +
                ", reason='" + reason + '\'' +
                ", date=" + date +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
