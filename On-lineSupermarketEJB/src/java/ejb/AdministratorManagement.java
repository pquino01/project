/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.Administrator;

/**
 *
 * @author pablo
 */
@Stateless
public class AdministratorManagement extends AbstractFacade<Administrator> implements AdministratorManagementRemote {

    @PersistenceContext(unitName = "On-lineSupermarketEJBPU")
    private EntityManager em;

    public AdministratorManagement() {
        super(Administrator.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    public Boolean checkValidAdministrator (String userName, String password) {
        Query query = em.createQuery("SELECT COUNT(a.userName) FROM Administrator a WHERE a.userName=:userName AND a.password=:password");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        long matchCounter = (Long) query.getSingleResult();
        return matchCounter > 0;
    }
    
}
