package org.code.jarvis.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by KimChheng on 5/18/2017.
 */
@JsonPropertyOrder({"NAME", "FATHER_NAME", "MOTHER_NAME", "BIRTH_DATE", "GENDER"})
public class RequestPerson {

    @JsonProperty("NAME")
    private String name;
    @JsonProperty("FATHER_NAME")
    private String fatName;
    @JsonProperty("MOTHER_NAME")
    private String momName;
    @JsonProperty("BIRTH_DATE")
    private String dob;
    @JsonProperty("GENDER")
    private int gender;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatName() {
        return fatName;
    }

    public void setFatName(String fatName) {
        this.fatName = fatName;
    }

    public String getMomName() {
        return momName;
    }

    public void setMomName(String momName) {
        this.momName = momName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
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
}
