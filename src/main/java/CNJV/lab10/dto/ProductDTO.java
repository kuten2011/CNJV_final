package CNJV.lab10.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String brand;
    private String color;

    @Builder
    public ProductDTO(Long id, String name, Double price, String brand, String color) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.color = color;
    }
}
