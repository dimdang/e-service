package org.code.jarvis.model.refdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.code.jarvis.model.core.AbstractEntity;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/10/2017.
 */
//@Entity
//@Table(name = "td_gender")
public class Gender extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    @Column(name = "gen_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "gen_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "gen_desc", length = 100)
    @JsonProperty("DESC")
    @Override
    public String getDesc() {
        return desc;
    }

}
