package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.code.jarvis.model.request.RequestCustomer;
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
    @JsonProperty("OTHER")
    private String other;
    @JsonProperty("PRODUCT")
    private Product product;
    @JsonIgnore
    private List<CustomerImage> customerImages;

    public Customer() {
    }

    public Customer(RequestCustomer requestCustomer) {
        if (requestCustomer != null) {
            this.groomName = requestCustomer.getGroomName();
            this.groomDadName = requestCustomer.getGroomDadName();
            this.groomMomName = requestCustomer.getGroomMomName();
            this.brideName = requestCustomer.getBrideName();
            this.brideDadName = requestCustomer.getBrideDadName();
            this.brideMomName = requestCustomer.getBrideMomName();
            this.home = requestCustomer.getHome();
            this.address = requestCustomer.getAddress();
            this.date = requestCustomer.getDate();
            this.phone = requestCustomer.getPhone();
            this.other = requestCustomer.getOther();
        }
    }

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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<CustomerImage> getCustomerImages() {
        return customerImages;
    }

    public void setCustomerImages(List<CustomerImage> customerImages) {
        this.customerImages = customerImages;
    }

    @JsonProperty("IMAGES")
    @Transient
    public List<Long> getImageId() {
        List<Long> list = new ArrayList<>();
        if (customerImages != null && !customerImages.isEmpty()) {
            for (CustomerImage customerImage : customerImages) {
                if (customerImage.getImage() != null)
                    list.add(customerImage.getImage().getId());
            }
        }
        return list;
    }
}
