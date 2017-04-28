package view;
import controller.Controller;
/**
 *
 * @author pablo
 */
public class JFrameLogin extends javax.swing.JFrame {
                        
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JButton jButtonForgotPassword;
    private javax.swing.JLabel jLabelLoginTitle;
    private javax.swing.JLabel jLabelUserName;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.  JPasswordField jTextFieldPassword;
    private javax.swing.JTextField jTextFieldUserName;
    private Controller controller;
    
    /**
     * Creates new form LoginJFrame
     */
    public JFrameLogin(Controller controller) {
        initComponents();
        this.controller=controller;
        controller.setJFrameLogin(this);
    }

                          
    private void initComponents() {

        jLabelLoginTitle = new javax.swing.JLabel();
        jLabelUserName = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldUserName = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JPasswordField();
        jButtonLogin = new javax.swing.JButton();
        jButtonForgotPassword = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelLoginTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelLoginTitle.setText("On-line Supermarket Login");

        jLabelUserName.setText("User Name");

        jLabelPassword.setText("Password");

        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonLoginActionPerformed(evt,jTextFieldUserName.getText(),String.valueOf(jTextFieldPassword.getPassword()));
            }
        });

        jButtonForgotPassword.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonForgotPassword.setText("I forgot my password");
        jButtonForgotPassword.setBorder(null);
        jButtonForgotPassword.setBorderPainted(false);
        jButtonForgotPassword.setContentAreaFilled(false);
        jButtonForgotPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                controller.jButtonForgotPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabelLoginTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelPassword)
                                    .addComponent(jLabelUserName))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldUserName)
                                    .addComponent(jTextFieldPassword)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(jButtonForgotPassword)
                                .addGap(24, 24, 24)))))
                .addGap(136, 136, 136))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLoginTitle)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUserName)
                    .addComponent(jTextFieldUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLogin)
                    .addComponent(jButtonForgotPassword))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pack();
    }                                                             

                    
}
