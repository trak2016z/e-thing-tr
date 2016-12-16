/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
/**
 *
 * @author Koksik
 */
@Entity
@Table(name = "ething_thing")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EthingThing.findAll", query = "SELECT e FROM EthingThing e"),
    @NamedQuery(name = "EthingThing.findById", query = "SELECT e FROM EthingThing e WHERE e.id = :id"),
    @NamedQuery(name = "EthingThing.findByIdhash", query = "SELECT e FROM EthingThing e WHERE e.idhash = :idhash"),
    @NamedQuery(name = "EthingThing.findByName", query = "SELECT e FROM EthingThing e WHERE e.name = :name"),
    @NamedQuery(name = "EthingThing.findByStatus", query = "SELECT e FROM EthingThing e WHERE e.status = :status"),
    @NamedQuery(name = "EthingThing.findByAccess", query = "SELECT e FROM EthingThing e WHERE e.access = :access"),
    @NamedQuery(name = "EthingThing.findByUrl", query = "SELECT e FROM EthingThing e WHERE e.url = :url"),
    @NamedQuery(name = "EthingThing.findByUrl2", query = "SELECT e FROM EthingThing e WHERE e.url2 = :url2"),
    @NamedQuery(name = "EthingThing.findByDescription", query = "SELECT e FROM EthingThing e WHERE e.description = :description")})
public class EthingThing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "thing_id", sequenceName = "thing_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thing_id")
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "idhash", nullable = false, length = 2147483647)
    private String idhash;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name", nullable = false, length = 2147483647)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "status", nullable = false, length = 2147483647)
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "access", nullable = false, length = 2147483647)
    private String access;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "url", nullable = false, length = 2147483647)
    private String url;
    @Size(max = 2147483647)
    @Column(name = "url2", length = 2147483647)
    private String url2;
    @Size(max = 2147483647)
    @Column(name = "description", length = 2147483647)
    private String description;
    @JoinColumn(name = "userid", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EthingUser userid;
    @JoinColumn(name = "thingtype", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EthingThingtype thingtype;
    @OneToMany(mappedBy = "thingid", fetch = FetchType.LAZY )
    private Set<EthingFeature> ethingFeatureSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thingid", fetch = FetchType.LAZY)
    private Set<EthingThingimage> ethingThingimageSet;

    public EthingThing() {
    }

    public EthingThing(Long id) {
        this.id = id;
    }

    public EthingThing(Long id, String idhash, String name, String status, String access, String url) {
        this.id = id;
        this.idhash = idhash;
        this.name = name;
        this.status = status;
        this.access = access;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdhash() {
        return idhash;
    }

    public void setIdhash(String idhash) {
        this.idhash = idhash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EthingUser getUserid() {
        return userid;
    }

    public void setUserid(EthingUser userid) {
        this.userid = userid;
    }

    public EthingThingtype getThingtype() {
        return thingtype;
    }

    public void setThingtype(EthingThingtype thingtype) {
        this.thingtype = thingtype;
    }

    @XmlTransient
    public Set<EthingFeature> getEthingFeatureSet() {
        return ethingFeatureSet;
    }

    public void setEthingFeatureSet(Set<EthingFeature> ethingFeatureSet) {
        this.ethingFeatureSet = ethingFeatureSet;
    }

    @XmlTransient
    public Set<EthingThingimage> getEthingThingimageSet() {
        return ethingThingimageSet;
    }

    public void setEthingThingimageSet(Set<EthingThingimage> ethingThingimageSet) {
        this.ethingThingimageSet = ethingThingimageSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EthingThing)) {
            return false;
        }
        EthingThing other = (EthingThing) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.ething.model.EthingThing[ id=" + id + " ]";
    }

}
