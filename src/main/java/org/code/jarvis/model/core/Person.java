package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.code.jarvis.model.refdata.Address;
import org.code.jarvis.model.refdata.Gender;
import org.code.jarvis.model.request.RequestPerson;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_person")
public class Person extends AbstractEntity {

    @JsonProperty("NAME")
    private String name;
    @JsonProperty("FATHER_NAME")
    private String fatName;
    @JsonProperty("MOTHER_NAME")
    private String momName;
    //@JsonIgnore
    //private String dob;
    @JsonIgnore
    private Gender gender;
    //@JsonIgnore
    //private Address address;
    //@JsonIgnore
    //private PersonContact personContant;
    @JsonIgnore
    private Applicant applicant;

    public Person() {
    }

    public Person(RequestPerson requestPerson) {
        this.name = requestPerson.getName();
        this.fatName = requestPerson.getFatName();
        this.momName = requestPerson.getMomName();
        //this.dob = requestPerson.getDob();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    @JsonProperty("PRE_ID")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "per_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "per_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return desc;
    }

    @Column(name = "per_name", length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "per_fat_name", length = 50)
    public String getFatName() {
        return fatName;
    }

    public void setFatName(String fatName) {
        this.fatName = fatName;
    }

    @Column(name = "per_mom_name", length = 50)
    public String getMomName() {
        return momName;
    }

    public void setMomName(String momName) {
        this.momName = momName;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gen_id", referencedColumnName = "gen_id")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /*@Column(name = "per_dob", length = 50)
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_id", referencedColumnName = "add_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public PersonContact getPersonContant() {
        return personContant;
    }

    public void setPersonContant(PersonContact personContant) {
        this.personContant = personContant;
    }
*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @JsonProperty("GENDER")
    public String gender() {
        if (gender != null)
            return gender.getDesc();
        return "";
    }

    /*@JsonProperty("ADDRESS")
    public Map<String, Object> address() {
        if (address != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("PROVINCE", address.provinceDesc());
            map.put("DISTRICT", address.districtDesc());
            map.put("COMMUNE", address.communeDesc());
            map.put("VILLAGE", address.villageDesc());
            return map;
        }
        return null;
    }*/


}
