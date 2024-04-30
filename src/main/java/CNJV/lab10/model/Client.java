package CNJV.lab10.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @JsonIgnore
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "users_username", referencedColumnName = "username")
    private User user;

    public User getUser() {
        return user;
    }

    @Builder
    public Client(String name, String phone, String email, User user) {
        this.name =  name;
        this.phone = phone;
        this.email = email;
        this.user =user;
    }
}
