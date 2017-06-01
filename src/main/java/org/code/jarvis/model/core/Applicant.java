package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.code.jarvis.model.refdata.Address;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_applicant")
public class Applicant extends AbstractEntity {

    @JsonProperty("PERSONS")
    private List<Person> persons;
    @JsonProperty("IMAGES")
    private List<ApplicantImage> applicantImages;
    @JsonIgnore
    private ApplicantProductDetail applicantProductDetail;
    @JsonIgnore
    private Address address;
    @JsonProperty("CONTACT")
    private PersonContact personContant;
    @JsonProperty("DATE")
    private String date;
    @JsonProperty("OTHER")
    private String other;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    @JsonProperty("ID")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "app_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "app_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return desc;
    }

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<ApplicantImage> getApplicantImages() {
        return applicantImages;
    }

    public void setApplicantImages(List<ApplicantImage> applicantImages) {
        this.applicantImages = applicantImages;
    }

    @OneToOne(mappedBy = "applicant", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public ApplicantProductDetail getApplicantProductDetail() {
        return applicantProductDetail;
    }

    public void setApplicantProductDetail(ApplicantProductDetail applicantProductDetail) {
        this.applicantProductDetail = applicantProductDetail;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "add_id", referencedColumnName = "add_id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public PersonContact getPersonContant() {
        return personContant;
    }

    public void setPersonContant(PersonContact personContant) {
        this.personContant = personContant;
    }

    @Column(name = "app_date", length = 100)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "app_other", length = 100)
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @JsonProperty("PRODUCT")
    public Product product() {
        if (applicantProductDetail != null && applicantProductDetail.getProduct() != null)
            return applicantProductDetail.getProduct();
        return null;
    }

    @JsonProperty("ADDRESS")
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
    }
}
