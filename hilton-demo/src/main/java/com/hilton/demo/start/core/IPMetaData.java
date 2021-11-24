package com.hilton.demo.start.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.security.Principal;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedNativeQuery;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "IPMetaData")
@XmlRootElement
@NamedNativeQuery(name = "findByIp",
        query = "SELECT * from \"IPMetaData\" where \"queryString\" =  :query ", resultClass = IPMetaData.class)
public class IPMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @JsonProperty
    @Column(name = "queryString")
    private String queryString;
    @JsonProperty
    private String status;
    @JsonProperty
    private String country;
    @JsonProperty
    private String countryCode;
    @JsonProperty
    private String region;
    @JsonProperty
    private String regionName;
    @JsonProperty
    private String city;
    @JsonProperty
    private String zip;
    @JsonProperty
    private String lat;
    @JsonProperty
    private String lon;
    @JsonProperty
    private String timezone;
    @JsonProperty
    private String isp;
    @JsonProperty
    private String org;
    @Column(name = "asn")
    @JsonProperty
    private String as;


}