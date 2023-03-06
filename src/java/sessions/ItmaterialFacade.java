/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Itmaterial;
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
public class ItmaterialFacade extends AbstractFacade<Itmaterial> implements ItmaterialFacadeLocal {
    @PersistenceContext(unitName = "GESCI_WEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItmaterialFacade() {
        super(Itmaterial.class);
    }
    @Override
    public List<Itmaterial> listitmaterials(){
        try {
            Query query = em.createNamedQuery("Itmaterial.findByIdmaterial");
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
