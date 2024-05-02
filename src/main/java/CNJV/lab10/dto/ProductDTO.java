package CNJV.lab10.dto;

import CNJV.lab10.model.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
public class ProductDTO {
    private Long id;
    private String name;
    private int status; //0 ban 1 thue
    private Double acreage;
    private String arena;
    private String address;
    private Double price;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @Builder
    public ProductDTO(String name, int status, Double acreage, String arena, String address, Double price, LocalDate dateCreated) {
        this.name = name;
        this.status = status;
        this.acreage = acreage;
        this.arena = arena;
        this.address = address;
        this.price = price;
        this.dateCreated = dateCreated;
    }
}
