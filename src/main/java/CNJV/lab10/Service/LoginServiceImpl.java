package CNJV.lab10.Service;

import CNJV.lab10.Repository.LoginRepository;
import CNJV.lab10.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    LoginRepository loginRepository;
    @Override
    public Iterable<Login> getAll() {
        return loginRepository.findAll();
    }

    @Override
    public Login get(Long id){
        return loginRepository.findById(id).get();
    }

    @Override
    public Login update(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public void remove(Long id) {
        loginRepository.deleteById(id);
    }
}
