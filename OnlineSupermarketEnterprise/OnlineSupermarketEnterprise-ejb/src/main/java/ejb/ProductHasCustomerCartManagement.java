/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.ProducthascustomerCart;

/**
 *
 * @author pablo
 */
@Stateless
public class ProductHasCustomerCartManagement extends AbstractFacade<ProducthascustomerCart> implements ProductHasCustomerCartManagementRemote {

    @PersistenceContext(unitName = "On-lineSupermarketEJBPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductHasCustomerCartManagement() {
        super(ProducthascustomerCart.class);
    }
    
    
    @Override
    public List<ProducthascustomerCart> findByOrderId(Object id) {
        return em.createNamedQuery("ProducthascustomerCart.findByCustomerCartid").setParameter("customerCartid", id).getResultList();
    }
    
    @Override
    public double calculateSalesReturn (){
        double result= 0;
        List<ProducthascustomerCart> allOrderedProducts= findAll();
        
        for (ProducthascustomerCart orderedProduct: allOrderedProducts){
            
            try{
                
            //product price * product quantity
            result+=orderedProduct.getProduct().getPrice()*orderedProduct.getQuantity();
            result = Math.floor(result * 100) / 100;
        
            }catch(NullPointerException e){};
        }
        return result;
    }
    
    @Override
    public double calculateCostOfGoods (){
        double result= 0;
        List<ProducthascustomerCart> allOrderedProducts= findAll();
        for (ProducthascustomerCart orderedProduct: allOrderedProducts){
            
            try{
                
            //product market cost * product quantity
            result+=orderedProduct.getProduct().getMarketCost()*orderedProduct.getQuantity();
            result = Math.floor(result * 100) / 100;
            
            }catch(NullPointerException e){};
        }
        return result;
    }
    
    @Override
    public double calculateNetSales (){
        double result= calculateSalesReturn()-calculateCostOfGoods();
        result = Math.floor(result * 100) / 100; //round 2 digits max decimal
        return result;
    }
    
    @Override
    public Map calculatePercentagesProductsSoldPerCategory (){
        Map<String,Double> mapPercentages=new HashMap<String,Double>();
        List<ProducthascustomerCart> allOrderedProducts= findAll();
        int totalProductsDiary=0;
        int totalProductsMeatAndFish=0;
        int totalProductsBakery=0;
        int totalProductsFruitAndVeg=0;
        for (ProducthascustomerCart orderedProduct: allOrderedProducts){
            if (orderedProduct.getProduct().getCategoryId().getName().equals("Diary")){
                totalProductsDiary+=orderedProduct.getQuantity();
            }
            if (orderedProduct.getProduct().getCategoryId().getName().equals("Meat&Fish")){
                totalProductsMeatAndFish+=orderedProduct.getQuantity();
            }
            if (orderedProduct.getProduct().getCategoryId().getName().equals("Bakery")){
                totalProductsBakery+=orderedProduct.getQuantity();
            }
            if (orderedProduct.getProduct().getCategoryId().getName().equals("Fruit&Veg")){
                totalProductsFruitAndVeg+=orderedProduct.getQuantity();
            }
        }
        int totalProducts= totalProductsDiary+totalProductsMeatAndFish+totalProductsBakery+totalProductsFruitAndVeg;
        mapPercentages.put("Diary",(double) totalProductsDiary/totalProducts);
        mapPercentages.put("Meat&Fish",(double) totalProductsMeatAndFish/totalProducts);
        mapPercentages.put("Bakery",(double) totalProductsBakery/totalProducts);
        mapPercentages.put("Fruit&Veg",(double) totalProductsFruitAndVeg/totalProducts);
        return mapPercentages;
    }
}
