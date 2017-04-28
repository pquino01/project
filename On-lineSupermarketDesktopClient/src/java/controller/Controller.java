/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import ejb.CategoryManagementRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import persistence.Category;
import ejb.CustomerManagementRemote;
import ejb.OrderManagementRemote;
import java.awt.CardLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistence.Customer;
import persistence.Product;
import persistence.ProducthascustomerCart;
import view.JFrameResetPassword;
import view.JFrameSupermarket;
import view.JPanelCategory;
import view.JPanelConfirmation;
import view.JPanelViewCart;

/**
 *
 * @author pablo
 */
public class Controller {

    private CustomerManagementRemote customerManagement;

    private CategoryManagementRemote categoryManagement;
    private OrderManagementRemote orderManagement;

    private JFrame jFrameLogin;

    private JFrameResetPassword jFrameResetPassword;

    private JPanelCategory jPanelCategory;

    private JPanelViewCart jPanelViewCart;

    private JPanelConfirmation jPanelConfirmation;

    private javax.swing.JTable jTableCategory;

    private javax.swing.JTable jTableConfirmation;

    private javax.swing.JPanel menuPanel;

    private ShoppingCart cart;

    private int orderId;

    private Map orderMap;

    private ArrayList<Product> listProducts;

    private ProductsTableModel model;

    private JFrameSupermarket jFrameSupermarket;
    private javax.swing.JTable jTableViewCart;

    public void setjPanelViewCart(JPanelViewCart jPanelViewCart) {
        this.jPanelViewCart = jPanelViewCart;
    }

    public void setjPanelCategory(JPanelCategory jPanelCategory) {
        this.jPanelCategory = jPanelCategory;
    }

    public void setjPanelConfirmation(JPanelConfirmation jPanelConfirmation) {
        this.jPanelConfirmation = jPanelConfirmation;
    }

    /*public void setjTableCategory(JTable jTableCategory) {
        this.jTableCategory = jTableCategory;
    }*/
    public void setJFrameLogin(JFrame jFrameLogin) {
        this.jFrameLogin = jFrameLogin;
    }

    public void setMenuPanel(JPanel menuPanel) {
        this.menuPanel = menuPanel;
    }

    public Controller() {
        callRemoteEJBs();
    }

