package persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.CustomerCart;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-22T14:03:54")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> country;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, String> ccNum;
    public static volatile SingularAttribute<Customer, String> fullName;
    public static volatile SingularAttribute<Customer, String> adress;
    public static volatile SingularAttribute<Customer, String> postCode;
    public static volatile SingularAttribute<Customer, Integer> id;
    public static volatile SingularAttribute<Customer, String> userName;
    public static volatile SingularAttribute<Customer, String> ccExp;
    public static volatile SingularAttribute<Customer, String> email;
    public static volatile CollectionAttribute<Customer, CustomerCart> customerCartCollection;

}