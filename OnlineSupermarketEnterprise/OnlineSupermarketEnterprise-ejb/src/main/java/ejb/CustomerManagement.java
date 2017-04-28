/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Customer;

/**
 *
 * @author pablo
 */
@Stateless
public class CustomerManagement extends AbstractFacade<Customer> implements CustomerManagementRemote {

    @PersistenceContext(unitName = "On-lineSupermarketEJBPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerManagement() {
        super(Customer.class);
    }
    
    public void editCustomer(Customer c){
        edit(c);
    }

    @Override
    public Boolean checkValidCustomer(String userName, String password) {
        Query query = em.createQuery("SELECT COUNT(c.userName) FROM Customer c WHERE c.userName=:userName AND c.password=:password");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        long matchCounter = (Long) query.getSingleResult();
        return matchCounter > 0;
    }

    @Override
    public void sendPasswordToEmail(String email) {
        
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        final String from = "onlinesupermarketbirkbeck@gmail.com";

        // Get system properties
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.user", from);
        properties.setProperty("mail.smtp.auth", "true");
        

        Session session = Session.getInstance(properties,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "jrwouojrgezxnhkf");
			}
		  });
        try{
        
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Online Supermarket forgot password");

            // Now set the actual message
            message.setText("User name: " + getCustomerByEmail(email).getUserName() + " and password: " + getCustomerByEmail(email).getPassword());

            // Send message
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException e) { System.out.println("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

    }

    @Override
    public Customer getCustomerByEmail(String email) {
        try {
            Query query = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email");
            query.setParameter("email", email);
            Customer c = (Customer) query.getSingleResult();
            return c;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    /**
    * given a user's name, returns the entire traveller user entity object related to that user name
    * @param userName string containing the user's name
    * @return traveller user entity object related to the passed userName
    */
    @Override
    public Customer findUser (String userName){
        Query query =em.createQuery("SELECT c FROM Customer c WHERE c.userName=:userName");
        query.setParameter("userName", userName);
        Customer user= (Customer)query.getSingleResult();
        return user;
    }

    /**
     * given a user's name, check if that user name is already registered in the
     * database
     *
     * @param userName string containing the user's name
     * @return boolean (true if user already exists)
     */
    @Override
    public Boolean checkIfUserExists(String userName) {
        Query query = em.createQuery("SELECT COUNT(c.userName) FROM Customer c WHERE c.userName=:userName");
        query.setParameter("userName", userName);
        long matchCounter = (Long) query.getSingleResult();
        return matchCounter > 0;
    }

    public void registerNewUser(String fullName, String userName, String email, String password, String adress, String postCode, String country, String ccNum, String ccExp) {
        Customer newUser = new Customer();
        newUser.setFullName(fullName);
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setAdress(adress);
        newUser.setPostCode(postCode);
        newUser.setCountry(country);
        newUser.setCcNum(ccNum);
        newUser.setCcExp(ccExp);
        create(newUser);
    }
}
