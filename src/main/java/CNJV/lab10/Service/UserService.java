package CNJV.lab10.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import CNJV.lab10.model.User;

public interface UserService extends UserDetailsService {

    User update(User user);

    public User loadUserByUsername(String username) throws UsernameNotFoundException;

    public Iterable<User> getAll();
}