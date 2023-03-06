/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Hardwarecons;
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
public class HardwareconsFacade extends AbstractFacade<Hardwarecons> implements HardwareconsFacadeLocal {
    @PersistenceContext(unitName = "GESCI_WEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HardwareconsFacade() {
        super(Hardwarecons.class);
    }
     @Override
    public Integer nextId(){
        try {
            Query query = em.createNamedQuery("Hardwarecons.nextId");
            return ((Integer) query.getSingleResult()) + 1;
        } catch (Exception e) {
            return 1;
        }
    }
    @Override
    public Hardwarecons findByIdhardwarecons(){
        try {
            Query query = em.createNamedQuery("Hardwarecons.findByIdhardwarecons");
            return ((Hardwarecons) query.getSingleResult());
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Hardwarecons> findById(){
        try {
            Query query = em.createNamedQuery("Hardwarecons.findById");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Hardwarecons> alert(){
        try {
            Query query = em.createNamedQuery("Hardwarecons.alert");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public int findByStockquantity(String name){
        try {
            Query query = em.createNamedQuery("Hardwarecons.findByQuantite");
            query.setParameter("name", name);
            return ((Integer) query.getSingleResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
             return 0;
        }
       
    }
}
