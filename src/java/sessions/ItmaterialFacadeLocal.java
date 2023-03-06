/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Itmaterial;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corei_5
 */
@Local
public interface ItmaterialFacadeLocal {

    void create(Itmaterial itmaterial);

    void edit(Itmaterial itmaterial);

    void remove(Itmaterial itmaterial);

    Itmaterial find(Object id);

    List<Itmaterial> findAll();

    List<Itmaterial> findRange(int[] range);

    int count();
    
    List<Itmaterial> listitmaterials();
    
}
