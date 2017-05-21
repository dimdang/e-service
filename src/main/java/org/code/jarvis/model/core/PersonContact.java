package org.code.jarvis.model.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.code.jarvis.model.request.RequestPerson;

import javax.persistence.*;

/**
 * Created by KimChheng on 5/10/2017.
 */
@Entity
@Table(name = "td_person_contact")
public class PersonContact extends AbstractContact {

    @JsonIgnore
    private Person person;

    public PersonContact() {

    }

    public PersonContact(RequestPerson requestPerson) {
        this.phone1 = requestPerson.getPhone1();
        this.phone2 = requestPerson.getPhone2();
        this.phone3 = requestPerson.getPhone3();
        this.facebook = requestPerson.getFacebook();
        this.email = requestPerson.getEmail();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_con_id")
    @JsonIgnore
    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "per_con_code", length = 50)
    @JsonIgnore
    @Override
    public String getCode() {
        return code;
    }

    @Column(name = "per_con_desc", length = 100)
    @JsonIgnore
    @Override
    public String getDesc() {
        return desc;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "per_id", referencedColumnName = "per_id", nullable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
