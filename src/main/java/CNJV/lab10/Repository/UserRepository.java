package CNJV.lab10.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import CNJV.lab10.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
