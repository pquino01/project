package view;

import controller.Controller;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author pablo
 */
public class JPanelHome extends javax.swing.JPanel {
                    
    private javax.swing.JButton jButtonMeatAndFish;
    private javax.swing.JButton jButtonBakery;
    private javax.swing.JButton jButtonFruitAndVeg;
    private javax.swing.JButton jButtonDiary; 
    private javax.swing.JPanel MenuPanel;
    private Controller controller;

    public JPanel getMenuPanel() {
        return MenuPanel;
    }

    public void setMenuPanel(JPanel MenuPanel) {
        this.MenuPanel = MenuPanel;
    }
    
    
    /**
     * Creates new form HomeJPanel
     */
    public JPanelHome(Controller controller) {
        this.controller=controller;
        initComponents();
    }

                             
    private void initComponents() {

        jButtonDiary = new javax.swing.JButton();
        jButtonMeatAndFish = new javax.swing.JButton();
        jButtonBakery = new javax.swing.JButton();
        jButtonFruitAndVeg = new javax.swing.JButton();

        jButtonDiary.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonDiary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DiaryIcon.png"))); // NOI18N
        jButtonDiary.setText("Diary");
        jButtonDiary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonCategoryActionPerformed(evt,"Diary");
            }
        });

        jButtonMeatAndFish.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonMeatAndFish.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/meat&fish.png"))); // NOI18N
        jButtonMeatAndFish.setText("Meat&Fish");
        jButtonMeatAndFish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonCategoryActionPerformed(evt,"Meat&Fish");
            }
        });
        
        jButtonBakery.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonBakery.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bakeryIcon.png"))); // NOI18N
        jButtonBakery.setText("Bakery");
        jButtonBakery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonCategoryActionPerformed(evt,"Bakery");
            }
        });

        jButtonFruitAndVeg.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonFruitAndVeg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fruit&veg.png"))); // NOI18N
        jButtonFruitAndVeg.setText("Fruit&Veg");
        jButtonFruitAndVeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonCategoryActionPerformed(evt,"Fruit&Veg");
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDiary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonBakery, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonFruitAndVeg, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMeatAndFish, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonMeatAndFish, jButtonBakery, jButtonFruitAndVeg, jButtonDiary});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDiary, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(jButtonMeatAndFish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonFruitAndVeg, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBakery, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonMeatAndFish, jButtonBakery, jButtonFruitAndVeg, jButtonDiary});

    }               

                 
}

