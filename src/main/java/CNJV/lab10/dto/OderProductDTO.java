package CNJV.lab10.dto;

import CNJV.lab10.model.Product;
import lombok.Getter;
import lombok.Setter;

public class OderProductDTO {
    private Product product;
    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }
}
