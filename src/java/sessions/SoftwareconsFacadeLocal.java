/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Softwarecons;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corei_5
 */
@Local
public interface SoftwareconsFacadeLocal {

    void create(Softwarecons softwarecons);

    void edit(Softwarecons softwarecons);

    void remove(Softwarecons softwarecons);

    Softwarecons find(Object id);

    List<Softwarecons> findAll();

    List<Softwarecons> findRange(int[] range);

    int count();
    
    Integer nextId();
    
    Softwarecons findByIdsoftwarecons();
    
    int findByStockquantity(String name);
    
    List<Softwarecons> findById();
    
    List<Softwarecons> alert();
}
