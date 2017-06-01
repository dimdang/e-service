package org.code.jarvis.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by KimChheng on 5/18/2017.
 */
@JsonPropertyOrder({"PRODUCT_ID", "APPLICANTS"})
public class RequestApplicant {

    @JsonProperty("PRODUCT_ID")
    private int productId;
    @JsonProperty("APPLICANTS")
    List<RequestPerson> persons;
    @JsonProperty("DATE")
    private String date;
    @JsonProperty("PROVINCE")
    private int province;
    @JsonProperty("COMMUNE")
    private int commune;
    @JsonProperty("DISTRICT")
    private int district;
    @JsonProperty("VILLAGE")
    private int village;
    @JsonProperty("PHONE1")
    private String phone1;
    @JsonProperty("PHONE2")
    private String phone2;
    @JsonProperty("PHONE3")
    private String phone3;
    @JsonProperty("EMAIL")
    private String email;
    @JsonProperty("FACEBOOK")
    private String facebook;
    @JsonProperty("OTHER")
    private String other;

    public RequestApplicant() {
    }

    public RequestApplicant(List<RequestPerson> persons) {
        this.persons = persons;
    }

    public List<RequestPerson> getPersons() {
        return persons;
    }

    public void setPersons(List<RequestPerson> persons) {
        this.persons = persons;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCommune() {
        return commune;
    }

    public void setCommune(int commune) {
        this.commune = commune;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getVillage() {
        return village;
    }

    public void setVillage(int village) {
        this.village = village;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
