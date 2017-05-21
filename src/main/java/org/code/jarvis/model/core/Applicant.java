package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

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

    @JsonProperty("PRODUCT")
    public Product product() {
        if (applicantProductDetail != null && applicantProductDetail.getProduct() != null)
            return applicantProductDetail.getProduct();
        return null;
    }
}
