package entities;

import entities.Attribution;
import entities.Hardwarecons;
import entities.Softwarecons;
import entities.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Demand.class)
public class Demand_ { 

    public static volatile CollectionAttribute<Demand, Attribution> attributionCollection;
    public static volatile SingularAttribute<Demand, Softwarecons> idsoftwarecons;
    public static volatile SingularAttribute<Demand, Hardwarecons> idhardwarecons;
    public static volatile SingularAttribute<Demand, String> color;
    public static volatile SingularAttribute<Demand, Users> idusers;
    public static volatile SingularAttribute<Demand, Integer> iddemand;
    public static volatile SingularAttribute<Demand, Integer> qtydemanded;
    public static volatile SingularAttribute<Demand, Date> dateofdemand;
    public static volatile SingularAttribute<Demand, String> state;

}