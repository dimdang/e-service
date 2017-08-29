package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_image")
public class Image extends AbstractEntity {

    @JsonIgnore
    private String path;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("TYPE")
    private String type;
    @JsonIgnore
    private byte[] bytes;

    public Image() {
    }

    public Image(String name, String path) {
        this.path = path;
        this.name = name;
    }

    @JsonProperty("ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    @Override
    public Long getId() {
        return id;
    }

    @JsonIgnore
    @Column(name = "img_code", length = 50)
    @Override
    public String getCode() {
        return code;
    }

    @JsonIgnore
    @Column(name = "img_desc", length = 100)
    @Override
    public String getDesc() {
        return desc;
    }

    @Column(name = "img_path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "img_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "img_blob")
    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Column(name = "img_type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
