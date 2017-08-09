package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_product_image")
public class ProductImage extends AbstractEntity {

    @JsonIgnore
    private Product product;
    @JsonIgnore
    private Image image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "pro_img_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "pro_img_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "pro_img_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return desc;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "img_id", nullable = false)
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    /*@JsonProperty("PATH")
    @Transient
    public String getPath() {
        if (image != null)
            return image.getPath();
        return "";
    }*/

    /*@JsonProperty("NAME")
    @Transient
    public String getName() {
        if (image != null)
            return image.getName();
        return "";
    }*/
}
