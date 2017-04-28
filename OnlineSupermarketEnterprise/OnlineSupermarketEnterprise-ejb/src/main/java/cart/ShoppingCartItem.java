

package cart;

import java.io.Serializable;
import persistence.Product;



/**
 *
 * @author tgiunipero
 */
public class ShoppingCartItem implements Serializable {

    Product product;
    short quantity;

    public ShoppingCartItem(Product product) {
        this.product = product;
        quantity = 1;
    }

    public Product getProduct() {
        return product;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity(short q) {
        quantity+=q;
    }

    public void decrementQuantity(short q) {
        quantity-=q;
    }

    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * product.getPrice());
        amount= Math.floor(amount * 100) / 100;
        return amount;
    }

}