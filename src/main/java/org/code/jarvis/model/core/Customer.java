package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimChheng on 6/13/2017.
 */
@Entity
@Table(name = "td_customer")
public class Customer extends AbstractEntity {

    @JsonProperty("GROOM_NAME")
    private String groomName;
    @JsonProperty("GROOM_DAD_NAME")
    private String groomDadName;
    @JsonProperty("GROOM_MOM_NAME")
    private String groomMomName;
    @JsonProperty("BRIDE_NAME")
    private String brideName;
    @JsonProperty("BRIDE_DAD_NAME")
    private String brideDadName;
    @JsonProperty("BRIDE_MOM_NAME")
    private String brideMomName;
    @JsonProperty("HOME")
    private String home;
    @JsonProperty("ADDRESS")
    private String address;
    @JsonProperty("DATE")
    private String date;
    @JsonProperty("PHONE")
    private String phone;
    @JsonProperty("FACEBOOK")
    private String fb;
    @JsonProperty("MAP")
    private String map;
    @JsonProperty("OTHER")
    private String other;
    @JsonProperty("PRODUCT")
    private Product product;
    @JsonProperty("PRODUCT_ID")
    private Long productId;
    @JsonIgnore
    private List<Image> images;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cus_id")
    @JsonProperty("ID")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "cus_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "cus_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return desc;
    }

    @Column(name = "cus_groom_name")
    public String getGroomName() {
        return groomName;
    }

    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }

    @Column(name = "cus_groom_dad_name")
    public String getGroomDadName() {
        return groomDadName;
    }

    public void setGroomDadName(String groomDadName) {
        this.groomDadName = groomDadName;
    }

    @Column(name = "cus_groom_mom_name")
    public String getGroomMomName() {
        return groomMomName;
    }

    public void setGroomMomName(String groomMomName) {
        this.groomMomName = groomMomName;
    }

    @Column(name = "cus_bride_name")
    public String getBrideName() {
        return brideName;
    }

    public void setBrideName(String brideName) {
        this.brideName = brideName;
    }

    @Column(name = "cus_bride_dad_name")
    public String getBrideDadName() {
        return brideDadName;
    }

    public void setBrideDadName(String brideDadName) {
        this.brideDadName = brideDadName;
    }

    @Column(name = "cus_bride_mom_name")
    public String getBrideMomName() {
        return brideMomName;
    }

    public void setBrideMomName(String brideMomName) {
        this.brideMomName = brideMomName;
    }

    @Column(name = "cus_home")
    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Column(name = "cus_address", length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "cus_date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "cus_phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "cus_fb")
    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    @Column(name = "cus_map")
    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    @Column(name = "cus_other")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pro_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Transient
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "td_customer_image", joinColumns = {@JoinColumn(name = "cus_id")},
            inverseJoinColumns = {@JoinColumn(name = "img_id")})
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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
