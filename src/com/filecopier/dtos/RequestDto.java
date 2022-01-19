/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filecopier.dtos;

/**
 *
 * @author cenebeli
 */
public class RequestDto {
    private String requestId;
    private String spoolStatus;
    private String spoolDate;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSpoolStatus() {
        return spoolStatus;
    }

    public void setSpoolStatus(String spoolStatus) {
        this.spoolStatus = spoolStatus;
    }

    public String getSpoolDate() {
        return spoolDate;
    }

    public void setSpoolDate(String spoolDate) {
        this.spoolDate = spoolDate;
    }
}
