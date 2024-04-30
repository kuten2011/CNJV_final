package CNJV.lab10.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private int pay; //0 chuyen khoan, 1 tien mat

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "oder_id", referencedColumnName = "id")
    private Oder oder;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Transient
    public Double getMoney() {
        return 0.1 * product.getPrice();
    }

    @Builder
    public Oder(LocalDate dateCreate, OderStatus status) {
        this.dateCreate = dateCreate;
        this.status = status;
    }
}
