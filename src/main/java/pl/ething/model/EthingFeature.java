/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Koksik
 */
@Entity
@Table(name = "ething_feature", catalog = "d3gdcmhjmsbvoq", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EthingFeature.findAll", query = "SELECT e FROM EthingFeature e"),
    @NamedQuery(name = "EthingFeature.findById", query = "SELECT e FROM EthingFeature e WHERE e.id = :id"),
    @NamedQuery(name = "EthingFeature.findByName", query = "SELECT e FROM EthingFeature e WHERE e.name = :name"),
    @NamedQuery(name = "EthingFeature.findByDate", query = "SELECT e FROM EthingFeature e WHERE e.date = :date"),
    @NamedQuery(name = "EthingFeature.findByDescription", query = "SELECT e FROM EthingFeature e WHERE e.description = :description")})
public class EthingFeature implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name", nullable = false, length = 2147483647)
    private String name;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 2147483647)
    @Column(name = "description", length = 2147483647)
    private String description;
    @JoinColumn(name = "thingid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private EthingThing thingid;

    public EthingFeature() {
    }

    public EthingFeature(Integer id) {
        this.id = id;
    }

    public EthingFeature(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EthingThing getThingid() {
        return thingid;
    }

    public void setThingid(EthingThing thingid) {
        this.thingid = thingid;
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
        if (!(object instanceof EthingFeature)) {
            return false;
        }
        EthingFeature other = (EthingFeature) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.ething.model.EthingFeature[ id=" + id + " ]";
    }
    
}
