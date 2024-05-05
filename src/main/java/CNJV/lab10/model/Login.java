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
    Long id;
    String username;
    String password;
    int role;

    public String getUsername() {
        return username;
    }

    public int getRole() {
        return this.role;
    }

    public Login(String username, String password, int role) {
        this.id = 1l;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
