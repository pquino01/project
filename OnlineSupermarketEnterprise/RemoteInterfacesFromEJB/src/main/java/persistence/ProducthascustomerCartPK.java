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
import javax.validation.constraints.NotNull;

/**
 *
 * @author pablo
 */
@Embeddable
public class ProducthascustomerCartPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "product_id")
    private int productId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "customerCart_id")
    private int customerCartid;

    public ProducthascustomerCartPK() {
    }

    public ProducthascustomerCartPK(int productId, int customerCartid) {
        this.productId = productId;
        this.customerCartid = customerCartid;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerCartid() {
        return customerCartid;
    }

    public void setCustomerCartid(int customerCartid) {
        this.customerCartid = customerCartid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productId;
        hash += (int) customerCartid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProducthascustomerCartPK)) {
            return false;
        }
        ProducthascustomerCartPK other = (ProducthascustomerCartPK) object;
        if (this.productId != other.productId) {
            return false;
        }
        if (this.customerCartid != other.customerCartid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.ProducthascustomerCartPK[ productId=" + productId + ", customerCartid=" + customerCartid + " ]";
    }
    
}
