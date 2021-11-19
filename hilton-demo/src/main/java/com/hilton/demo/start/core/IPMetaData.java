package com.hilton.demo.start.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.Principal;

@Entity
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "IPMetaData.findByIp",
        query = "SELECT i FROM IPMetaData i WHERE i.ip = :ip")})
public class IPMetaData implements Principal, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ip;


    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}