package CNJV.lab10.Controller;


import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.LoginService;
import CNJV.lab10.Service.ProductService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.dto.ProductDTO;
import CNJV.lab10.jwtutils.TokenManager;
import CNJV.lab10.model.Client;
import CNJV.lab10.model.Product;
import CNJV.lab10.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.time.LocalDate;
import java.io.IOException;
import org.springframework.util.FileCopyUtils;

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

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String showRegisterPage() {
        try {
            String name = loginService.get(1L).getUsername();
            if (name != null && !name.isEmpty()) {
                return "redirect:/index"; // Sử dụng redirect:/index để chuyển hướng
            }
        } catch (Exception ex) {
            // Xử lý exception, ví dụ như ghi log
            ex.printStackTrace(); // Thông tin lỗi sẽ được in ra console
        }
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        try {
            String name = loginService.get(1L).getUsername();
            if (name != null && !name.isEmpty()) {
                return "redirect:/index"; // Sử dụng redirect:/index để chuyển hướng
            }
        } catch (Exception ex) {
            // Xử lý exception, ví dụ như ghi log
            ex.printStackTrace(); // Thông tin lỗi sẽ được in ra console
        }
        return "login";
    }

    @GetMapping("/index")
    public String showIndexPage() {
        try {
            String name = loginService.get(1L).getUsername();
            if (name != null && !name.isEmpty()) {
                if (loginService.get(1l).getRole() == 0) {
                    return "index_after_admin";
                } else if (loginService.get(1l).getRole() == 1) {
                    return "index_after_staff";
                }
                return "index_after"; // Sử dụng redirect:/index để chuyển hướng
            }
        } catch (Exception ex) {
            // Xử lý exception, ví dụ như ghi log
            ex.printStackTrace(); // Thông tin lỗi sẽ được in ra console
        }
        return "index";
    }

    @GetMapping("/test")
    public String testN() {
        return "test";
    }

    @PostMapping("/product/add")
    public RedirectView addPro(HttpServletRequest http, @RequestPart("avatar") MultipartFile avatar) throws IOException {
        User user = userService.loadUserByUsername(loginService.get(1L).getUsername());

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
        if (acreageParam != null) {  // Fixed typo here
            try {
                acreage = Double.parseDouble(acreageParam);
            } catch (NumberFormatException e) {
                // Handle the case where the parameter value cannot be parsed as a Double
                e.printStackTrace();
            }
        }

        Product product = new Product(http.getParameter("name")
                , http.getParameter("status").equals("Sell") ? 1 : 0
                , acreage
                , http.getParameter("arena")
                , http.getParameter("address")
                , price
                , LocalDate.now(), user.getClient());

        // Save product information to the database
        productService.save(product);

        int size = 0;
        for (Product p : productService.getAllProduct()) {
            size++;
        }

        String filename = Integer.toString(size) + ".jpg"; // Corrected int.toString()

        String uploadDir = "src/main/resources/static/photo/product";
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory and all necessary parent directories
        }

        File filePath = new File(directory.getAbsolutePath() + File.separator + filename);
        FileCopyUtils.copy(avatar.getBytes(), filePath);

        return new RedirectView("/index");
    }


    @GetMapping("/changepass")
    public ModelAndView changePass() {
        String name = "";
        String pass = "";
        try {
            name = loginService.get(1l).getUsername();
            pass = loginService.get(1l).getPassword();
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(name, pass)
            );
        } catch (Exception e) {
            return new ModelAndView(new RedirectView("/login"));
        }
        return new ModelAndView("changepass");
    }
}
