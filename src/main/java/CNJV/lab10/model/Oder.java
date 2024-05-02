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

    private OderStatus status; // 0 chưa, 1 đang, 2 đã

    private int pay; //0 chuyen khoan, 1 tien mat

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @JsonIgnore
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    private Staff staff;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Transient
    public Double getMoney() {
        return 0.05 * product.getPrice();
    }

    public Client getClient() {
        return client;
    }

    public Staff getStaff() {
        return staff;
    }

    public Product getProduct() {
        return product;
    }

    @Builder
    public Oder(LocalDate dateCreate, OderStatus status, int pay, Client client, Staff staff, Product product) {
        this.pay = pay;
        this.staff = staff;
        this.client = client;
        this.product = product;
        this.dateCreate = dateCreate;
        this.status = status;
    }
}
