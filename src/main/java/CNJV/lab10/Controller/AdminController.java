package CNJV.lab10.Controller;

import CNJV.lab10.Service.*;
import CNJV.lab10.jwtutils.Token;
import CNJV.lab10.jwtutils.TokenManager;
import CNJV.lab10.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OderService oderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private ClientService clientService;
    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenManager tokenManager;

    @GetMapping({"", "/"})
    public ModelAndView adminPage() throws Exception{
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
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            return new ModelAndView("admin");
        } else {
            return new ModelAndView(new RedirectView("/index"));
        }
    }

    @GetMapping("/client")
    public ModelAndView adminClient() {
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
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            Iterable<Client> clients = clientService.getAllClient();
            ModelAndView modelAndView = new ModelAndView("admin_client");
            modelAndView.addObject("clients", clients);
            return modelAndView;
        } else {
            return new ModelAndView(new RedirectView("/index"));
        }
    }

    @GetMapping("/staff")
    public ModelAndView adminStaff() {
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
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            Iterable<Staff> staffs = staffService.getAllStaff();
            ModelAndView modelAndView = new ModelAndView("admin_staff");
            modelAndView.addObject("staffs", staffs);
            return modelAndView;
        } else {
            return new ModelAndView(new RedirectView("/index"));
        }
    }

    @GetMapping("/addUser")
    public ModelAndView adminUser() {
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
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            ModelAndView modelAndView = new ModelAndView("admin_register");
            return modelAndView;
        } else {
            return new ModelAndView(new RedirectView("/index"));
        }
    }

    @GetMapping("/user")
    public ModelAndView adminAddUser() {
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
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            Iterable<User> users = userService.getAll();
            ModelAndView modelAndView = new ModelAndView("admin_user");
            modelAndView.addObject("users", users);
            return modelAndView;
        } else {
            return new ModelAndView(new RedirectView("/index"));
        }
    }

    @GetMapping("/product")
    public ModelAndView adminProduct() {
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
            throw e;
        }

        if (loginService.get(1l).getRole() == 0) {
            Iterable<Product> products = productService.getAllProduct();
            ModelAndView modelAndView = new ModelAndView("admin_product");
            modelAndView.addObject("products", products);
            return modelAndView;
        } else {
            return new ModelAndView(new RedirectView("/index"));
        }
    }

    @GetMapping("/oder")
    public ModelAndView payOder() throws Exception{
        Iterable<Oder> oders = oderService.getAllOder();

        ModelAndView modelAndView = new ModelAndView("admin_oder");
        modelAndView.addObject("now", LocalDate.now());
        modelAndView.addObject("oders", oders);

        return modelAndView;
    }
}
