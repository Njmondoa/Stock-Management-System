package entities;

import entities.Attribution;
import entities.Demand;
import entities.Itmaterial;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Softwarecons.class)
public class Softwarecons_ { 

    public static volatile SingularAttribute<Softwarecons, String> license;
    public static volatile SingularAttribute<Softwarecons, Integer> idsoftwarecons;
    public static volatile CollectionAttribute<Softwarecons, Attribution> attributionCollection;
    public static volatile SingularAttribute<Softwarecons, String> supplier;
    public static volatile SingularAttribute<Softwarecons, Integer> criticalquantity;
    public static volatile SingularAttribute<Softwarecons, String> name;
    public static volatile CollectionAttribute<Softwarecons, Demand> demandCollection;
    public static volatile SingularAttribute<Softwarecons, Integer> stockquantity;
    public static volatile SingularAttribute<Softwarecons, Itmaterial> idmaterial;

}