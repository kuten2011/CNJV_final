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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.time.LocalDate;
import java.io.IOException;


@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;
    @Autowired
    LoginService loginService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/test")
    public String testN() {
        return "test";
    }

    @PostMapping("/product/add")
    public RedirectView addPro(HttpServletRequest http, @RequestPart("avatar") MultipartFile avatar) throws IOException {
        User user = userService.loadUserByUsername(loginService.get(1l).getUsername());
        Client client = new Client(user.getName(), user.getPhone(), user.getEmail(), user);
        clientService.saveClient(client);

        Double price = null;
        String priceParam = http.getParameter("price");
        if (priceParam != null) {
            try {
                price = Double.parseDouble(priceParam);
            } catch (NumberFormatException e) {
                // Handle the case where the parameter value cannot be parsed as a Double
                e.printStackTrace();
            }
        }

        Double acreage = null;
        String acreageParam = http.getParameter("acreage");
        if (priceParam != null) {
            try {
                acreage = Double.parseDouble(acreageParam);
            } catch (NumberFormatException e) {
                // Handle the case where the parameter value cannot be parsed as a Double
                e.printStackTrace();
            }
        }

        Product product = new Product(http.getParameter("name")
                ,http.getParameter("status").equals("Sell") ? 1 : 0
                ,acreage
                ,http.getParameter("arena")
                ,http.getParameter("address")
                ,price
                , LocalDate.now(), client);

        // Lưu thông tin sản phẩm vào CSDL
        productService.save(product);

//        String staticPath = "src/main/resources/hello";
//        File directory = new File(staticPath);
//        if (!directory.exists()) {
//            directory.mkdirs(); // Tạo thư mục và tất cả các thư mục cha cần thiết
//        }
//
//        String fileName = avatar.getOriginalFilename();
//        String filePath = staticPath +"/" + fileName; // Đường dẫn thư mục static/images/
//        avatar.transferTo(new File(filePath));
        return new RedirectView("/index");
    }
}
