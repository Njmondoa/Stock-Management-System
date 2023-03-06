package entities;

import entities.Demand;
import entities.Hardwarecons;
import entities.Softwarecons;
import entities.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Attribution.class)
public class Attribution_ { 

    public static volatile SingularAttribute<Attribution, Date> dateofattribution;
    public static volatile SingularAttribute<Attribution, Softwarecons> idsoftwarecons;
    public static volatile SingularAttribute<Attribution, Hardwarecons> idhardwarecons;
    public static volatile SingularAttribute<Attribution, Integer> qtyattributed;
    public static volatile SingularAttribute<Attribution, Users> idusers;
    public static volatile SingularAttribute<Attribution, Demand> iddemand;
    public static volatile SingularAttribute<Attribution, Integer> idattribution;

}