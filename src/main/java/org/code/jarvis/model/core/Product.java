package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimChheng on 5/9/2017.
 */
@Entity
@Table(name = "td_product")
@JsonPropertyOrder({"ID", "CODE", "SIZE", "PRICE", "COLOR", "CONTACT"})
public class Product extends AbstractEntity {

    @JsonProperty("COLOR")
    private String color;
    @JsonProperty("SIZE")
    private String size;
    @JsonProperty("PRICE")
    private String price;
    @JsonProperty("CONTACT")
    private ProductContact contact;
    @JsonProperty("TYPE")
    private EProductType productType;
    @JsonIgnore
    private List<Image> images;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    @JsonProperty("ID")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "pro_code", length = 50)
    @JsonProperty("CODE")
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "pro_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return null;
    }

    @Column(name = "pro_color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "pro_size", nullable = false)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "pro_price", nullable = false)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    public ProductContact getContact() {
        return contact;
    }

    public void setContact(ProductContact contact) {
        this.contact = contact;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "td_product_image", joinColumns = {@JoinColumn(name = "pro_id")},
            inverseJoinColumns = {@JoinColumn(name = "img_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "pro_type")
    public EProductType getProductType() {
        return productType;
    }

    public void setProductType(EProductType productType) {
        this.productType = productType;
    }

    @JsonProperty("IMAGES")
    @Transient
    public List<Long> getImageId() {
        List<Long> list = new ArrayList<>();
        if (images != null && !images.isEmpty()) {
            for (Image image : images) {
                list.add(image.getId());
            }
        }
        return list;
    }
}
