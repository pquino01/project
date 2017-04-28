package view;


import cart.ShoppingCart;
import controller.Controller;
import ejb.CategoryManagementRemote;
import java.awt.Image;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import persistence.Category;
import persistence.Product;

/**
 *
 * @author pablo
 */
public class JPanelCategory extends javax.swing.JPanel {
                   
    private javax.swing.JButton jButtonAddProduct;
    private javax.swing.JLabel jLabelCategoryTitle;
    private javax.swing.JLabel jLabelQuantity;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTableCategory;
    private javax.swing.JTextField jTextFieldQuantity;
    private ArrayList<Product> listProducts;
    private Controller controller;
    
    
    //private ShoppingCart cart;

    /**
     * Creates new form DiaryJPanel
     */
    public JPanelCategory(Controller controller) {
        this.controller=controller;
        initComponents();
        //this.controller=controller;
        //controller.setjTableCategory(jTableCategory);
        //controller.populateCategoryJTable();
    }

    public JTable getjTableCategory() {
        return jTableCategory;
    }

    public void setjTableCategory(JTable jTableCategory) {
        this.jTableCategory = jTableCategory;
    }

    /*public ShoppingCart getCart() {
        return cart;
    }*/

    public void setjLabelCategoryTitle(String categoryName) {
        jLabelCategoryTitle.setText(categoryName);
    }

                             
    private void initComponents() {

        jLabelCategoryTitle = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jTableCategory = new javax.swing.JTable();
        jLabelQuantity = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jButtonAddProduct = new javax.swing.JButton();

        jLabelCategoryTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        //jLabelCategoryTitle.setText(categoryName);

        jTableCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {"", "", "", ""}
        ));
        jScrollPane.setViewportView(jTableCategory);

        jLabelQuantity.setText("Quantity:");

        jButtonAddProduct.setText("Add selected product");
        jButtonAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonAddProductActionPerformed(evt,jTextFieldQuantity.getText());
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabelQuantity)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCategoryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCategoryTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuantity)
                    .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddProduct))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }                   

                                                           

    
}