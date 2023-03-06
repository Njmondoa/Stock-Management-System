/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Corei_5
 */
@Entity
@Table(name = "demand")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Demand.findAll", query = "SELECT d FROM Demand d"),

    @NamedQuery(name = "Demand.findmydemand", query = "SELECT d FROM Demand d WHERE d.idusers = :idusers"),
    @NamedQuery(name = "Demand.findmydemandHard", query = "SELECT d FROM Demand d WHERE d.idusers = :idusers AND d.idhardwarecons.name != '/'"),
    @NamedQuery(name = "Demand.findmydemandSoft", query = "SELECT d FROM Demand d WHERE d.idusers = :idusers AND d.idsoftwarecons.name != '/'"),
    @NamedQuery(name = "Demand.stat", query = "SELECT COUNT(d) FROM Demand d WHERE d.dateofdemand <= :date AND d.dateofdemand >= :date1"),
    @NamedQuery(name = "Demand.stat2", query = "SELECT COUNT(d) FROM Demand d WHERE d.idusers.idservice.iddepartment.name = 'DAAF'"),
    @NamedQuery(name = "Demand.stat3", query = "SELECT COUNT(d) FROM Demand d WHERE d.idusers.idservice.iddepartment.name = 'DEL'"),
    @NamedQuery(name = "Demand.stat4", query = "SELECT COUNT(d) FROM Demand d WHERE d.idusers.idservice.iddepartment.name = 'DIRE'"),
    @NamedQuery(name = "Demand.stat5", query = "SELECT COUNT(d) FROM Demand d WHERE d.idusers.idservice.iddepartment.name = 'DTB'"),
    @NamedQuery(name = "Demand.stat6", query = "SELECT COUNT(d) FROM Demand d WHERE d.idusers.idservice.iddepartment.name = 'DEP'"),

    @NamedQuery(name = "Demand.findByIddemand", query = "SELECT d FROM Demand d WHERE d.iddemand = :iddemand"),
    @NamedQuery(name = "Demand.findByColor", query = "SELECT d FROM Demand d WHERE d.color = :color"),
    @NamedQuery(name = "Demand.findByQtydemanded", query = "SELECT d FROM Demand d WHERE d.qtydemanded = :qtydemanded"),
    @NamedQuery(name = "Demand.findByDateofdemand", query = "SELECT d FROM Demand d WHERE d.dateofdemand = :dateofdemand"),
    @NamedQuery(name = "Demand.findByhardsofts", query = "SELECT d FROM Demand d WHERE d.idhardwarecons != :hard AND d.idsoftwarecons = :soft"),
    @NamedQuery(name = "Demand.findByhardsoft", query = "SELECT d FROM Demand d WHERE d.idhardwarecons = :hard AND d.idsoftwarecons != :soft"),
    @NamedQuery(name = "Demand.findByNotConfirmed", query = "SELECT d FROM Demand d WHERE d.state = '' AND d.idusers.idservice = :service"),
    @NamedQuery(name = "Demand.findByConfirmed", query = "SELECT d FROM Demand d WHERE d.state = 'confirmed'"),
    @NamedQuery(name = "Demand.findByAttributed", query = "SELECT d FROM Demand d WHERE d.state = 'Attributed'"),

    @NamedQuery(name = "Demand.findByValidatedhard", query = "SELECT d FROM Demand d WHERE d.idhardwarecons.name != '/' AND d.state = 'validated'"),
    @NamedQuery(name = "Demand.findByValidatedsoft", query = "SELECT d FROM Demand d WHERE d.idsoftwarecons.name != '/' AND d.state = 'validated'"),

    @NamedQuery(name = "Demand.findByRejected", query = "SELECT d FROM Demand d WHERE d.state = 'rejected'"),
    @NamedQuery(name = "Demand.findByOnHold", query = "SELECT d FROM Demand d WHERE d.state = 'on hold'"),

    @NamedQuery(name = "Demand.findByTreated", query = "SELECT d FROM Demand d WHERE d.state = 'validated' OR d.state = 'rejected' OR d.state = 'on hold' OR d.state = 'Attributed'")

})

public class Demand implements Serializable {

    @Size(max = 254)
    @Column(name = "state")
    private String state;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddemand")
    private Integer iddemand;
    @Size(max = 254)
    @Column(name = "color")
    private String color;
    @Column(name = "qtydemanded")
    private Integer qtydemanded;
    @Column(name = "dateofdemand")
    @Temporal(TemporalType.DATE)
    private Date dateofdemand;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddemand")
    private Collection<Attribution> attributionCollection;
    @JoinColumn(name = "idhardwarecons", referencedColumnName = "idhardwarecons")
    @ManyToOne(optional = true)
    private Hardwarecons idhardwarecons;
    @JoinColumn(name = "idsoftwarecons", referencedColumnName = "idsoftwarecons")
    @ManyToOne(optional = true)
    private Softwarecons idsoftwarecons;
    @JoinColumn(name = "idusers", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private Users idusers;

    public Demand() {
    }

    public Demand(Integer iddemand) {
        this.iddemand = iddemand;
    }

    public Integer getIddemand() {
        return iddemand;
    }

    public void setIddemand(Integer iddemand) {
        this.iddemand = iddemand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQtydemanded() {
        return qtydemanded;
    }

    public void setQtydemanded(Integer qtydemanded) {
        this.qtydemanded = qtydemanded;
    }

    public Date getDateofdemand() {
        return dateofdemand;
    }

    public void setDateofdemand(Date dateofdemand) {
        this.dateofdemand = dateofdemand;
    }

    @XmlTransient
    public Collection<Attribution> getAttributionCollection() {
        return attributionCollection;
    }

    public void setAttributionCollection(Collection<Attribution> attributionCollection) {
        this.attributionCollection = attributionCollection;
    }

    public Hardwarecons getIdhardwarecons() {
        return idhardwarecons;
    }

    public void setIdhardwarecons(Hardwarecons idhardwarecons) {
        this.idhardwarecons = idhardwarecons;
    }

    public Softwarecons getIdsoftwarecons() {
        return idsoftwarecons;
    }

    public void setIdsoftwarecons(Softwarecons idsoftwarecons) {
        this.idsoftwarecons = idsoftwarecons;
    }

    public Users getIdusers() {
        return idusers;
    }

    public void setIdusers(Users idusers) {
        this.idusers = idusers;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddemand != null ? iddemand.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Demand)) {
            return false;
        }
        Demand other = (Demand) object;
        if ((this.iddemand == null && other.iddemand != null) || (this.iddemand != null && !this.iddemand.equals(other.iddemand))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idusers.getName();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
