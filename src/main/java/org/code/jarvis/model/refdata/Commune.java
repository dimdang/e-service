package org.code.jarvis.model.refdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.code.jarvis.model.core.AbstractEntity;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/9/2017.
 */
//@Entity
//@Table(name = "td_commune")
public class Commune extends AbstractEntity {

    @JsonIgnore
    private District district;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    @Column(name = "com_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "com_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "com_desc", length = 100)
    @JsonProperty("DESC")
    @Override
    public String getDesc() {
        return desc;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dis_id", referencedColumnName = "dis_id")
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @JsonProperty("DIS_ID")
    public Long district() {
        if (district != null)
            return district.getId();
        return null;
    }
}
