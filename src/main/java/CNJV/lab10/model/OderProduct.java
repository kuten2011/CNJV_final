package CNJV.lab10.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Entity
public class OderProduct implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private OderProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    public OderProduct() {}

    @Builder
    public OderProduct(Oder oder, Product product, Integer quantity) {
        pk = new OderProductPK();
        pk.setOder(oder);
        pk.setProduct(product);
        this.quantity = quantity;
    }

    @JsonIgnore
    public Oder getOder() {
        return this.pk.getOder();
    }

    @JsonIgnore
    public Product getProduct() {
        return this.pk.getProduct();
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Long getProductId() {
        return this.pk.getProduct().getId();
    }

    @Transient
    public Long getOderId() {
        return this.pk.getOder().getId();
    }

    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice()*getQuantity();
    }

    public OderProductPK getPk(){
        return this.pk;
    }

    @Override
    public final int hashCode() {
        return this.pk.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OderProduct)) {
            return false;
        }
        OderProduct other = (OderProduct) o;
        return this.pk == other.pk;
    }
}
