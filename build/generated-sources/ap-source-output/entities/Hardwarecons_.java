package entities;

import entities.Attribution;
import entities.Demand;
import entities.Itmaterial;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Hardwarecons.class)
public class Hardwarecons_ { 

    public static volatile SingularAttribute<Hardwarecons, String> reference;
    public static volatile CollectionAttribute<Hardwarecons, Attribution> attributionCollection;
    public static volatile SingularAttribute<Hardwarecons, Integer> idhardwarecons;
    public static volatile SingularAttribute<Hardwarecons, String> color;
    public static volatile SingularAttribute<Hardwarecons, String> supplier;
    public static volatile SingularAttribute<Hardwarecons, Integer> criticalquantity;
    public static volatile SingularAttribute<Hardwarecons, String> name;
    public static volatile CollectionAttribute<Hardwarecons, Demand> demandCollection;
    public static volatile SingularAttribute<Hardwarecons, Integer> stockquantity;
    public static volatile SingularAttribute<Hardwarecons, Itmaterial> idmaterial;

}