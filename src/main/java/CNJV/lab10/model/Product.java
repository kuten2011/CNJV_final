package CNJV.lab10.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic (optional = false)
    private String name;

    private Double price;

    private String brand;

    private String color;

    @Builder
    public Product(String name, Double price, String brand, String color) {
        this.name = name;
        this.price = price;
        this.brand =brand;
        this.color = color;
    }
}
