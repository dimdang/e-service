package org.code.jarvis.model.refdata;

import org.code.jarvis.model.core.AbstractEntity;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_gender")
public class Gender extends AbstractEntity {

    public static final Gender BOY_SIDE = new Gender(1l, "boy", "boy_side");
    public static final Gender GIRL_SIDE = new Gender(2l, "girl", "girl_side");

    public Gender() {

    }

    public Gender(Long id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gen_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "gen_code", length = 50)
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "gen_desc", length = 100)
    @Override
    public String getDesc() {
        return desc;
    }

}
