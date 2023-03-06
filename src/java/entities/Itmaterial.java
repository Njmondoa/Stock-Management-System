/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corei_5
 */
@Entity
@Table(name = "itmaterial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itmaterial.findAll", query = "SELECT i FROM Itmaterial i"),
    @NamedQuery(name = "Itmaterial.findByIdmaterial", query = "SELECT i FROM Itmaterial i WHERE i.idmaterial != 1"),
    @NamedQuery(name = "Itmaterial.findByName", query = "SELECT i FROM Itmaterial i WHERE i.name = :name"),
    @NamedQuery(name = "Itmaterial.findByCharacteristics", query = "SELECT i FROM Itmaterial i WHERE i.characteristics = :characteristics")})
public class Itmaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmaterial")
    private Integer idmaterial;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @Size(max = 254)
    @Column(name = "characteristics")
    private String characteristics;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmaterial")
    private Collection<Softwarecons> softwareconsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmaterial")
    private Collection<Hardwarecons> hardwareconsCollection;

    public Itmaterial() {
    }

    public Itmaterial(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public Integer getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Integer idmaterial) {
        this.idmaterial = idmaterial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    @XmlTransient
    public Collection<Softwarecons> getSoftwareconsCollection() {
        return softwareconsCollection;
    }

    public void setSoftwareconsCollection(Collection<Softwarecons> softwareconsCollection) {
        this.softwareconsCollection = softwareconsCollection;
    }

    @XmlTransient
    public Collection<Hardwarecons> getHardwareconsCollection() {
        return hardwareconsCollection;
    }

    public void setHardwareconsCollection(Collection<Hardwarecons> hardwareconsCollection) {
        this.hardwareconsCollection = hardwareconsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmaterial != null ? idmaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itmaterial)) {
            return false;
        }
        Itmaterial other = (Itmaterial) object;
        if ((this.idmaterial == null && other.idmaterial != null) || (this.idmaterial != null && !this.idmaterial.equals(other.idmaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
