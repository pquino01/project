/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import persistence.Category;
import persistence.Product;

/**
 *
 * @author pablo
 */
@Remote
public interface CategoryManagementRemote {
   //ArrayList<String> getAllCategories ();
    Boolean categoryExists (String categoryName);
    Category findCategoryByName (String name);
    List<Category> getAllCategories ();
    ArrayList<Product> getProductCollectionGivenCategory (String name);
    
}
