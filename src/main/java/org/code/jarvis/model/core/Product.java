package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
    private Integer size;
    @JsonProperty("PRICE")
    private String price;
    @JsonProperty("CONTACT")
    private ProductContact contact;
    @JsonIgnore
    private List<ProductImage> productImages;

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

    /*@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "col_id", referencedColumnName = "col_id")
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }*/

    @Column(name = "pro_color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "pro_size", nullable = false)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    @JsonProperty("IMAGES")
    @Transient
    public List<Long> getImageId() {
        List<Long> list = new ArrayList<>();
        if (productImages != null && !productImages.isEmpty()) {
            for (ProductImage productImage : productImages) {
                if (productImage.getImage() != null)
                    list.add(productImage.getImage().getId());
            }
        }
        return list;
    }

}
