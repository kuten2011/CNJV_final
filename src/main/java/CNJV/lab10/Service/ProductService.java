package CNJV.lab10.Service;

import CNJV.lab10.model.Product;

public interface ProductService {
    Iterable<Product> getAllProduct();
    Product getProduct(Long id) throws Exception;
    Product save(Product product);
    void remove(Long id);
}