    public void jMenuItemHomePageActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout card = (CardLayout) menuPanel.getLayout();
        card.show(menuPanel, "home");
    }

    public void jMenuItemCategoryActionPerformed(java.awt.event.ActionEvent evt, String category) {
        jPanelCategory.setjLabelCategoryTitle(category);
        jTableCategory = jPanelCategory.getjTableCategory();
        populateCategoryJTable(category);
        jPanelCategory.setjTableCategory(jTableCategory);
        CardLayout card = (CardLayout) menuPanel.getLayout();
        card.show(menuPanel, "category");
    }

    public void jMenuItemViewCartActionPerformed(java.awt.event.ActionEvent evt) {
        /*cart=jPanelCategory.getCart();
        jPanelViewCart.setCart(cart);
        jPanelViewCart.populateViewCartJTable();*/
        jTableViewCart = jPanelViewCart.getjTableViewCart();
        populateViewCartJTable();
        jPanelViewCart.setjTableViewCart(jTableViewCart);
        CardLayout card = (CardLayout) menuPanel.getLayout();
        card.show(menuPanel, "viewCart");
    }

    public void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt, String userName, String password) {

        if (customerManagement.checkValidCustomer(userName, password) == true) {
            jFrameLogin.dispose();
            jFrameSupermarket = new JFrameSupermarket(this);
            jFrameSupermarket.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(jFrameLogin, "User name or password incorrect!");
        }
    }

    public void jButtonForgotPasswordActionPerformed(java.awt.event.ActionEvent evt) {
        jFrameResetPassword = new JFrameResetPassword();
        jFrameResetPassword.setController(this);
        jFrameResetPassword.setVisible(true);
    }

    public void jButtonSendPasswordActionPerformed(java.awt.event.ActionEvent evt, String email) {
        if (customerManagement.getCustomerByEmail(email) == null) {
            JOptionPane.showMessageDialog(jFrameResetPassword, "Email does not correspond to any user!");
        } else {
            customerManagement.sendPasswordToEmail(email);
            jFrameResetPassword.dispose();
            JOptionPane.showMessageDialog(jFrameLogin, "User/password sent to your email!");
        }
    }

    public void jButtonAddProductActionPerformed(java.awt.event.ActionEvent evt, String quantityText) {
        if (jTableCategory.getSelectedRow() != -1) {
            if (quantityText.equals("") || Integer.parseInt(quantityText) < 1) {
                JOptionPane.showMessageDialog(jPanelCategory, "Enter a valid quantity!");
            } else {
                jTableCategory = jPanelCategory.getjTableCategory();
                int selectedRow = jTableCategory.getSelectedRow();
                String s = "Add: " + listProducts.get(selectedRow).getName() + " x" + quantityText;
                int option = JOptionPane.showConfirmDialog(jPanelCategory, s);
                if (option == 0) {//only if user presses ok in JOptionPane
                    addToCart(listProducts.get(selectedRow), Integer.parseInt(quantityText));
                }
            }
        } else {
            JOptionPane.showMessageDialog(jPanelCategory, "You must select a product!");
        }
    }

    public void jButtonDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTableViewCart.getSelectedRow();
        //model.removeRow(selectedRow);
        cart.update(cart.getItems().get(selectedRow).getProduct(), (short) 0);//delete item from cart
        populateViewCartJTable();
    }

    public void jButtonCheckoutActionPerformed(java.awt.event.ActionEvent evt) {
        if (cart!=null){
            CardLayout card = (CardLayout) menuPanel.getLayout();
            card.show(menuPanel, "paying");
        }else{
            JOptionPane.showMessageDialog(jPanelViewCart, "Your cart is empty!");
        }
    }

    public void jButtonCategoryActionPerformed(java.awt.event.ActionEvent evt, String category) {
        jTableCategory = jPanelCategory.getjTableCategory();
        populateCategoryJTable(category);
        jPanelCategory.setjTableCategory(jTableCategory);
        CardLayout card = (CardLayout) menuPanel.getLayout();
        card.show(menuPanel, "category");
    }

    public void jButtonPayActionPerformed(java.awt.event.ActionEvent evt, String cCNUM, String cCEXP) {
        payCart(cCNUM, cCEXP);
        cart = null;//delete contents in cart due to already purchase of the cart
        jTableViewCart = jPanelViewCart.getjTableViewCart();
        populateViewCartJTable();
        jPanelViewCart.setjTableViewCart(jTableViewCart);
        orderMap = orderManagement.getOrderDetailsFromDB(orderId);
        // place order details 
        Customer user = (Customer) orderMap.get("customer");
        double totalOrderPrice = (double) orderMap.get("orderTotalPrice");
        
        String priceConfirmation = "Thank you for your purchase " + user.getFullName() + ", " + String.format("%.2f", (double)totalOrderPrice) + "$ were taken from your credit card";
        jPanelConfirmation.setjLabelPriceConfirmation(priceConfirmation);
        String adressConfirmation = "The above products will be sent to the following adress: " + user.getAdress() + ", " + user.getPostCode() + ", " + user.getCountry();
        jPanelConfirmation.setjLabelAdressConfirmation(adressConfirmation);
        jTableConfirmation = jPanelConfirmation.getjTableConfirmation();
        populateConfirmationJTable();
        jPanelConfirmation.setjTableConfirmation(jTableConfirmation);
        CardLayout card = (CardLayout) menuPanel.getLayout();
        card.show(menuPanel, "confirmation");

    }

    public void addToCart(Product p, int quantity) {
        if (cart == null) {
            cart = new ShoppingCart();
        }
        cart.addItem(p, (short) quantity);
    }

    public void populateCategoryJTable(String categoryName) {
        try {

            Category category = (Category) categoryManagement.findCategoryByName(categoryName);
            int i = 0;
            listProducts = categoryManagement.getProductCollectionGivenCategory(category.getName());
            String[] columnName = {"", "Product name", "Product description", "price"};
            Object[][] rows = new Object[listProducts.size()][4];
            for (Product p : listProducts) {
                if (p.getImage() != null) {
                    ImageIcon img = new ImageIcon(new ImageIcon(p.getImage()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
                    rows[i][0] = img;
                } else {
                    rows[i][0] = null;
                }
                rows[i][1] = p.getName();
                rows[i][2] = p.getDescription();
                rows[i][3] = p.getPrice();
                i++;
            }
            model = new ProductsTableModel(rows, columnName);
            jTableCategory.setModel(model);
            jTableCategory.setRowHeight(120);
            jTableCategory.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTableCategory.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableCategory.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTableCategory.getColumnModel().getColumn(3).setPreferredWidth(50);
        } catch (Exception e) {
        };
    }

    public void populateConfirmationJTable() {
        try {

            ArrayList<ProducthascustomerCart> orderedProducts = (ArrayList<ProducthascustomerCart>) orderMap.get("orderedProducts");
            int i = 0;
            ArrayList<Product> products = new ArrayList<Product>();
            products = (ArrayList<Product>) orderMap.get("products");
            String[] columnName = {"", "Product name", "Price", "Quantity"};
            Object[][] rows = new Object[orderedProducts.size()][4];
            for (ProducthascustomerCart op : orderedProducts) {
                System.out.println("entra for i= " + i);
                if (products.get(i).getImage() != null) {
                    ImageIcon img = new ImageIcon(new ImageIcon(products.get(i).getImage()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
                    rows[i][0] = img;
                    System.out.println("tiene imagen");
                } else {
                    rows[i][0] = null;
                    System.out.println("no tiene imagen");
                }
                rows[i][1] = null;
                rows[i][1] = products.get(i).getName();
                rows[i][2] = products.get(i).getPrice();
                rows[i][3] = op.getQuantity();
                i++;
            }
            model = new ProductsTableModel(rows, columnName);
            jTableConfirmation.setModel(model);
            jTableConfirmation.setRowHeight(120);
            jTableConfirmation.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTableConfirmation.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTableConfirmation.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTableConfirmation.getColumnModel().getColumn(3).setPreferredWidth(75);
        } catch (Exception e) {
        };
    }

    public void populateViewCartJTable() {
        if (cart == null) {

            jTableViewCart.setModel(new DefaultTableModel());
        } else {
            try {

                List<ShoppingCartItem> listItems = cart.getItems();
                String[] columnName = {"", "Product name", "Product description", "price", "Quantity"};
                Object[][] rows = new Object[listItems.size()][5];
                int i = 0;
                for (ShoppingCartItem item : listItems) {

                    if (item.getProduct().getImage() != null) {
                        ImageIcon img = new ImageIcon(new ImageIcon(item.getProduct().getImage()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
                        rows[i][0] = img;
                    } else {
                        rows[i][0] = null;
                    }
                    rows[i][1] = item.getProduct().getName();
                    rows[i][2] = item.getProduct().getDescription();
                    rows[i][3] = item.getProduct().getPrice();
                    rows[i][4] = item.getQuantity();
                    i++;
                }

                model = new ProductsTableModel(rows, columnName);
                jTableViewCart.setModel(model);
                jTableViewCart.setRowHeight(120);
                jTableViewCart.getColumnModel().getColumn(0).setPreferredWidth(150);
                jTableViewCart.getColumnModel().getColumn(1).setPreferredWidth(100);
                jTableViewCart.getColumnModel().getColumn(2).setPreferredWidth(200);
                jTableViewCart.getColumnModel().getColumn(3).setPreferredWidth(50);
                jTableViewCart.getColumnModel().getColumn(4).setPreferredWidth(100);
            } catch (Exception e) {
            };

        }
    }

    public void payCart(String cCNUM, String cCEXP) {
        try {
            Customer user = customerManagement.findUser("pquino01");
            user.setCcNum(cCNUM);
            user.setCcExp(cCEXP);
            customerManagement.editCustomer(user);
            if (cart != null) {
                orderId = orderManagement.placeOrder(user, cart);
                // if order processed successfully send user to confirmation page
                if (orderId != 0) {

                    // dissociate shopping cart from session
                    cart = null;

                    // get order details
                    Map orderMap = orderManagement.getOrderDetailsFromDB(orderId);

                    // place order details 
                    user = (Customer) orderMap.get("customer");
                    double totalOrderPrice = (double) orderMap.get("orderTotalPrice");
                    ArrayList<Product> products = new ArrayList<Product>();
                    products = (ArrayList<Product>) orderMap.get("products");

                    List<ProducthascustomerCart> orderedProducts = (List<ProducthascustomerCart>) orderMap.get("orderedProducts");

                    // otherwise, send back to checkout page and display error
                } else {
                    //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Order unsuccesful"));

                }
            }
        } catch (Exception e) {
        }
    }

    public void callRemoteEJBs() {
        try {
            InitialContext context = new InitialContext();
            customerManagement = (CustomerManagementRemote) context.lookup("java:global/On-lineSupermarketEJB/CustomerManagement");
            categoryManagement = (CategoryManagementRemote) context.lookup("java:global/On-lineSupermarketEJB/CategoryManagement");
            orderManagement = (OrderManagementRemote) context.lookup("java:global/On-lineSupermarketEJB/OrderManagement");
        } catch (Exception e) {
        };

    }
}
