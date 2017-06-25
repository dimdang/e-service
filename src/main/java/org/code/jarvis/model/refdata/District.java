
package org.code.jarvis.model.refdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.code.jarvis.model.core.AbstractEntity;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/9/2017.
 */
//@Entity
//@Table(name = "td_district")
public class District extends AbstractEntity {

    @JsonIgnore
    private Province province;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dis_id")
    @JsonProperty("ID")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "dis_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "dis_desc", length = 100)
    @JsonProperty("DESC")
    @Override
    public String getDesc() {
        return desc;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @JsonProperty("PRO_ID")
    public Long province() {
        if (province != null)
            return province.getId();
        return null;
    }
}
