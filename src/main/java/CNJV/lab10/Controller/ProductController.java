package CNJV.lab10.Controller;

import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.LoginService;
import CNJV.lab10.Service.ProductService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.dto.ProductDTO;
import CNJV.lab10.model.Client;
import CNJV.lab10.model.Product;

import CNJV.lab10.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ClientService clientService;
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

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

    @PostMapping("/{id}")
    public ResponseEntity<Product> postProduct(@RequestBody ProductDTO productDto, @PathVariable Long id) throws Exception{
        Product product1 = new Product(productDto.getName()
                , productDto.getStatus(), productDto.getAcreage(), productDto.getArena()
                , productDto.getAddress(), productDto.getPrice()
                , LocalDate.now(), clientService.getClient(id));

        productService.save(product1);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ModelAndView buyProduct(@PathVariable Long id) throws Exception{
        Product product = productService.getProduct(id);
        Client client = product.getClient();
        ModelAndView modelAndView = new ModelAndView("mua");
        modelAndView.addObject("products", product);
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    /*Product by id*/
    @GetMapping("/api/{id}")
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

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("add_product");
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
        Iterable<Product> a = listRentProduct;

        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("products", a);
        return modelAndView;
    }

//    @PostMapping(value = "/add")
//    public ResponseEntity<Product> testAddProduct(@RequestBody ProductDTO productDTO) {
//        User user = userService.loadUserByUsername(loginService.get(1l).getUsername());
//        Client client = new Client(user.getName(), user.getPhone(), user.getEmail(), user);
//        clientService.saveClient(client);
//
//        // Tạo đối tượng Product
//        Product product = new Product(productDTO.getName()
//                ,productDTO.getStatus()
//                ,productDTO.getAcreage()
//                ,productDTO.getArena()
//                ,productDTO.getAddress()
//                ,productDTO.getPrice()
//                ,LocalDate.now(), client);
//
//        // Lưu thông tin sản phẩm vào CSDL
//        productService.save(product);
//
//        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
//    }

    @GetMapping("/upload-success")
    public String showUploadSuccessPage() {
        return "index";
    }

    @GetMapping("/upload-error")
    public String showUploadErrorPage() {
        return "index";
    }

    @GetMapping("/search-results")
    public ModelAndView findProduct(@RequestParam("search") String result) {
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

        if (listRentProduct.isEmpty()) {
            // Redirect đến trang "/index"
            return new ModelAndView(new RedirectView("/index"));
        }

        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("products", listRentProduct);
        return modelAndView;
    }
}
