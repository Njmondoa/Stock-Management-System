package entities;

import entities.Menu;
import entities.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile SingularAttribute<Menu, String> name;
    public static volatile SingularAttribute<Menu, Integer> idmenu;
    public static volatile CollectionAttribute<Menu, Users> usersCollection;
    public static volatile SingularAttribute<Menu, Menu> menIdmenu;
    public static volatile SingularAttribute<Menu, String> key;
    public static volatile CollectionAttribute<Menu, Menu> menuCollection;

}