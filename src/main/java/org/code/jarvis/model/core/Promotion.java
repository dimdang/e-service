package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimChheng on 8/13/2017.
 */
@Entity
@Table(name = "td_promotion")
public class Promotion extends AbstractEntity {

    @JsonIgnore
    private List<Image> images;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    @JsonProperty("ID")
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    @Column(name = "pro_code")
    @JsonProperty("CODE")
    public String getCode() {
        return this.code;
    }

    @Override
    @Column(name = "pro_desc")
    @JsonProperty("DESC")
    public String getDesc() {
        return this.desc;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "td_promotion_image", joinColumns = {@JoinColumn(name = "pro_id")},
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
