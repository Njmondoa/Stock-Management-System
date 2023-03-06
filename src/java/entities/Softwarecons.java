/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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

/**
 *
 * @author Corei_5
 */
@Entity
@Table(name = "softwarecons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Softwarecons.nextId", query = "SELECT MAX(s.idsoftwarecons) FROM Softwarecons s"),

    @NamedQuery(name = "Softwarecons.findAll", query = "SELECT s FROM Softwarecons s"),
    @NamedQuery(name = "Softwarecons.findByIdsoftwarecons", query = "SELECT s FROM Softwarecons s WHERE s.idsoftwarecons = 2"),
    @NamedQuery(name = "Softwarecons.findById", query = "SELECT s FROM Softwarecons s WHERE s.idsoftwarecons != 2"),
    @NamedQuery(name = "Softwarecons.alert", query = "SELECT s FROM Softwarecons s WHERE s.stockquantity <= s.criticalquantity "),

    @NamedQuery(name = "Softwarecons.findByName", query = "SELECT s FROM Softwarecons s WHERE s.name = :name"),
    @NamedQuery(name = "Softwarecons.findByQuantite", query = "SELECT s.stockquantity FROM Softwarecons s WHERE s.name = :name"),

    @NamedQuery(name = "Softwarecons.findBySupplier", query = "SELECT s FROM Softwarecons s WHERE s.supplier = :supplier"),
    @NamedQuery(name = "Softwarecons.findByStockquantity", query = "SELECT s FROM Softwarecons s WHERE s.stockquantity = :stockquantity"),
    @NamedQuery(name = "Softwarecons.findByCriticalquantity", query = "SELECT s FROM Softwarecons s WHERE s.criticalquantity = :criticalquantity"),
    @NamedQuery(name = "Softwarecons.findByLicense", query = "SELECT s FROM Softwarecons s WHERE s.license = :license")})
public class Softwarecons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsoftwarecons")
    private Integer idsoftwarecons;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @Size(max = 254)
    @Column(name = "supplier")
    private String supplier;
    @Column(name = "stockquantity")
    private Integer stockquantity;
    @Column(name = "criticalquantity")
    private Integer criticalquantity;
    @Size(max = 254)
    @Column(name = "license")
    private String license;
    @JoinColumn(name = "idmaterial", referencedColumnName = "idmaterial")
    @ManyToOne(optional = false)
    private Itmaterial idmaterial;
    @OneToMany(mappedBy = "idsoftwarecons")
    private Collection<Attribution> attributionCollection;
    @OneToMany(mappedBy = "idsoftwarecons")
    private Collection<Demand> demandCollection;

    public Softwarecons() {
    }

    public Softwarecons(Integer idsoftwarecons) {
        this.idsoftwarecons = idsoftwarecons;
    }

    public Integer getIdsoftwarecons() {
        return idsoftwarecons;
    }

    public void setIdsoftwarecons(Integer idsoftwarecons) {
        this.idsoftwarecons = idsoftwarecons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(Integer stockquantity) {
        this.stockquantity = stockquantity;
    }

    public Integer getCriticalquantity() {
        return criticalquantity;
    }

    public void setCriticalquantity(Integer criticalquantity) {
        this.criticalquantity = criticalquantity;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Itmaterial getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Itmaterial idmaterial) {
        this.idmaterial = idmaterial;
    }

    @XmlTransient
    public Collection<Attribution> getAttributionCollection() {
        return attributionCollection;
    }

    public void setAttributionCollection(Collection<Attribution> attributionCollection) {
        this.attributionCollection = attributionCollection;
    }

    @XmlTransient
    public Collection<Demand> getDemandCollection() {
        return demandCollection;
    }

    public void setDemandCollection(Collection<Demand> demandCollection) {
        this.demandCollection = demandCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsoftwarecons != null ? idsoftwarecons.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Softwarecons)) {
            return false;
        }
        Softwarecons other = (Softwarecons) object;
        if ((this.idsoftwarecons == null && other.idsoftwarecons != null) || (this.idsoftwarecons != null && !this.idsoftwarecons.equals(other.idsoftwarecons))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
