package org.code.jarvis.model.refdata;

import org.code.jarvis.model.core.AbstractEntity;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/9/2017.
 */
@Entity
@Table(name = "td_village")
public class Village extends AbstractEntity {

    private Commune commune;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vil_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "vil_code", length = 50)
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "vil_desc", length = 100)
    @Override
    public String getDesc() {
        return desc;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "com_id",referencedColumnName = "com_id")
    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }
}
