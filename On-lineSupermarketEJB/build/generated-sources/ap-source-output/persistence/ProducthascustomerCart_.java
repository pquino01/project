package persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.CustomerCart;
import persistence.Product;
import persistence.ProducthascustomerCartPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-22T14:03:54")
@StaticMetamodel(ProducthascustomerCart.class)
public class ProducthascustomerCart_ { 

    public static volatile SingularAttribute<ProducthascustomerCart, Product> product;
    public static volatile SingularAttribute<ProducthascustomerCart, Short> quantity;
    public static volatile SingularAttribute<ProducthascustomerCart, ProducthascustomerCartPK> producthascustomerCartPK;
    public static volatile SingularAttribute<ProducthascustomerCart, CustomerCart> customerCart;

}