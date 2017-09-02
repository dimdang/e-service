package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by KimChheng on 5/9/2017.
 */
@MappedSuperclass
public abstract class AbstractContact extends AbstractEntity {

    @JsonProperty("PHONE1")
    protected String phone1;
    @JsonProperty("PHONE2")
    protected String phone2;
    @JsonProperty("EMAIL")
    protected String email;
    @JsonProperty("FACEBOOK")
    protected String facebook;

    @Column(name = "phone1", length = 25)
    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @Column(name = "phone2", length = 25)
    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Column(name = "email", length = 25)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "facebook", length = 25)
    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

}
