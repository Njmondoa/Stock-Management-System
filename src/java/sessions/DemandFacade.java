/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Demand;
import entities.Department;
import entities.Hardwarecons;
import entities.Softwarecons;
import entities.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Corei_5
 */
@Stateless
public class DemandFacade extends AbstractFacade<Demand> implements DemandFacadeLocal {

    @PersistenceContext(unitName = "GESCI_WEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DemandFacade() {
        super(Demand.class);
    }

    @Override
    public List<Demand> listdemand(Hardwarecons hard, Softwarecons soft) {
        try {
            Query query = em.createNamedQuery("Demand.findByhardsoft");
            query.setParameter("hard", hard).setParameter("soft", soft);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listmydemand() {
        try {
            Query query = em.createNamedQuery("Demand.findmydemand");
            query.setParameter("idusers", ((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser")));
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listmydemandHard() {
        try {
            Query query = em.createNamedQuery("Demand.findmydemandHard");
            query.setParameter("idusers", ((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser")));
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listmydemandSoft() {
        try {
            Query query = em.createNamedQuery("Demand.findmydemandSoft");
            query.setParameter("idusers", ((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser")));
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemands(Hardwarecons hard, Softwarecons soft) {
        try {
            Query query = em.createNamedQuery("Demand.findByhardsofts");
            query.setParameter("hard", hard).setParameter("soft", soft);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandnotconfirmed() {
        try {
            Query query = em.createNamedQuery("Demand.findByNotConfirmed");
            query.setParameter("service", ((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser")).getIdservice());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandhardvalidated() {
        try {
            Query query = em.createNamedQuery("Demand.findByValidatedhard");
            //   query.setParameter("id", n.getIdhardwarecons());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandsoftvalidated() {
        try {
            Query query = em.createNamedQuery("Demand.findByValidatedsoft");
            //   query.setParameter("id", n.getIdhardwarecons());
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandrejected() {
        try {
            Query query = em.createNamedQuery("Demand.findByRejected");
            //query.setParameter("hard", hard).setParameter("soft", soft);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandonhold() {
        try {
            Query query = em.createNamedQuery("Demand.findByOnHold");
            //query.setParameter("hard", hard).setParameter("soft", soft);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandconfirmed() {
        try {
            Query query = em.createNamedQuery("Demand.findByConfirmed");
            //query.setParameter("hard", hard).setParameter("soft", soft);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandattributed() {
        try {
            Query query = em.createNamedQuery("Demand.findByAttributed");
            //query.setParameter("hard", hard).setParameter("soft", soft);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Demand> listdemandtreated() {
        try {
            Query query = em.createNamedQuery("Demand.findByTreated");
            //query.setParameter("hard", hard).setParameter("soft", soft);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int countdemand(Date date, Date date2) {
        try {
            Query query = em.createNamedQuery("Demand.stat");

            query.setParameter("date", date);
            query.setParameter("date1", date2);
            System.out.println("enter");
            System.out.println(((Long) query.getSingleResult()).intValue());
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
            System.out.println("no verify ");
            return '0';
        }
    }

    @Override
    public int countdemand2() {
        try {
            Query query = em.createNamedQuery("Demand.stat2");
            
            System.out.println("enter");
            System.out.println(((Long) query.getSingleResult()).intValue());
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
            System.out.println("no verify ");
            return '0';
        }
    }

    @Override
    public int countdemand3() {
        try {
            Query query = em.createNamedQuery("Demand.stat3");
            
            System.out.println("enter");
            System.out.println(((Long) query.getSingleResult()).intValue());
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
            System.out.println("no verify ");
            return '0';
        }
    }
    @Override
    public int countdemand4() {
        try {
            Query query = em.createNamedQuery("Demand.stat4");
            
            System.out.println("enter");
            System.out.println(((Long) query.getSingleResult()).intValue());
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
            System.out.println("no verify ");
            return '0';
        }
    }
    @Override
    public int countdemand5() {
        try {
            Query query = em.createNamedQuery("Demand.stat5");
            
            System.out.println("enter");
            System.out.println(((Long) query.getSingleResult()).intValue());
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
            System.out.println("no verify ");
            return '0';
        }
    }
    @Override
    public int countdemand6() {
        try {
            Query query = em.createNamedQuery("Demand.stat6");
            
            System.out.println("enter");
            System.out.println(((Long) query.getSingleResult()).intValue());
            return ((Long) query.getSingleResult()).intValue();

        } catch (Exception e) {
            System.out.println("no verify ");
            return '0';
        }
    }
}
