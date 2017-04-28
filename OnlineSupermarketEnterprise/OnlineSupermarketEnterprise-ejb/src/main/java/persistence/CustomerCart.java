/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "customerCart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerCart.findAll", query = "SELECT c FROM CustomerCart c"),
    @NamedQuery(name = "CustomerCart.findById", query = "SELECT c FROM CustomerCart c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerCart.findByAmount", query = "SELECT c FROM CustomerCart c WHERE c.amount = :amount")})
public class CustomerCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerCart")
    private Collection<ProducthascustomerCart> producthascustomerCartCollection;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    public CustomerCart() {
    }

    public CustomerCart(Integer id) {
        this.id = id;
    }

    public CustomerCart(Integer id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @XmlTransient
    public Collection<ProducthascustomerCart> getProducthascustomerCartCollection() {
        return producthascustomerCartCollection;
    }

    public void setProducthascustomerCartCollection(Collection<ProducthascustomerCart> producthascustomerCartCollection) {
        this.producthascustomerCartCollection = producthascustomerCartCollection;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerCart)) {
            return false;
        }
        CustomerCart other = (CustomerCart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.CustomerCart[ id=" + id + " ]";
    }
    
}
