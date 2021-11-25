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
import java.math.BigDecimal;
import java.security.Principal;
import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.Parameter;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ipmetadata")
@Table(name = "ipmetadata")
@XmlRootElement
@NamedNativeQuery(name = "findByIp",
        query = "SELECT * from \"ipmetadata\" where \"querystring\" =  :query ", resultClass = IPMetaData.class)
public class IPMetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "querystring")
    @JsonProperty
    private String query;
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
    private BigDecimal lat;
    @JsonProperty
    private BigDecimal lon;
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