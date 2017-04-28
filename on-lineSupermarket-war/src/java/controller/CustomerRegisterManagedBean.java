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
import javax.naming.InitialContext;

/**
 *
 * @author pablo
 */
@Named(value = "customerRegisterManagedBean")
@SessionScoped
public class CustomerRegisterManagedBean implements Serializable {

    //@EJB
    private CustomerManagementRemote customerManagement;

    
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
    private String fullName;
    
    /**
    * Property to store the traveller's password
    */
    private String email;
    
    /**
    * Property to store the traveller's password
    */
    private String adress;
    
    /**
    * Property to store the traveller's password
    */
    private String postCode;
    
    /**
    * Property to store the traveller's password
    */
    private String country;
    
    /**
    * Property to store the traveller's password
    */
    private String ccNum;
    
    /**
    * Property to store the traveller's password
    */
    private String ccExp;
   
    /**
     * Creates a new instance of travellerLoginManagedBean
     */
    public CustomerRegisterManagedBean() {
        try {
            InitialContext c = new InitialContext();
            customerManagement = (CustomerManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/CustomerManagement");
        } catch (Exception e) {};
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    

    public String getCcNum() {
        return ccNum;
    }

    public void setCcNum(String ccNum) {
        this.ccNum = ccNum;
    }

    public String getCcExp() {
        return ccExp;
    }

    public void setCcExp(String ccExp) {
        this.ccExp = ccExp;
    }
    
    
    /**
    * checks if the client browser given name and password correspond to a valid existing user
    * @return string containing the html redirect in case it is a valid user (otherwise null)
    */
    public String registerCustomer() {
        
        if (customerManagement.checkIfUserExists(userName)==false){
            customerManagement.registerNewUser(fullName,userName,email,password,adress,postCode,country,ccNum,ccExp);
            return "/storeLogin.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("This user name already exists, please choose a different one"));
            return "/storeRegister.xhtml";
        }
        
    }
    
    
    
}


