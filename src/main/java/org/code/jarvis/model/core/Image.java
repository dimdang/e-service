package org.code.jarvis.model.core;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_image")
public class Image extends AbstractEntity {

    private String path;
    private String name;
    private String type;
    private byte[] bytes;

    public Image() {
    }

    public Image(String name, String path) {
        this.path = path;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "img_code", length = 50)
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "img_desc", length = 100)
    @Override
    public String getDesc() {
        return desc;
    }

    @Column(name = "img_path", length = 200)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "img_name", length = 100)
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
