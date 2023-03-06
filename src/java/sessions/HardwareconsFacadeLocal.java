/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Hardwarecons;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corei_5
 */
@Local
public interface HardwareconsFacadeLocal {

    void create(Hardwarecons hardwarecons);

    void edit(Hardwarecons hardwarecons);

    void remove(Hardwarecons hardwarecons);

    Hardwarecons find(Object id);

    List<Hardwarecons> findAll();

    List<Hardwarecons> findRange(int[] range);

    int count();
    
    Integer nextId();
    
    Hardwarecons findByIdhardwarecons();
    
    public List<Hardwarecons> findById();
    
    int findByStockquantity(String name);
    
    List<Hardwarecons> alert();
}
