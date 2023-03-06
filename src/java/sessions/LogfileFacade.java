/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Logfile;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Corei_5
 */
@Stateless
public class LogfileFacade extends AbstractFacade<Logfile> implements LogfileFacadeLocal {
    @PersistenceContext(unitName = "GESCI_WEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LogfileFacade() {
        super(Logfile.class);
    }
    
}
