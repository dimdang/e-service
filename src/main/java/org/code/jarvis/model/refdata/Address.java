package org.code.jarvis.model.refdata;

import org.code.jarvis.model.core.AbstractEntity;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/9/2017.
 */
@Entity
@Table(name = "td_address")
public class Address extends AbstractEntity {

    private Province province;
    private Commune commune;
    private District district;
    private Village village;

    public Address() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id")
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "add_code", length = 50)
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "add_desc", length = 100)
    @Override
    public String getDesc() {
        return desc;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "com_id", referencedColumnName = "com_id")
    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dis_id", referencedColumnName = "dis_id")
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vil_id", referencedColumnName = "vil_id")
    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }


    public String provinceDesc() {
        if (province != null)
            return province.getDesc();
        return "";
    }

    public String districtDesc() {
        if (district != null)
            return district.getDesc();
        return "";
    }

    public String communeDesc() {
        if (commune != null)
            return commune.getDesc();
        return "";
    }

    public String villageDesc() {
        if (village != null)
            return village.getDesc();
        return "";
    }
}
