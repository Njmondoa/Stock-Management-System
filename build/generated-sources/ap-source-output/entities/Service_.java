package entities;

import entities.Department;
import entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Service.class)
public class Service_ { 

    public static volatile SingularAttribute<Service, Integer> idservice;
    public static volatile SingularAttribute<Service, String> name;
    public static volatile CollectionAttribute<Service, Users> usersCollection;
    public static volatile SingularAttribute<Service, Department> iddepartment;

}