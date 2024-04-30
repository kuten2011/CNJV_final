package CNJV.lab10.Service;

import CNJV.lab10.model.Oder;

public interface OderService {
    Iterable<Oder> getAllOder();
    Oder getOder(Long id) throws Exception;
    Oder save(Oder oder);
    void removeOder(Long id);
    void updateOder(Oder oder);
}
