package com.example.mealerapp;

public class ComplaintData {

    private String clientId;
    private String clientName;
    private String cookId;
    private String cookName;
    private String complaint;
    private String complaintId;

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public ComplaintData(){}

    public ComplaintData(String clientId, String clientName, String cookId, String cookName, String complaint) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.cookId = cookId;
        this.cookName = cookName;
        this.complaint = complaint;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
