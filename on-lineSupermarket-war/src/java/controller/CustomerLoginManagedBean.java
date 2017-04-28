/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.CategoryManagementRemote;
import ejb.CustomerManagementRemote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.InitialContext;

/**
 *
 * @author pablo
 */
@Named(value = "customerLoginManagedBean")
@SessionScoped
public class CustomerLoginManagedBean implements Serializable {

    //@EJB
    private CustomerManagementRemote customerManagement;

    
    /**
    * stores injection to the 
    */
    @Inject
    ViewCartManagedBean viewCart;
    
   
    
    private Boolean loggedIn;
    /**
    * Property to store the traveller's user name
    */
    private String userName;
    
    /**
    * Property to store the traveller's password
    */
    private String password;
    
    /**
    * Property to store the traveller's password
    */
    private String emailForgotPassword;
   
    /**
     * Creates a new instance of travellerLoginManagedBean
     */
    public CustomerLoginManagedBean() {
        try {
            InitialContext c = new InitialContext();
            customerManagement = (CustomerManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/CustomerManagement");
        } catch (Exception e) {};
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getEmailForgotPassword() {
        return emailForgotPassword;
    }

    public void setEmailForgotPassword(String emailForgotPassword) {
        this.emailForgotPassword = emailForgotPassword;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
    * checks if a user exists in the database (and checks the user credentials)
    * @return boolean indicating if it is a valid user or no
    */
    public Boolean validUser(){
        return customerManagement.checkValidCustomer(this.userName, this.password)==true;
    }
    
    /**
    * checks if the client browser given name and password correspond to a valid existing user
    * @return string containing the html redirect in case it is a valid user (otherwise null)
    */
    public String login() {
        
        if (validUser()==true){
            loggedIn=true;
            /*selectCategory.setLoggedIn(Boolean.TRUE);
            viewCart.setLoggedIn(Boolean.TRUE);*/
            return "storeHome.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("user or password incorrect!"));
            return "storeLogin.xhtml";
        }
        
    }
    
    public String forgotPassword(){
        if (customerManagement.getCustomerByEmail(emailForgotPassword)==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("email not registered"));
            return "forgotPassword.xhtml";
        }else{
            customerManagement.sendPasswordToEmail(emailForgotPassword);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("login details sent to your email"));
            return "storeLogin.xhtml";
        }
    }
    
    /**
    * invalidates the session for the user
    * @return string containing the html redirect url 
    */
    public String logout() {
     
        viewCart.setCart(null);
        loggedIn=false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "storeHome.xhtml";
    }
    
}

