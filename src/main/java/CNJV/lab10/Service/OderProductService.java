package CNJV.lab10.Service;

import CNJV.lab10.model.Oder;
import CNJV.lab10.model.OderProduct;
import CNJV.lab10.model.OderProductPK;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface OderProductService {
    OderProduct create(@NotNull(message = "The product for oder cannot null") @Valid OderProduct oderProduct);
    Iterable<OderProduct> getAll();

    OderProduct getOderProduct(OderProductPK Id);
}
