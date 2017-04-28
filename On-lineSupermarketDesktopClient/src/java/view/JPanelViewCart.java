package view;

import cart.ShoppingCart;
import controller.Controller;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author pablo
 */
public class JPanelViewCart extends javax.swing.JPanel {
                  
    private javax.swing.JButton jButtonDeleteProduct;
    private javax.swing.JButton jButtonCheckout;
    private javax.swing.JLabel jLabelViewCartTitle;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTableViewCart;
    private Controller controller;   
    private ShoppingCart cart;

   // private ProductsTableModel model;
    
    private javax.swing.JPanel MenuPanel;

    public JTable getjTableViewCart() {
        return jTableViewCart;
    }

    public void setjTableViewCart(JTable jTableViewCart) {
        this.jTableViewCart = jTableViewCart;
    }

    
    public JPanel getMenuPanel() {
        return MenuPanel;
    }

    public void setMenuPanel(JPanel MenuPanel) {
        this.MenuPanel = MenuPanel;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    /**
     * Creates new form ViewCartJPanel
     */
    public JPanelViewCart(Controller controller) {
        this.controller=controller;
        initComponents();
        //populateViewCartJTable();
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

                            
    private void initComponents() {

        jLabelViewCartTitle = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jTableViewCart = new javax.swing.JTable();
        jButtonDeleteProduct = new javax.swing.JButton();
        jButtonCheckout = new javax.swing.JButton();

        jLabelViewCartTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelViewCartTitle.setText("Your shopping cart");

        jTableViewCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane.setViewportView(jTableViewCart);

        jButtonDeleteProduct.setText("Delete selected product");
        jButtonDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonDeleteProductActionPerformed(evt);
            }
        });

        jButtonCheckout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonCheckout.setText("Checkout  & Pay");
        jButtonCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonCheckoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelViewCartTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDeleteProduct)))
                .addContainerGap(142, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCheckout)
                .addGap(158, 158, 158))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelViewCartTitle)
                        .addGap(0, 0, 0))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonDeleteProduct)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCheckout)
                .addGap(45, 45, 45))
        );
    }                   
                                      
            
}

