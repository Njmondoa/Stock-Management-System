/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corei_5
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();
    
    Users findByLoginPassword(String login, String password);
    
    List<Users> findByLogin(String login);
    
}
