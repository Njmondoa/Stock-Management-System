/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Logfile;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corei_5
 */
@Local
public interface LogfileFacadeLocal {

    void create(Logfile logfile);

    void edit(Logfile logfile);

    void remove(Logfile logfile);

    Logfile find(Object id);

    List<Logfile> findAll();

    List<Logfile> findRange(int[] range);

    int count();
    
}
