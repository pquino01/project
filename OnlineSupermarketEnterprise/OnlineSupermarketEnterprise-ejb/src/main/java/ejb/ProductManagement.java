/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Product;


/**
 *
 * @author pablo
 */
@Stateless
public class ProductManagement extends AbstractFacade<Product> implements ProductManagementRemote {

    @PersistenceContext(unitName = "On-lineSupermarketEJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductManagement() {
        super(Product.class);
    }
    
    @Override
    public Product findProductByName(String name) {
        Product product=null;
        try{
            product=(Product) em.createNamedQuery("Product.findByName").setParameter("name", name).getSingleResult();
        }catch(NullPointerException e){};
        
        return product;

    }
    
    public Boolean productAlreadyExists(String name){
        List<Product> allProducts= findAll();
        for(Product p: allProducts){
            if(p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    public void editProduct(Product product){
        edit(product);
    }
    
    public void removeProduct(Product product){
        remove(product);
        //em.flush();
    }
    
    public void registerNewProduct(Product newProduct){
        create(newProduct);
    }
    
    public Product findProductById (int id){
        return find(id);
    }
    
    public List<Product> findAllProducts(){
        List<Product> allProducts=findAll();
        return allProducts;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
    

    
}
