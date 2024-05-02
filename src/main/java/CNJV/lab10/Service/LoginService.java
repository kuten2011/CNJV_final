package CNJV.lab10.Service;

import CNJV.lab10.Repository.LoginRepository;
import CNJV.lab10.model.Login;

public interface LoginService {
    Iterable<Login> getAll() ;
    Login get(Long id);
    Login update(Login login);
    void remove(Long id);
}
