/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ething.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "ething_user", catalog = "d3gdcmhjmsbvoq", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"}),
    @UniqueConstraint(columnNames = {"name"}),
    @UniqueConstraint(columnNames = {"login"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EthingUser.findAll", query = "SELECT e FROM EthingUser e"),
    @NamedQuery(name = "EthingUser.findById", query = "SELECT e FROM EthingUser e WHERE e.id = :id"),
    @NamedQuery(name = "EthingUser.findByName", query = "SELECT e FROM EthingUser e WHERE e.name = :name"),
    @NamedQuery(name = "EthingUser.findByLogin", query = "SELECT e FROM EthingUser e WHERE e.login = :login"),
    @NamedQuery(name = "EthingUser.findByPassword", query = "SELECT e FROM EthingUser e WHERE e.password = :password"),
    @NamedQuery(name = "EthingUser.findByEmail", query = "SELECT e FROM EthingUser e WHERE e.email = :email"),
    @NamedQuery(name = "EthingUser.findByRole", query = "SELECT e FROM EthingUser e WHERE e.role = :role"),
    @NamedQuery(name = "EthingUser.findByActivation", query = "SELECT e FROM EthingUser e WHERE e.activation = :activation")})
public class EthingUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "user_id", sequenceName = "user_id" , allocationSize=1,initialValue = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "user_id")
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
    @Column(name = "login", nullable = false, length = 2147483647)
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "password", nullable = false, length = 2147483647)
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "email", nullable = false, length = 2147483647)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "role", nullable = false, length = 2147483647)
    private String role;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "activation", nullable = false, length = 2147483647)
    private String activation;
    @OneToMany(mappedBy = "userid")
    private Set<EthingThing> ethingThingSet;

    public EthingUser() {
    }

    public EthingUser(Long id) {
        this.id = id;
    }

    public EthingUser(Long id, String name, String login, String password, String email, String role, String activation) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.activation = activation;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
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
        if (!(object instanceof EthingUser)) {
            return false;
        }
        EthingUser other = (EthingUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.ething.model.EthingUser[ id=" + id + " ]";
    }

}
