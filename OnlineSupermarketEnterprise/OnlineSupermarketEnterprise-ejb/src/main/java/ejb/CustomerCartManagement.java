/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Category;
import persistence.CustomerCart;

/**
 *
 * @author pablo
 */
@Stateless
public class CustomerCartManagement extends AbstractFacade<CustomerCart> implements CustomerCartManagementRemote {
    
    @PersistenceContext(unitName = "On-lineSupermarketEJBPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerCartManagement() {
        super(CustomerCart.class);
    }
    
    @Override
    public CustomerCart findCustomerCartByOrderId (int orderId){
        return find(orderId);
    }
    
}
