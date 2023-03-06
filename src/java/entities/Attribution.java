/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Corei_5
 */
@Entity
@Table(name = "attribution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attribution.findAll", query = "SELECT a FROM Attribution a"),
    @NamedQuery(name = "Attribution.findmyattribution", query = "SELECT a FROM Attribution a WHERE a.iddemand.idusers = :iddemand"),
    @NamedQuery(name = "Attribution.stat", query = "SELECT COUNT(a) FROM Attribution a WHERE a.dateofattribution <= :date AND a.dateofattribution >= :date1"),

    @NamedQuery(name = "Attribution.findByIdattribution", query = "SELECT a FROM Attribution a WHERE a.idattribution = :idattribution"),
    @NamedQuery(name = "Attribution.findByQtyattributed", query = "SELECT a FROM Attribution a WHERE a.qtyattributed = :qtyattributed"),
    @NamedQuery(name = "Attribution.findByDateofattribution", query = "SELECT a FROM Attribution a WHERE a.dateofattribution = :dateofattribution")})
public class Attribution implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idattribution")
    private Integer idattribution;
    @Column(name = "qtyattributed")
    private Integer qtyattributed;
    @Column(name = "dateofattribution")
    @Temporal(TemporalType.DATE)
    private Date dateofattribution;
    @JoinColumn(name = "iddemand", referencedColumnName = "iddemand")
    @ManyToOne(optional = false)
    private Demand iddemand = new Demand();
    @JoinColumn(name = "idhardwarecons", referencedColumnName = "idhardwarecons")
    @ManyToOne
    private Hardwarecons idhardwarecons = new Hardwarecons();
    @JoinColumn(name = "idsoftwarecons", referencedColumnName = "idsoftwarecons")
    @ManyToOne
    private Softwarecons idsoftwarecons = new Softwarecons();
    @JoinColumn(name = "idusers", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private Users idusers = new Users();

    public Attribution() {
    }

    public Attribution(Integer idattribution) {
        this.idattribution = idattribution;
    }

    public Integer getIdattribution() {
        return idattribution;
    }

    public void setIdattribution(Integer idattribution) {
        this.idattribution = idattribution;
    }

    public Integer getQtyattributed() {
        return qtyattributed;
    }

    public void setQtyattributed(Integer qtyattributed) {
        this.qtyattributed = qtyattributed;
    }

    public Date getDateofattribution() {
        return dateofattribution;
    }

    public void setDateofattribution(Date dateofattribution) {
        this.dateofattribution = dateofattribution;
    }

    public Demand getIddemand() {
        return iddemand;
    }

    public void setIddemand(Demand iddemand) {
        this.iddemand = iddemand;
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
        hash += (idattribution != null ? idattribution.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attribution)) {
            return false;
        }
        Attribution other = (Attribution) object;
        if ((this.idattribution == null && other.idattribution != null) || (this.idattribution != null && !this.idattribution.equals(other.idattribution))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Attribution[ idattribution=" + idattribution + " ]";
    }
    
}
