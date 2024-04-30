package CNJV.lab10.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Staff {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String phone;
    private String email;

    @Column(nullable = false)
    private int position; //1 là admin, 2 là nv thuong

    @Builder
    public Staff(String name, String phone, String email, int position) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }
}