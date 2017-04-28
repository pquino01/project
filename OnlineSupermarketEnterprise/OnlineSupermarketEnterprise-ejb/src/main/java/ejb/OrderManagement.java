/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.Customer;
import persistence.CustomerCart;
import persistence.Product;
import persistence.ProducthascustomerCart;
import persistence.ProducthascustomerCartPK;

/**
 *
 * @author pablo
 */
@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManagement implements OrderManagementRemote {

    @PersistenceContext(unitName = "On-lineSupermarketEJBPU")
    private EntityManager em;

    /*@Resource
    private SessionContext context;*/
    @EJB
    private ProductManagementRemote productManagement;
    @EJB
    private CustomerCartManagementRemote customerCartManagement;
    @EJB
    private ProductHasCustomerCartManagementRemote productHasCustomerCartManagement;

    public OrderManagement() {
       /* try {
            InitialContext c = new InitialContext();
            customerCartManagement = (CustomerCartManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/CustomerCartManagement");
            productHasCustomerCartManagement = (ProductHasCustomerCartManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/ProductHasCustomerCartManagement");
            productManagement = (ProductManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/ProductManagement");
        } catch (Exception e) {};*/
    }

    
    //@TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public int placeOrder(Customer c, ShoppingCart cart) {
        System.out.println("entra en el ejb");
        try {

            CustomerCart order = addOrder(c, cart);
            addOrderedItems(order, cart);
            return order.getId();
        } catch (Exception e) {
            //context.setRollbackOnly();
            return 0;
        }
    }

    public CustomerCart addOrder(Customer customer, ShoppingCart cart) {

        // set up customer order
        CustomerCart order = new CustomerCart();
        order.setCustomerId(customer);
        order.setAmount(cart.getTotalCartPrice());

        em.persist(order);
        return order;
    }

    public void addOrderedItems(CustomerCart order, ShoppingCart cart) {

        em.flush();

        List<ShoppingCartItem> items = cart.getItems();

        // iterate through shopping cart and create OrderedProducts
        for (ShoppingCartItem scItem : items) {

            int productId = scItem.getProduct().getId();

            // set up primary key object
            ProducthascustomerCartPK orderedProductPK = new ProducthascustomerCartPK();
            orderedProductPK.setCustomerCartid(order.getId());
            orderedProductPK.setProductId(productId);

            // create ordered item using PK object
            ProducthascustomerCart orderedItem = new ProducthascustomerCart(orderedProductPK);

            // set quantity
            orderedItem.setQuantity(scItem.getQuantity());

            em.persist(orderedItem);
        }
    }

    @Override
    public Map getOrderDetailsFromDB(int orderId) {

        Map orderMap = new HashMap();
        try {
            // get order
            CustomerCart order = customerCartManagement.findCustomerCartByOrderId(orderId);

            // get customer
            Customer customer = order.getCustomerId();

            // get product details for ordered items
            ArrayList<ProducthascustomerCart> orderedProducts = new ArrayList<ProducthascustomerCart>();

            orderedProducts.addAll(productHasCustomerCartManagement.findByOrderId(orderId));

            // get product details for ordered items
            List<Product> products = new ArrayList<Product>();

            for (ProducthascustomerCart op : orderedProducts) {
                Product p = (Product) productManagement.findProductById(op.getProducthascustomerCartPK().getProductId());
                System.out.println("...............g..................");
                System.out.println(p.getName());
                products.add(p);
            }

            // add each item to orderMap
            orderMap.put("orderTotalPrice", order.getAmount());
            orderMap.put("customer", customer);
            orderMap.put("orderedProducts", orderedProducts);
            orderMap.put("products", products);

        } catch (NullPointerException e) {
        };
        return orderMap;
    }

}
