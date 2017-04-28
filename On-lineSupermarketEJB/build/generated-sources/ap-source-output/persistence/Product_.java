package persistence;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistence.Category;
import persistence.ProducthascustomerCart;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-22T14:03:54")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, byte[]> image;
    public static volatile CollectionAttribute<Product, ProducthascustomerCart> producthascustomerCartCollection;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Double> marketCost;
    public static volatile SingularAttribute<Product, Integer> id;
    public static volatile SingularAttribute<Product, Category> categoryId;

}