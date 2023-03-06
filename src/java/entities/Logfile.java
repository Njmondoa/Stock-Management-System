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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Corei_5
 */
@Entity
@Table(name = "logfile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logfile.findAll", query = "SELECT l FROM Logfile l"),
    @NamedQuery(name = "Logfile.findByIdlogfile", query = "SELECT l FROM Logfile l WHERE l.idlogfile = :idlogfile"),
    @NamedQuery(name = "Logfile.findByName", query = "SELECT l FROM Logfile l WHERE l.name = :name"),
    @NamedQuery(name = "Logfile.findByTarget", query = "SELECT l FROM Logfile l WHERE l.target = :target"),
    @NamedQuery(name = "Logfile.findByDate", query = "SELECT l FROM Logfile l WHERE l.date = :date"),
    @NamedQuery(name = "Logfile.findByTime", query = "SELECT l FROM Logfile l WHERE l.time = :time"),
    @NamedQuery(name = "Logfile.findByIpadresse", query = "SELECT l FROM Logfile l WHERE l.ipadresse = :ipadresse")})
public class Logfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlogfile")
    private Integer idlogfile;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @Size(max = 254)
    @Column(name = "target")
    private String target;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Size(max = 254)
    @Column(name = "ipadresse")
    private String ipadresse;
    @JoinColumn(name = "idusers", referencedColumnName = "idusers")
    @ManyToOne(optional = false)
    private Users idusers;

    public Logfile() {
    }

    public Logfile(Integer idlogfile) {
        this.idlogfile = idlogfile;
    }

    public Integer getIdlogfile() {
        return idlogfile;
    }

    public void setIdlogfile(Integer idlogfile) {
        this.idlogfile = idlogfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIpadresse() {
        return ipadresse;
    }

    public void setIpadresse(String ipadresse) {
        this.ipadresse = ipadresse;
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
        hash += (idlogfile != null ? idlogfile.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logfile)) {
            return false;
        }
        Logfile other = (Logfile) object;
        if ((this.idlogfile == null && other.idlogfile != null) || (this.idlogfile != null && !this.idlogfile.equals(other.idlogfile))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Logfile[ idlogfile=" + idlogfile + " ]";
    }
    
}
