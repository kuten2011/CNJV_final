package CNJV.lab10.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserDto {
    private String username;

    private String password;
    private String name;
    private String phone;
    private String email;
    private int position;

    public UserDto(String username, String password, String name, String phone, String email, int position) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }
}