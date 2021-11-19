package com.hilton.demo.start.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.Principal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "IPMetaData")
@XmlRootElement
@NamedNativeQuery(name = "findByIp",
        query = "SELECT * from \"IPMetaData\" where ip = :ip", resultClass = IPMetaData.class)
@NamedQuery(name = "IPMetaData.findByIp",
        query = "SELECT i FROM IPMetaData i WHERE i.ip = :ip")
public class IPMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "id")
    private long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ip")
    private String ip;

}