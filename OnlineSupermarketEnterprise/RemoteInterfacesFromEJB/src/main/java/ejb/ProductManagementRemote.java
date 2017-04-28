/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Remote;
import persistence.Product;

/**
 *
 * @author pablo
 */
@Remote
public interface ProductManagementRemote {

    Product findProductByName(String name);
    
    List<Product> findAllProducts();
    
    Product findProductById (int id);
    
    void registerNewProduct(Product newProduct);
    
    void editProduct(Product product);
    
    void removeProduct(Product product);
    
    Boolean productAlreadyExists(String name);
    
}
