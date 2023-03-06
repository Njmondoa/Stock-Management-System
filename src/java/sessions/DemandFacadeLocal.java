/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Demand;
import entities.Department;
import entities.Hardwarecons;
import entities.Softwarecons;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Corei_5
 */
@Local
public interface DemandFacadeLocal {

    void create(Demand demand);

    void edit(Demand demand);

    void remove(Demand demand);

    Demand find(Object id);

    List<Demand> findAll();

    List<Demand> findRange(int[] range);

    int count();

    public List<Demand> listdemand(Hardwarecons hard, Softwarecons soft);

    public List<Demand> listdemands(Hardwarecons hard, Softwarecons soft);

    public List<Demand> listdemandnotconfirmed();

    public List<Demand> listdemandhardvalidated();

    public List<Demand> listdemandsoftvalidated();

    public List<Demand> listdemandconfirmed();

    public List<Demand> listdemandtreated();

    public List<Demand> listdemandrejected();

    public List<Demand> listdemandonhold();

    public List<Demand> listdemandattributed();

    public List<Demand> listmydemand();

    public List<Demand> listmydemandHard();

    public List<Demand> listmydemandSoft();

    public int countdemand(Date date, Date date2);

    public int countdemand2();
    
    public int countdemand6();
    
    public int countdemand5();
    
    public int countdemand4();
    
    public int countdemand3();
}
