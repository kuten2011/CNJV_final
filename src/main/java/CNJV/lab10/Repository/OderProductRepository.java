package CNJV.lab10.Repository;

import CNJV.lab10.model.OderProduct;
import CNJV.lab10.model.OderProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OderProductRepository extends JpaRepository<OderProduct, OderProductPK> {
}
