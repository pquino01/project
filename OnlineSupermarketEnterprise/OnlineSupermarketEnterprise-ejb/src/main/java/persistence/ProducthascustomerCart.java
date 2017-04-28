/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pablo
 */
@Entity
@Table(name = "product_has_customerCart")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProducthascustomerCart.findAll", query = "SELECT p FROM ProducthascustomerCart p"),
    @NamedQuery(name = "ProducthascustomerCart.findByProductId", query = "SELECT p FROM ProducthascustomerCart p WHERE p.producthascustomerCartPK.productId = :productId"),
    @NamedQuery(name = "ProducthascustomerCart.findByCustomerCartid", query = "SELECT p FROM ProducthascustomerCart p WHERE p.producthascustomerCartPK.customerCartid = :customerCartid"),
    @NamedQuery(name = "ProducthascustomerCart.findByQuantity", query = "SELECT p FROM ProducthascustomerCart p WHERE p.quantity = :quantity")})
public class ProducthascustomerCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProducthascustomerCartPK producthascustomerCartPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private short quantity;
    @JoinColumn(name = "customerCart_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomerCart customerCart;
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public ProducthascustomerCart() {
    }

    public ProducthascustomerCart(ProducthascustomerCartPK producthascustomerCartPK) {
        this.producthascustomerCartPK = producthascustomerCartPK;
    }

    public ProducthascustomerCart(ProducthascustomerCartPK producthascustomerCartPK, short quantity) {
        this.producthascustomerCartPK = producthascustomerCartPK;
        this.quantity = quantity;
    }

    public ProducthascustomerCart(int productId, int customerCartid) {
        this.producthascustomerCartPK = new ProducthascustomerCartPK(productId, customerCartid);
    }

    public ProducthascustomerCartPK getProducthascustomerCartPK() {
        return producthascustomerCartPK;
    }

    public void setProducthascustomerCartPK(ProducthascustomerCartPK producthascustomerCartPK) {
        this.producthascustomerCartPK = producthascustomerCartPK;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public CustomerCart getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(CustomerCart customerCart) {
        this.customerCart = customerCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (producthascustomerCartPK != null ? producthascustomerCartPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProducthascustomerCart)) {
            return false;
        }
        ProducthascustomerCart other = (ProducthascustomerCart) object;
        if ((this.producthascustomerCartPK == null && other.producthascustomerCartPK != null) || (this.producthascustomerCartPK != null && !this.producthascustomerCartPK.equals(other.producthascustomerCartPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.ProducthascustomerCart[ producthascustomerCartPK=" + producthascustomerCartPK + " ]";
    }
    
}
