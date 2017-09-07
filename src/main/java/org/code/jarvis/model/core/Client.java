package org.code.jarvis.model.core;

import javax.persistence.*;

/**
 * Created by KimChheng on 9/3/2017.
 */
@Entity
@Table(name = "td_client")
public class Client extends AbstractEntity {

    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    @Override
    public Long getId() {
        return this.id;
    }

    @Column(name = "cli_code")
    @Override
    public String getCode() {
        return this.code;
    }

    @Column(name = "cli_desc")
    @Override
    public String getDesc() {
        return this.desc;
    }

    @Column(name = "cli_token", unique = true, nullable = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
