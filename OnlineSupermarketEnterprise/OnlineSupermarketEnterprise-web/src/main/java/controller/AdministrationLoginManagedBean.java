/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejb.AdministratorManagementRemote;
import ejb.CategoryManagementRemote;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author pablo
 */
@Named(value = "administrationLoginManagedBean")
@SessionScoped
public class AdministrationLoginManagedBean implements Serializable {

    //@EJB
    private AdministratorManagementRemote administratorManagement;

    @Inject
    AdministrationManagedBean adminHome;
    /**
    * Property to store the traveller's user name
    */
    private String userName;
    
    /**
    * Property to store the traveller's password
    */
    private String password;
    
    private Boolean loggedIn;

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
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
     * Creates a new instance of AdministrationLoginManagedBean
     */
    public AdministrationLoginManagedBean() {
        try {
            InitialContext c = new InitialContext();
            administratorManagement = (AdministratorManagementRemote) c.lookup("java:global/On-lineSupermarketEJB/AdministratorManagement");
        } catch (Exception e) {};
    }
    
    /**
    * checks if a user exists in the database (and checks the user credentials)
    * @return boolean indicating if it is a valid user or no
    */
    public Boolean validUser(){
        return administratorManagement.checkValidAdministrator(this.userName, this.password)==true;
    }
    
    /**
    * checks if the client browser given name and password correspond to a valid existing user
    * @return string containing the html redirect in case it is a valid user (otherwise null)
    */
    public String login() {
        
        if (validUser()==true){
            loggedIn=true;
            return "administrationStore.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("user or password incorrect!"));
            return "administrationLogin.xhtml";
        }
        
    }
    
    /**
    * invalidates the session for the user
    * @return string containing the html redirect url 
    */
    public String logout() {
        loggedIn=false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            adminHome.getContext().close();
        } catch (NamingException ex) {
            Logger.getLogger(AdministrationLoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "administrationLogin.xhtml";
    }
    
}
