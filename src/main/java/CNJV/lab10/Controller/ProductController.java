//package CNJV.lab10.Controller;
//
//import CNJV.lab10.Service.ProductService;
//import CNJV.lab10.dto.ProductDTO;
//import CNJV.lab10.model.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.constraints.NotNull;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController {
//    @Autowired
//    ProductService productService;
//
//    /*Product*/
//    @GetMapping({"", "/"})
//    public @NotNull Iterable<Product> getAllProduct() {
//        return productService.getAllProduct();
//    }
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
//
//    /*Product by id*/
//    @GetMapping("/{id}")
//    public Product getProduct(@PathVariable Long id) throws Exception{
//        return productService.getProduct(id);
//    }
//
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
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
//        productService.remove(id);
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//}
