/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import persistence.Customer;
import javax.ejb.Remote;

/**
 *
 * @author pablo
 */
@Remote
public interface CustomerManagementRemote {
    
    Boolean checkValidCustomer(String userName, String password);
    
    void sendPasswordToEmail(String email) ;
    
    Customer getCustomerByEmail(String email);
    
    Customer findUser (String userName);
    
     Boolean checkIfUserExists(String userName);
     
     void editCustomer(Customer c);
     
     void registerNewUser(String fullName, String userName, String email, String password, String adress, String postCode, String country, String ccNum, String ccExp);
     
     
    
}
