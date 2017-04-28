package view;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import controller.Controller;
import ejb.CategoryManagementRemote;
import ejb.CustomerManagementRemote;
import ejb.OrderManagementRemote;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JPanel;
import persistence.Customer;
import persistence.Product;
import persistence.ProducthascustomerCart;

/**
 *
 * @author pablo
 */
public class JPanelPaying extends javax.swing.JPanel {
                    
    private javax.swing.JButton jButtonPay;
    private javax.swing.JLabel jLabelCheckoutTitle;
    private javax.swing.JLabel jLabelCCNUM;
    private javax.swing.JLabel jLabelCCEXP;
    private javax.swing.JTextField jTextFieldCCEXP;
    private javax.swing.JTextField jTextFieldCCNUM;
         
    private CustomerManagementRemote customerManagement;
    private Controller controller;
    private ShoppingCart cart;
    
    private double totalOrderPrice;
    
    private ArrayList<Product> products;
    
    private List<ProducthascustomerCart> orderedProducts;
    
    private javax.swing.JPanel MenuPanel;

    public JPanel getMenuPanel() {
        return MenuPanel;
    }

    public void setMenuPanel(JPanel MenuPanel) {
        this.MenuPanel = MenuPanel;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }
    
    
    /**
     * Creates new form PayingJPanel
     */
    public JPanelPaying(Controller controller) {
        this.controller=controller;
        initComponents();
        populateTextFields();
    }

    public void populateTextFields() {

        try {
            InitialContext c = new InitialContext();
            customerManagement = (CustomerManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/CustomerManagement");
            Customer me = customerManagement.findUser("pquino01");

            if (me.getCcNum() != null && me.getCcExp() != null) {
                jTextFieldCCNUM.setText(me.getCcNum());
                jTextFieldCCEXP.setText(me.getCcExp());
            }else{
                jTextFieldCCNUM.setText("");
                jTextFieldCCEXP.setText("");
            }
        } catch (Exception e) {};
    }
    

                             
    private void initComponents() {

        jLabelCheckoutTitle = new javax.swing.JLabel();
        jLabelCCNUM = new javax.swing.JLabel();
        jTextFieldCCNUM = new javax.swing.JTextField();
        jLabelCCEXP = new javax.swing.JLabel();
        jTextFieldCCEXP = new javax.swing.JTextField();
        jButtonPay = new javax.swing.JButton();

        jLabelCheckoutTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelCheckoutTitle.setText("Checkout");

        jLabelCCNUM.setText("Credit Card Number: ");

        jLabelCCEXP.setText("Credit Card Expiration:");

        /*jTextFieldCCEXP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCCEXPActionPerformed(evt);
            }
        });*/

        jButtonPay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonPay.setText("Pay");
        jButtonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonPayActionPerformed(evt,jTextFieldCCNUM.getText(),jTextFieldCCEXP.getText());
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonPay)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCheckoutTitle)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelCCEXP)
                                .addComponent(jLabelCCNUM)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCCNUM)
                            .addComponent(jTextFieldCCEXP, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCheckoutTitle)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCCNUM)
                    .addComponent(jTextFieldCCNUM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCCEXP)
                    .addComponent(jTextFieldCCEXP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jButtonPay)
                .addContainerGap(108, Short.MAX_VALUE))
        );
    }              

    /*private void jTextFieldCCEXPActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    } */                                                                                    
             
}
