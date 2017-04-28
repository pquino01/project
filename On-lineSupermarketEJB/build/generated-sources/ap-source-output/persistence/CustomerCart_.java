package persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.Customer;
import persistence.ProducthascustomerCart;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-22T14:03:54")
@StaticMetamodel(CustomerCart.class)
public class CustomerCart_ { 

    public static volatile SingularAttribute<CustomerCart, Double> amount;
    public static volatile CollectionAttribute<CustomerCart, ProducthascustomerCart> producthascustomerCartCollection;
    public static volatile SingularAttribute<CustomerCart, Customer> customerId;
    public static volatile SingularAttribute<CustomerCart, Integer> id;

}