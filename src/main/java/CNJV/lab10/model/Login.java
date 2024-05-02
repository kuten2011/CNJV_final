package CNJV.lab10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Login {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    String username;

    public String getUsername() {
        return username;
    }

    public Login(String username) {
        this.username = username;
    }
}
