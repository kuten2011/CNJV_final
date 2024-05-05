package CNJV.lab10.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Client client;
    private String name;
    private int status; //0 ban 1 thue
    private Double acreage;
    private String arena;
    private String address;
    private Double price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    public Client getClient() {
        return client;
    }

    public Long getId() {
        return this.id;
    }

    @Builder
    public Product(String name, int status, Double acreage, String arena, String address, Double price, LocalDate dateCreated, Client client) {
        this.name = name;
        this.status = status;
        this.acreage = acreage;
        this.arena = arena;
        this.address = address;
        this.price = price;
        this.dateCreated = dateCreated;
        this.client = client;
    }
}
