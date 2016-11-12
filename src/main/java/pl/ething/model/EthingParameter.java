/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Koksik
 */
@Entity
@Table(name = "ething_parameter", catalog = "d3gdcmhjmsbvoq", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EthingParameter.findAll", query = "SELECT e FROM EthingParameter e"),
    @NamedQuery(name = "EthingParameter.findById", query = "SELECT e FROM EthingParameter e WHERE e.id = :id"),
    @NamedQuery(name = "EthingParameter.findByName", query = "SELECT e FROM EthingParameter e WHERE e.name = :name"),
    @NamedQuery(name = "EthingParameter.findByValue", query = "SELECT e FROM EthingParameter e WHERE e.value = :value")})
public class EthingParameter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "parameter_id", sequenceName = "parameter_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_id")
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name", nullable = false, length = 2147483647)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "value", nullable = false, length = 2147483647)
    private String value;
    @JoinColumn(name = "thingid", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EthingThing thingid;

    public EthingParameter() {
    }

    public EthingParameter(Long id) {
        this.id = id;
    }

    public EthingParameter(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!(object instanceof EthingParameter)) {
            return false;
        }
        EthingParameter other = (EthingParameter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.ething.model.EthingParameter[ id=" + id + " ]";
    }

}
