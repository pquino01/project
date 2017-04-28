/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import persistence.Customer;
import persistence.CustomerCart;
import cart.ShoppingCart;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface OrderManagementRemote {
    
    int placeOrder(Customer c, ShoppingCart cart);
    
    CustomerCart addOrder(Customer customer, ShoppingCart cart);
    
    void addOrderedItems(CustomerCart order, ShoppingCart cart);
    
    Map getOrderDetailsFromDB(int orderId);
    
}
