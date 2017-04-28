/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Category;
import persistence.Product;

/**
 *
 * @author pablo
 */
@Stateless
public class CategoryManagement extends AbstractFacade<Category> implements CategoryManagementRemote {

    @PersistenceContext(unitName = "On-lineSupermarketEJBPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryManagement() {
        super(Category.class);
    }
    /*
    @Override
    public ArrayList<String> getAllCategories (){
        Query query = em.createQuery("SELECT c.name FROM Category c");
        List<String> list = query.getResultList();
        ArrayList<String> listOfCategories = new ArrayList<>();
        listOfCategories.addAll(list);
        return listOfCategories;
    }*/
    
    public List<Category> getAllCategories (){
        return findAll();
    }
    
    
    @Override
    public Boolean categoryExists (String categoryName){
        Query query = em.createQuery("SELECT COUNT(c.name) FROM Category c WHERE c.name=:name");
        query.setParameter("name", categoryName);
        long matchCounter = (Long) query.getSingleResult();
        return matchCounter > 0;
    }
    
    
    
    @Override
    public Category findCategoryByName (String name){
        
        try{
        Category c= (Category)em.createNamedQuery("Category.findByName").setParameter("name", name).getSingleResult();
       
        return c;
        }catch(NullPointerException e){};
        return null;
        
    }
    
    @Override
    public ArrayList<Product> getProductCollectionGivenCategory (String name){
       
        Category c = findCategoryByName (name);
        /*ArrayList<String> list = new ArrayList();
        try{
        for(Product p: c.getProductCollection()){
            System.out.println(p.getName()+"$$$$$$$$$$$$!!!!!!!");
            list.add(p.getName());
        }
        }catch(NullPointerException e){};*/
        ArrayList list =new ArrayList();
        list.addAll(c.getProductCollection());
        return list;
    }

   
}


