package view;

import controller.Controller;

/**
 *
 * @author pablo
 */
public class JFrameResetPassword extends javax.swing.JFrame {
                  
    private javax.swing.JButton jButtonSendPassword;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelResetPasswordTitle;
    private javax.swing.JTextField jTextFieldEmail;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }
          
    /**
     * Creates new form JFrameForgotPassword
     */
    public JFrameResetPassword() {
        initComponents();
    }
                          
    private void initComponents() {

        jLabelResetPasswordTitle = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jButtonSendPassword = new javax.swing.JButton();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelResetPasswordTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelResetPasswordTitle.setText("Reset password");

        jLabelEmail.setText("Enter your email: ");

        jButtonSendPassword.setText("Send password to your email");
        jButtonSendPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonSendPasswordActionPerformed(evt,jTextFieldEmail.getText());
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSendPassword)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelResetPasswordTitle)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelEmail)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelResetPasswordTitle)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jButtonSendPassword)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
    }                  
    
}
