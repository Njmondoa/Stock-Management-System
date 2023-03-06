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
@Table(name = "hardwarecons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardwarecons.nextId", query = "SELECT MAX(h.idhardwarecons) FROM Hardwarecons h"),

    @NamedQuery(name = "Hardwarecons.findAll", query = "SELECT h FROM Hardwarecons h"),
    @NamedQuery(name = "Hardwarecons.alert", query = "SELECT h FROM Hardwarecons h WHERE h.stockquantity <= h.criticalquantity "),
    @NamedQuery(name = "Hardwarecons.findByIdhardwarecons", query = "SELECT h FROM Hardwarecons h WHERE h.idhardwarecons = 2"),
    @NamedQuery(name = "Hardwarecons.findById", query = "SELECT h FROM Hardwarecons h WHERE h.idhardwarecons != 2"),

    @NamedQuery(name = "Hardwarecons.findByName", query = "SELECT h FROM Hardwarecons h WHERE h.name = :name"),
    @NamedQuery(name = "Hardwarecons.findByQuantite", query = "SELECT h.stockquantity FROM Hardwarecons h WHERE h.name = :name"),
    
    @NamedQuery(name = "Hardwarecons.findBySupplier", query = "SELECT h FROM Hardwarecons h WHERE h.supplier = :supplier"),
    @NamedQuery(name = "Hardwarecons.findByStockquantity", query = "SELECT h FROM Hardwarecons h WHERE h.stockquantity = :stockquantity"),
    @NamedQuery(name = "Hardwarecons.findByCriticalquantity", query = "SELECT h FROM Hardwarecons h WHERE h.criticalquantity = :criticalquantity"),
    @NamedQuery(name = "Hardwarecons.findByColor", query = "SELECT h FROM Hardwarecons h WHERE h.color = :color"),
    @NamedQuery(name = "Hardwarecons.findByReference", query = "SELECT h FROM Hardwarecons h WHERE h.reference = :reference")})
public class Hardwarecons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idhardwarecons")
    private Integer idhardwarecons;
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
    @Column(name = "color")
    private String color;
    @Size(max = 254)
    @Column(name = "reference")
    private String reference;
    @OneToMany(mappedBy = "idhardwarecons")
    private Collection<Attribution> attributionCollection;
    @JoinColumn(name = "idmaterial", referencedColumnName = "idmaterial")
    @ManyToOne(optional = false)
    private Itmaterial idmaterial;
    @OneToMany(mappedBy = "idhardwarecons")
    private Collection<Demand> demandCollection;

    public Hardwarecons() {
    }

    public Hardwarecons(Integer idhardwarecons) {
        this.idhardwarecons = idhardwarecons;
    }

    public Integer getIdhardwarecons() {
        return idhardwarecons;
    }

    public void setIdhardwarecons(Integer idhardwarecons) {
        this.idhardwarecons = idhardwarecons;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @XmlTransient
    public Collection<Attribution> getAttributionCollection() {
        return attributionCollection;
    }

    public void setAttributionCollection(Collection<Attribution> attributionCollection) {
        this.attributionCollection = attributionCollection;
    }

    public Itmaterial getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(Itmaterial idmaterial) {
        this.idmaterial = idmaterial;
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
        hash += (idhardwarecons != null ? idhardwarecons.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hardwarecons)) {
            return false;
        }
        Hardwarecons other = (Hardwarecons) object;
        if ((this.idhardwarecons == null && other.idhardwarecons != null) || (this.idhardwarecons != null && !this.idhardwarecons.equals(other.idhardwarecons))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

}
