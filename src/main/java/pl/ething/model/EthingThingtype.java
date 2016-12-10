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
@Table(name = "ething_thingtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EthingThingtype.findAll", query = "SELECT e FROM EthingThingtype e"),
    @NamedQuery(name = "EthingThingtype.findByName", query = "SELECT e FROM EthingThingtype e WHERE e.name = :name"),
    @NamedQuery(name = "EthingThingtype.findById", query = "SELECT e FROM EthingThingtype e WHERE e.id = :id")})
public class EthingThingtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name", nullable = false, length = 2147483647)
    private String name;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "thingtype_id", sequenceName = "thingtype_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thingtype_id")
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thingtype", fetch = FetchType.EAGER)
    private Set<EthingThing> ethingThingSet;

    public EthingThingtype() {
    }

    public EthingThingtype(Long id) {
        this.id = id;
    }

    public EthingThingtype(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Set<EthingThing> getEthingThingSet() {
        return ethingThingSet;
    }

    public void setEthingThingSet(Set<EthingThing> ethingThingSet) {
        this.ethingThingSet = ethingThingSet;
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
        if (!(object instanceof EthingThingtype)) {
            return false;
        }
        EthingThingtype other = (EthingThingtype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.ething.model.EthingThingtype[ id=" + id + " ]";
    }

}
