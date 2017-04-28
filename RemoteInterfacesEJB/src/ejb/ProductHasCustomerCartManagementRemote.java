/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import persistence.ProducthascustomerCart;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface ProductHasCustomerCartManagementRemote {
    
    List<ProducthascustomerCart> findByOrderId(Object id);
    
    double calculateSalesReturn ();
    
    double calculateCostOfGoods ();
    
    double calculateNetSales ();
    
    Map calculatePercentagesProductsSoldPerCategory ();
}
