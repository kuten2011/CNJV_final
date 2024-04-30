package CNJV.lab10.dto;

import CNJV.lab10.model.OderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class OderDTO {
    private OderStatus status;
    private List<OderProductDTO> oderProduct;

    public List<OderProductDTO> getProductOrders() {
        return oderProduct;
    }

    public void setProductOrders(List<OderProductDTO> productOrders) {
        this.oderProduct = productOrders;
    }
}
