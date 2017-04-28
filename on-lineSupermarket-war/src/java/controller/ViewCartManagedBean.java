/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;



import cart.ShoppingCart;
import cart.ShoppingCartItem;
import ejb.CustomerManagementRemote;

import ejb.OrderManagementRemote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.InitialContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import persistence.Customer;
import persistence.Product;
import persistence.ProducthascustomerCart;

/**
 *
 * @author pablo
 */
@Named(value = "viewCartManagedBean")
@SessionScoped
public class ViewCartManagedBean implements Serializable {

    //@EJB
    private OrderManagementRemote orderManagement;

    //@EJB
    private CustomerManagementRemote customerManagement;

    /**
     * injection of the travellerLoginManagedBean
     */
    @Inject
    private CustomerLoginManagedBean loginBean;

    

    private Integer quantityProduct;

    private double totalOrderPrice;

    private ShoppingCartItem cartItem;

    private ShoppingCart cart;

    private Customer user;

    private String ccNum;

    private String ccExp;

    private Map orderMap;

    private MenuModel model;

    private ArrayList<Product> allProducts;

    private ArrayList<Product> productsCloseToDiscount;

    public ArrayList<Product> getProductsCloseToDiscount() {
        return productsCloseToDiscount;
    }

    public void setProductsCloseToDiscount(ArrayList<Product> productsCloseToDiscount) {
        this.productsCloseToDiscount = productsCloseToDiscount;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    List<ProducthascustomerCart> orderedProducts;

    ArrayList<Product> products;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<ProducthascustomerCart> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<ProducthascustomerCart> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public Map getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map orderMap) {
        this.orderMap = orderMap;
    }

    public Customer getUser() {
        return user;
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public String getCcNum() {
        return ccNum;
    }

    public void setCcNum(String ccNum) {
        this.ccNum = ccNum;
    }

    public String getCcExp() {
        return ccExp;
    }

    public void setCcExp(String ccExp) {
        this.ccExp = ccExp;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(ShoppingCartItem cartItem) {
        this.cartItem = cartItem;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    /**
     * Creates a new instance of viewCartManagedBean
     */
    public ViewCartManagedBean() {
        try {
            InitialContext c = new InitialContext();
            orderManagement = (OrderManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/OrderManagement");
            customerManagement = (CustomerManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/CustomerManagement");
        } catch (Exception e) {
        };
    }

    public void addToCart(Product p) {
        try {
            if (loginBean.getLoggedIn()) {
                if (quantityProduct >= 1) {
                    if (cart == null) {
                        cart = new ShoppingCart();
                    }
                    cart.addItem(p, quantityProduct.shortValue());
                    updateMenuView();
                    System.out.println(cart.getItems().get(0).getProduct().getName() + cart.getItems().get(0).getTotal());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product added"));
                } 
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Must be logged in before adding items to Cart"));
            }
        } catch (NullPointerException e) {};
        quantityProduct=null;
    }
   
    /*public void addToCartSingleProduct(Product p) {
        try {
            if (loginBean.getLoggedIn()) {

                if (cart == null) {
                    cart = new ShoppingCart();
                }
                cart.addItem(p, (short) 1);
                //updateMenuView(p, (short) 1);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product added"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Must be logged in before adding items to Cart"));
            }
        } catch (NullPointerException e) {
        }
    }*/

    public String purchaseItems() {
        try {
            if (loginBean.getLoggedIn()) {
                user = customerManagement.findUser(loginBean.getUserName());
                if (user.getCcNum() != null && user.getCcExp() != null) {
                    ccNum = user.getCcNum();
                    ccExp = user.getCcExp();
                }

                return "payingStore.xhtml";

            } else {
                FacesContext.getCurrentInstance().addMessage("cancelMessage", new FacesMessage("Must be logged in before purchasing items"));
            }
        } catch (NullPointerException e) {
        }
        return null;
    }

    /**
     * once an itinerary is paid, it checks that the paying time did not exceed
     * 2 min (otherwise is cancelled) and generates the ticket number/s for the
     * itinerary
     *
     * @return the redirect url of the home html page
     */
    public String paidCart() {
        
        
        try {
            user.setCcNum(ccNum);
            user.setCcExp(ccExp);
            customerManagement.editCustomer(user);
            System.out.println("eo aquillega");
            if (cart != null) {
                int orderId = orderManagement.placeOrder(user, cart);
                // if order processed successfully send user to confirmation page
                if (orderId != 0) {

                    // dissociate shopping cart from session
                    cart = null;
                    System.out.println("principioooooooooo");
                    // get order details
                    Map orderMap = orderManagement.getOrderDetailsFromDB(orderId);
                    System.out.println("finaaaaaaaaaaal");
                    // place order details 
                    user = (Customer) orderMap.get("customer");
                    totalOrderPrice = (double) orderMap.get("orderTotalPrice");
                    products = new ArrayList<Product>();
                    products = (ArrayList<Product>) orderMap.get("products");

                    orderedProducts = (List<ProducthascustomerCart>) orderMap.get("orderedProducts");

                    System.out.println("fndjfdjfdjfjdf------fjjfjfjf333333333333333333");
                    //System.out.println(orderedProducts.get(1).);

                    return "confirmationCheckout.xhtml";

                    // otherwise, send back to checkout page and display error
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Order unsuccesful"));

                }
            }
        } catch (NullPointerException e) {};
        cart=null;
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        cart.update(((ShoppingCartItem) event.getObject()).getProduct(), ((ShoppingCartItem) event.getObject()).getQuantity());
        //RequestContext.getCurrentInstance().update(":cart");
        FacesMessage msg = new FacesMessage("Product Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        cart.update(((ShoppingCartItem) event.getObject()).getProduct(), (short) 0);
        FacesMessage msg = new FacesMessage("Product deleted from cart");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void updateMenuView() {
        if (cart!=null){
        model = new DefaultMenuModel();
        for (ShoppingCartItem i : cart.getItems()) {
            DefaultMenuItem item = new DefaultMenuItem("x" + i.getQuantity());
            //InputStream iStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/YOUR_IMAGE_NAME");
            // item.setIcon("resources/images/products/"+p.getName()+"png");
            // item.setStyleClass("xlogo");

            item.setIcon("xlogo");

            model.addElement(item);
        }
        }else{
            model = new DefaultMenuModel();
        }
    }

}
