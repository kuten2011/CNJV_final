package CNJV.lab10.Service;

import CNJV.lab10.Repository.OderRepository;
import CNJV.lab10.model.Oder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class OderServiceImpl implements OderService {
    @Autowired
    private OderRepository oderRepository;
    @Override
    public Iterable<Oder> getAllOder() {
        return oderRepository.findAll();
    }

    @Override
    public Oder getOder(Long id) throws Exception{
        return oderRepository.findById(id).orElseThrow(() -> new Exception("Oder not found"));
    }

    @Override
    public Oder save(Oder oder) {
        oder.setDateCreate(LocalDate.now());
        return oderRepository.save(oder);
    }

    @Override
    public void removeOder(Long id) {
        oderRepository.deleteById(id);
    }

    @Override
    public void updateOder(Oder oder) {
        this.oderRepository.save(oder);
    }
}
