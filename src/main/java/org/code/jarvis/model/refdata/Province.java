package org.code.jarvis.model.refdata;

import org.code.jarvis.model.core.AbstractEntity;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/9/2017.
 */
@Entity
@Table(name = "td_province")
public class Province extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "pro_code", length = 50)
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "pro_desc", length = 100)
    @Override
    public String getDesc() {
        return desc;
    }
}
