package view;

import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author pablo
 */
public class JPanelConfirmation extends javax.swing.JPanel {
                    
    private javax.swing.JLabel jLabelAdressConfirmation;
    private javax.swing.JLabel jLabelConfirmationTitle;
    private javax.swing.JLabel jLabelPriceConfirmation;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTableConfirmation;   
    /**
     * Creates new form ConfirmationJPanel
     */
    public JPanelConfirmation() {
        initComponents();
    }

    public JTable getjTableConfirmation() {
        return jTableConfirmation;
    }

    public void setjTableConfirmation(JTable jTableConfirmation) {
        this.jTableConfirmation = jTableConfirmation;
    }

    public void setjLabelAdressConfirmation(String adressConfirmation) {
        jLabelAdressConfirmation.setText(adressConfirmation);
    }

    public void setjLabelPriceConfirmation(String priceConfirmation) {
        jLabelPriceConfirmation.setText(priceConfirmation);
    }
                     
    private void initComponents() {

        jLabelConfirmationTitle = new javax.swing.JLabel();
        jLabelPriceConfirmation = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        jTableConfirmation = new javax.swing.JTable();
        jLabelAdressConfirmation = new javax.swing.JLabel();

        jLabelConfirmationTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelConfirmationTitle.setText("Purchase confirmation");

        jTableConfirmation.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane.setViewportView(jTableConfirmation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane)
                    .addComponent(jLabelConfirmationTitle)
                    .addComponent(jLabelAdressConfirmation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelPriceConfirmation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConfirmationTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelPriceConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelAdressConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
    }                        
              
}