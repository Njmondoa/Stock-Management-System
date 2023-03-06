package entities;

import entities.Hardwarecons;
import entities.Softwarecons;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Itmaterial.class)
public class Itmaterial_ { 

    public static volatile SingularAttribute<Itmaterial, String> characteristics;
    public static volatile CollectionAttribute<Itmaterial, Softwarecons> softwareconsCollection;
    public static volatile SingularAttribute<Itmaterial, String> name;
    public static volatile SingularAttribute<Itmaterial, Integer> idmaterial;
    public static volatile CollectionAttribute<Itmaterial, Hardwarecons> hardwareconsCollection;

}