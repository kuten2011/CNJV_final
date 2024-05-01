package CNJV.lab10.Controller;

import CNJV.lab10.Service.ProductService;
import CNJV.lab10.dto.ProductDTO;
import CNJV.lab10.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    /*Product*/

    @GetMapping({"/api"})
    public @NotNull Iterable<Product> getAllProduct() {
        Iterable<Product> listProducts = productService.getAllProduct();
        return listProducts;
    }

    @GetMapping({"/", ""})
    public ModelAndView getAllProductsHTML() {
        Iterable<Product> listProducts = productService.getAllProduct();
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("products", listProducts);
        return modelAndView;
    }

//    @PostMapping
//    public ResponseEntity<Product> postProduct(@RequestBody ProductDTO productDto) {
//        Product product1 = Product.builder()
//                .brand(productDto.getBrand())
//                .color(productDto.getColor())
//                .name(productDto.getName())
//                .price(productDto.getPrice())
//                .build();
//        productService.save(product1);
//        return new ResponseEntity<>(product1, HttpStatus.CREATED);
//    }

    /*Product by id*/
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) throws Exception{
        return productService.getProduct(id);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Product> putProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws Exception{
//        Product product = productService.getProduct(id);
//        if (productDTO.getName() != null) product.setName(productDTO.getName());
//        if (productDTO.getBrand() != null) product.setBrand(productDTO.getBrand());
//        if (productDTO.getColor() != null) product.setColor(productDTO.getColor());
//        if (productDTO.getPrice() != null) product.setPrice(productDTO.getPrice());
//        productService.save(product);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) throws Exception{
//        Product product = productService.getProduct(id);
//        if (productDTO.getName() != null) product.setName(productDTO.getName());
//        if (productDTO.getBrand() != null) product.setBrand(productDTO.getBrand());
//        if (productDTO.getColor() != null) product.setColor(productDTO.getColor());
//        if (productDTO.getPrice() != null) product.setPrice(productDTO.getPrice());
//        productService.save(product);
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/sell")
    public ModelAndView getSellProduct() {
        Iterable<Product> products = productService.getAllProduct();
        List<Product> listSellProduct = new ArrayList<>();
        for (Product product : products) {
            if (product.getStatus() == 0) {
                listSellProduct.add(product);
            }
        }

        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("products", listSellProduct);
        return modelAndView;
    }

    @GetMapping("/rent")
    public ModelAndView getRentProduct() {
        Iterable<Product> products = productService.getAllProduct();
        List<Product> listRentProduct = new ArrayList<>();
        for (Product product : products) {
            if (product.getStatus() == 1) {
                listRentProduct.add(product);
            }
        }

        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("products", listRentProduct);
        return modelAndView;
    }

    @GetMapping("/search-results")
    public ModelAndView findProduct(@RequestParam("search") String result) {
        if (result == null || result.trim().isEmpty()) {
            ModelAndView errorModelAndView = new ModelAndView("error");
            errorModelAndView.addObject("message", "Vui lòng nhập từ khóa tìm kiếm");
            return errorModelAndView;
        }

        Iterable<Product> products = productService.getAllProduct();
        List<Product> listRentProduct = new ArrayList<>();
        result = result.toLowerCase().replaceAll("\\+", "").replace(" ", "").trim();
        for (Product product : products) {
            String[] parts = product.getAddress().split(",");
            String province = parts[parts.length - 1].trim();
            province = province.replaceAll("\\s+", "").toLowerCase().trim();
            if (result.equalsIgnoreCase(province)) {
                listRentProduct.add(product);
            }
        }
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("products", listRentProduct);
        return modelAndView;
    }
}
