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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Koksik
 */
@Entity
@Table(name = "ething_thingimage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EthingThingimage.findAll", query = "SELECT e FROM EthingThingimage e"),
    @NamedQuery(name = "EthingThingimage.findById", query = "SELECT e FROM EthingThingimage e WHERE e.id = :id")})
public class EthingThingimage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "thingimage_id", sequenceName = "thingimage_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "thingimage_id")
    @Column(name = "id", nullable = false)
    private Long id;
    @JoinColumn(name = "thingid", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EthingThing thingid;

    public EthingThingimage() {
    }

    public EthingThingimage(Long id) {
        this.id = id;
    }

    public EthingThingimage(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof EthingThingimage)) {
            return false;
        }
        EthingThingimage other = (EthingThingimage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.ething.model.EthingThingimage[ id=" + id + " ]";
    }

}
