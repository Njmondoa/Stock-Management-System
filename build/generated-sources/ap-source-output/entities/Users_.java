package entities;

import entities.Attribution;
import entities.Demand;
import entities.Logfile;
import entities.Menu;
import entities.Service;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Date> dateofbirth;
    public static volatile SingularAttribute<Users, String> matricule;
    public static volatile SingularAttribute<Users, String> sex;
    public static volatile SingularAttribute<Users, String> phonenumber;
    public static volatile SingularAttribute<Users, String> login;
    public static volatile SingularAttribute<Users, Service> idservice;
    public static volatile SingularAttribute<Users, String> efunction;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile CollectionAttribute<Users, Attribution> attributionCollection;
    public static volatile SingularAttribute<Users, Integer> idusers;
    public static volatile SingularAttribute<Users, String> surname;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, String> adresse;
    public static volatile CollectionAttribute<Users, Demand> demandCollection;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile CollectionAttribute<Users, Logfile> logfileCollection;
    public static volatile CollectionAttribute<Users, Menu> menuCollection;

}