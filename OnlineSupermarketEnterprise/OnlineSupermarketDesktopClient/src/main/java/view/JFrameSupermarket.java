package view;

import cart.ShoppingCart;
import controller.Controller;
import java.awt.CardLayout;


/**
 *
 * @author pablo
 */
public class JFrameSupermarket extends javax.swing.JFrame {
                      
    private javax.swing.JPanel menuPanel;
    private view.JPanelConfirmation jPanelConfirmation;
    private view.JPanelCategory jPanelCategory;
    private view.JPanelHome jPanelHome;
    private javax.swing.JMenu jMenuHome;
    private javax.swing.JMenu jMenuCategory;
    private javax.swing.JMenu jMenuCart;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuItemDiary;
    private javax.swing.JMenuItem jMenuItemBakery;
    private javax.swing.JMenuItem jMenuItemFruitAndVeg;
    private javax.swing.JMenuItem jMenuItemMeatAndFish;
    private javax.swing.JMenuItem jMenuItemHomePage;
    private javax.swing.JMenuItem jMenuItemViewCart;
    private view.JPanelPaying jPanelPaying;
    private view.JPanelViewCart jPanelViewCart;
 
    private ShoppingCart cart;
    private Controller controller;
    /**
     * Creates new form SupermarketJFrame
     */
    public JFrameSupermarket(Controller controller) {   
        this.controller=controller;
        initComponents();
        controller.setMenuPanel(menuPanel);
        controller.setjPanelCategory(jPanelCategory);
        controller.setjPanelViewCart(jPanelViewCart);
        controller.setjPanelConfirmation(jPanelConfirmation);
        /*jPanelHome.setMenuPanel(menuPanel);
        jPanelViewCart.setMenuPanel(menuPanel);
        jPanelPaying.setMenuPanel(menuPanel);*/
    }
                         
    private void initComponents() {

        
        menuPanel = new javax.swing.JPanel();
        jPanelHome = new view.JPanelHome(controller);
        jPanelCategory = new view.JPanelCategory(controller);
        jPanelViewCart = new view.JPanelViewCart(controller);
        jPanelPaying = new view.JPanelPaying(controller);
        jPanelConfirmation = new view.JPanelConfirmation();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuHome = new javax.swing.JMenu();
        jMenuItemHomePage = new javax.swing.JMenuItem();
        jMenuItemMeatAndFish = new javax.swing.JMenuItem();
        jMenuItemBakery = new javax.swing.JMenuItem();
        jMenuItemFruitAndVeg = new javax.swing.JMenuItem();
        jMenuCategory = new javax.swing.JMenu();
        jMenuItemDiary = new javax.swing.JMenuItem();
        jMenuCart = new javax.swing.JMenu();
        jMenuItemViewCart = new javax.swing.JMenuItem();
   

        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPanel.setLayout(new java.awt.CardLayout());
        menuPanel.add(jPanelHome, "home");
        menuPanel.add(jPanelCategory, "category");
        menuPanel.add(jPanelViewCart, "viewCart");
        menuPanel.add(jPanelPaying, "paying");
        menuPanel.add(jPanelConfirmation, "confirmation");

        getContentPane().add(menuPanel, java.awt.BorderLayout.CENTER);

        jMenuHome.setText("Home");
        /*jMenuHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });*/

        jMenuItemHomePage.setLabel("home page");
        jMenuItemHomePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jMenuItemHomePageActionPerformed(evt);
            }
        });
        jMenuHome.add(jMenuItemHomePage);

        jMenuBar.add(jMenuHome);

        jMenuCategory.setText("Category");
        /*jMenuCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });*/

        jMenuItemDiary.setText("Diary");
        jMenuItemDiary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jMenuItemCategoryActionPerformed(evt,"Diary");
            }
        });
        jMenuCategory.add(jMenuItemDiary);
        jMenuItemMeatAndFish.setText("Meat&Fish");
        jMenuItemMeatAndFish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jMenuItemCategoryActionPerformed(evt,"Meat&Fish");
            }
        });
        jMenuCategory.add(jMenuItemMeatAndFish);

        jMenuItemBakery.setText("Bakery");
        jMenuItemBakery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jMenuItemCategoryActionPerformed(evt,"Bakery");
            }
        });
        jMenuCategory.add(jMenuItemBakery);

        jMenuItemFruitAndVeg.setText("Fruit&Veg");
        jMenuItemFruitAndVeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jMenuItemCategoryActionPerformed(evt,"Fruit&Veg");
            }
        });
        jMenuCategory.add(jMenuItemFruitAndVeg);
        
        jMenuBar.add(jMenuCategory);

        jMenuCart.setText("Cart");
        /*jMenuCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });*/

        jMenuItemViewCart.setText("View cart");
        jMenuItemViewCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jMenuItemViewCartActionPerformed(evt);
            }
        });
        jMenuCart.add(jMenuItemViewCart);

        jMenuBar.add(jMenuCart);

        

        setJMenuBar(jMenuBar);

        pack();
    }                        
/*
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        
    }                                      

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        
    }                                      

                                            

                                           

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {                                       
        
    }    */                                  

                                                  
                                                                                                                       
                 
}