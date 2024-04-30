package CNJV.lab10.Service;

import CNJV.lab10.Repository.OderProductRepository;
import CNJV.lab10.model.OderProduct;
import CNJV.lab10.model.OderProductPK;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OderProductServiceImpl implements OderProductService{
    @Autowired
    private OderProductRepository oderProductRepository;

    @Override
    public OderProduct create(OderProduct oderProduct) {
        return oderProductRepository.save(oderProduct);
    }

    @Override
    public Iterable<OderProduct> getAll() {
        return oderProductRepository.findAll();
    }

    @Override
    public OderProduct getOderProduct(OderProductPK Id){
        return oderProductRepository.findById(Id).get();
    }


}
