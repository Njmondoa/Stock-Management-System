/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Attribution;
import entities.Demand;
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
public class AttributionFacade extends AbstractFacade<Attribution> implements AttributionFacadeLocal {
    @PersistenceContext(unitName = "GESCI_WEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttributionFacade() {
        super(Attribution.class);
    }
    @Override
    public List<Attribution> listmyattribution(){
        try {
            Query query = em.createNamedQuery("Attribution.findmyattribution");
            query.setParameter("iddemand", ((Users) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser")));
            return query.getResultList();
        }catch (Exception e){
            return null;
        }
    }
     @Override
    public int countdemand(Date date, Date date2) {
        try {
            Query query = em.createNamedQuery("Attribution.stat");
          
          query.setParameter("date", date);
          query.setParameter("date1", date2);
            System.out.println("enter");
            System.out.println(((Long) query.getSingleResult()).intValue());
            return  ((Long) query.getSingleResult()).intValue();
          
        }catch (Exception e) {
            System.out.println("no verify ");
            return '0';
        }
    }
}
