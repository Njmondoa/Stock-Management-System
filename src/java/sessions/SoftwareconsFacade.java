/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Softwarecons;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Corei_5
 */
@Stateless
public class SoftwareconsFacade extends AbstractFacade<Softwarecons> implements SoftwareconsFacadeLocal {
    @PersistenceContext(unitName = "GESCI_WEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SoftwareconsFacade() {
        super(Softwarecons.class);
    }
    @Override
    public Integer nextId(){
        try {
            Query query = em.createNamedQuery("Softwarecons.nextId");
            return ((Integer) query.getSingleResult()) + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    @Override
    public Softwarecons findByIdsoftwarecons(){
        try {
            Query query = em.createNamedQuery("Softwarecons.findByIdsoftwarecons");
            return ((Softwarecons) query.getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Softwarecons> findById(){
        try {
            Query query = em.createNamedQuery("Softwarecons.findById");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
}
    @Override
    public List<Softwarecons> alert(){
        try {
            Query query = em.createNamedQuery("Softwarecons.alert");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public int findByStockquantity(String name){
        try {
            Query query = em.createNamedQuery("Softwarecons.findByQuantite");
            query.setParameter("name", name);
            return ((Integer) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
             return 0;
        }
    }
}
    
    
