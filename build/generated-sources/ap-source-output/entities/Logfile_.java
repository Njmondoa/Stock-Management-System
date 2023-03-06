package entities;

import entities.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-22T13:20:33")
@StaticMetamodel(Logfile.class)
public class Logfile_ { 

    public static volatile SingularAttribute<Logfile, Date> date;
    public static volatile SingularAttribute<Logfile, String> ipadresse;
    public static volatile SingularAttribute<Logfile, Users> idusers;
    public static volatile SingularAttribute<Logfile, Integer> idlogfile;
    public static volatile SingularAttribute<Logfile, String> name;
    public static volatile SingularAttribute<Logfile, Date> time;
    public static volatile SingularAttribute<Logfile, String> target;

}