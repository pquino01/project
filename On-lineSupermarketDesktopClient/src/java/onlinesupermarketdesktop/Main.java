/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinesupermarketdesktop;

import controller.Controller;
import view.JFrameLogin;


/**
 *
 * @author pablo
 */
public class Main {
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
      
       Controller controller= new Controller();
       JFrameLogin jFrameLogin = new JFrameLogin(controller);
       jFrameLogin.setVisible(true);
      
       
    }

}
