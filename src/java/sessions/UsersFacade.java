/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Users;
import static java.util.Collections.list;
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
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {
    @PersistenceContext(unitName = "GESCI_WEBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    @Override
    public Users findByLoginPassword(String login, String password){
        try {
            Query query = em.createNamedQuery("Users.findByLoginPassword");
            query.setParameter("login", login).setParameter("password", password);
            return (Users) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
    
    @Override
    public List<Users> findByLogin(String login) {
        try{
            Query query = em.createNamedQuery("Users.findByLogin");
            query.setParameter("login", login);
            return query.getResultList();
            
        }catch (Exception e){
            return null;
        }
    }
}
