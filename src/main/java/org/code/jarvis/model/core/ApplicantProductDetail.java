package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/18/2017.
 */
@Entity
@Table(name = "td_applicant_product_detail")
public class ApplicantProductDetail extends AbstractEntity {

    @JsonIgnore
    private Applicant applicant;
    @JsonIgnore
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_det_pro_id")
    @JsonIgnore
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "per_det_pro_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "per_det_pro_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return desc;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", referencedColumnName = "app_id")
    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
