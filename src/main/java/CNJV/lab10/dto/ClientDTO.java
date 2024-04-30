package CNJV.lab10.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ClientDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;

    @Builder
    public ClientDTO(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
