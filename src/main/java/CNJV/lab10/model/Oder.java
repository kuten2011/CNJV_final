package CNJV.lab10.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "oder")
@Getter @Setter
@NoArgsConstructor

public class Oder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate dateCreate;

    private OderStatus status;

    @JsonManagedReference
    @OneToMany (mappedBy = "pk.oder", cascade = {CascadeType.REMOVE})
    @Valid
    @JsonIgnore

    private List<OderProduct> oderProducts = new ArrayList<OderProduct>();

    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<OderProduct> oderProducts = getOderProducts();
        for (OderProduct op : oderProducts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.oderProducts.size();
    }

    @Builder
    public Oder(LocalDate dateCreate, OderStatus status) {
        this.dateCreate = dateCreate;
        this.status = status;
    }
}
