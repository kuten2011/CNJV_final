package CNJV.lab10.model;

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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="client_id")
    private Client client;

    @Basic (optional = false)
    private String name;
    private int status; //1 mua 2 ban
    private Double acreage;
    private String arena;
    private String address;
    private Double price;

    private LocalDate dateCreated;

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
