package CNJV.lab10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String email;

    @Builder
    public Client(String name, String phone, String email) {
        this.name =  name;
        this.phone = phone;
        this.email = email;
    }
}
