package org.code.jarvis.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by KimChheng on 5/18/2017.
 */
public class RequestPerson {

    @JsonProperty("NAME")
    private String name;
    @JsonProperty("FATHER_NAME")
    private String fatName;
    @JsonProperty("MOTHER_NAME")
    private String momName;
    //@JsonProperty("BIRTH_DATE")
    //private String dob;
    @JsonProperty("GENDER")
    private int gender;

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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
