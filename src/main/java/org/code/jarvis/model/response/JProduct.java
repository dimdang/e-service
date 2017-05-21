package org.code.jarvis.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.code.jarvis.model.core.Product;
import org.code.jarvis.model.core.ProductContact;
import org.code.jarvis.model.core.ProductImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KimChheng on 5/17/2017.
 */
@JsonPropertyOrder({"ID", "CODE", "SIZE", "PRICE", "COLOR", "CONTACT", "IMAGES"})
public class JProduct {

    @JsonProperty("ID")
    private int id;
    @JsonProperty("CODE")
    private String code;
    @JsonProperty("SIZE")
    private int size;
    @JsonProperty("PRICE")
    private String price;
    @JsonProperty("COLOR")
    private String color;
    @JsonProperty("CONTACT")
    private Map<String, Object> contact;
    @JsonProperty("IMAGES")
    private List<Long> images;

    public JProduct() {

    }

    public JProduct(Product product) {
        this.id = Integer.valueOf(product.getId().toString());
        this.code = product.getCode();
        this.size = product.getSize();
        this.price = product.getPrice();
        this.color = product.getColor() != null ? product.getColor().getDesc() : "";
        if (product.getContact() != null) {
            ProductContact productContact = product.getContact();
            contact = new HashMap<>();
            contact.put("PHONE1", productContact.getPhone1());
            contact.put("PHONE2", productContact.getPhone1());
            contact.put("PHONE3", productContact.getPhone1());
            contact.put("EMAIL", productContact.getEmail());
            contact.put("FACEBOOK", productContact.getFacebook());
        }
        if (product.getProductImages() != null) {
            List<ProductImage> productImages = product.getProductImages();
            images = new ArrayList<>();
            for (ProductImage productImage : productImages)
                images.add(productImage.getId());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Map<String, Object> getContact() {
        return contact;
    }

    public void setContact(Map<String, Object> contact) {
        this.contact = contact;
    }

    public List<Long> getImages() {
        return images;
    }

    public void setImages(List<Long> images) {
        this.images = images;
    }
}
