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
@Table(name = "ething_thing", catalog = "d3gdcmhjmsbvoq", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EthingThing.findAll", query = "SELECT e FROM EthingThing e"),
    @NamedQuery(name = "EthingThing.findById", query = "SELECT e FROM EthingThing e WHERE e.id = :id"),
    @NamedQuery(name = "EthingThing.findByIdhash", query = "SELECT e FROM EthingThing e WHERE e.idhash = :idhash"),
    @NamedQuery(name = "EthingThing.findByName", query = "SELECT e FROM EthingThing e WHERE e.name = :name"),
    @NamedQuery(name = "EthingThing.findByModel", query = "SELECT e FROM EthingThing e WHERE e.model = :model"),
    @NamedQuery(name = "EthingThing.findByFirm", query = "SELECT e FROM EthingThing e WHERE e.firm = :firm"),
    @NamedQuery(name = "EthingThing.findByStatus", query = "SELECT e FROM EthingThing e WHERE e.status = :status")})
public class EthingThing implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean access;

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
    @Column(name = "model", nullable = false, length = 2147483647)
    private String model;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "firm", nullable = false, length = 2147483647)
    private String firm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "status", nullable = false, length = 2147483647)
    private String status;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne
    private EthingUser userid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thingid")
    private Set<EthingParameter> ethingParameterSet;
    @OneToMany(mappedBy = "thingid")
    private Set<EthingFeature> ethingFeatureSet;

    public EthingThing() {
    }

    public EthingThing(Long id) {
        this.id = id;
    }

    public EthingThing(Long id, String idhash, String name, String model, String firm, String status) {
        this.id = id;
        this.idhash = idhash;
        this.name = name;
        this.model = model;
        this.firm = firm;
        this.status = status;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EthingUser getUserid() {
        return userid;
    }

    public void setUserid(EthingUser userid) {
        this.userid = userid;
    }

    @XmlTransient
    public Set<EthingParameter> getEthingParameterSet() {
        return ethingParameterSet;
    }

    public void setEthingParameterSet(Set<EthingParameter> ethingParameterSet) {
        this.ethingParameterSet = ethingParameterSet;
    }

    @XmlTransient
    public Set<EthingFeature> getEthingFeatureSet() {
        return ethingFeatureSet;
    }

    public void setEthingFeatureSet(Set<EthingFeature> ethingFeatureSet) {
        this.ethingFeatureSet = ethingFeatureSet;
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

    public boolean getAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }


}
