package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_product_contact")
public class ProductContact extends AbstractContact {

    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_con_id")
    @JsonIgnore
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "pro_con_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "pro_con_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return desc;
    }

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
