package CNJV.lab10.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class OderProductPK implements Serializable {
    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "oder_id")
    private Oder oder;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Oder getOder() {
        return this.oder;
    }

    public void setOder(Oder oder) {
        this.oder = oder;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public final int hashcode() {
        return (int) (oder.getId() * product.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OderProductPK))
            return false;
        OderProductPK other = (OderProductPK) o;

        return this.oder.getId() == other.oder.getId() && this.product.getId() == other.product.getId();
    }
}
