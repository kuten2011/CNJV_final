package CNJV.lab10.Repository;

import CNJV.lab10.model.Login;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long> {
}
