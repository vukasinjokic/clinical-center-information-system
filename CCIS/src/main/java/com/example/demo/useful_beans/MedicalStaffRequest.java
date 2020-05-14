package com.example.demo.useful_beans;

import java.util.Date;

public class MedicalStaffRequest {

    public enum RequestType{Vacation, Leave};

    private Date fromDate;
    private Date toDate;
    private RequestType type;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }
}
