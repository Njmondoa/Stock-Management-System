/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Attribution;
import entities.Demand;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corei_5
 */
@Local
public interface AttributionFacadeLocal {

    void create(Attribution attribution);

    void edit(Attribution attribution);

    void remove(Attribution attribution);

    Attribution find(Object id);

    List<Attribution> findAll();

    List<Attribution> findRange(int[] range);

    int count();
    
 List<Attribution> listmyattribution();
 
  int countdemand(Date date, Date date2);

}
