package org.code.jarvis.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by KimChheng on 6/13/2017.
 */
public class RequestCustomer {

    @JsonProperty("GROOM_NAME")
    private String groomName;
    @JsonProperty("GROOM_DAD_NAME")
    private String groomDadName;
    @JsonProperty("GROOM_MOM_NAME")
    private String groomMomName;
    @JsonProperty("BRIDE_NAME")
    private String brideName;
    @JsonProperty("BRIDE_DAD_NAME")
    private String brideDadName;
    @JsonProperty("BRIDE_MOM_NAME")
    private String brideMomName;
    @JsonProperty("HOME")
    private String home;
    @JsonProperty("ADDRESS")
    private String address;
    @JsonProperty("DATE")
    private String date;
    @JsonProperty("PHONE")
    private String phone;
    @JsonProperty("OTHER")
    private String other;
    @JsonProperty("PRODUCT_ID")
    private int productId;

    public String getGroomName() {
        return groomName;
    }

    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }

    public String getGroomDadName() {
        return groomDadName;
    }

    public void setGroomDadName(String groomDadName) {
        this.groomDadName = groomDadName;
    }

    public String getGroomMomName() {
        return groomMomName;
    }

    public void setGroomMomName(String groomMomName) {
        this.groomMomName = groomMomName;
    }

    public String getBrideName() {
        return brideName;
    }

    public void setBrideName(String brideName) {
        this.brideName = brideName;
    }

    public String getBrideDadName() {
        return brideDadName;
    }

    public void setBrideDadName(String brideDadName) {
        this.brideDadName = brideDadName;
    }

    public String getBrideMomName() {
        return brideMomName;
    }

    public void setBrideMomName(String brideMomName) {
        this.brideMomName = brideMomName;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
