package CNJV.lab10.Controller;

import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.ProductService;
import CNJV.lab10.Service.StaffService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.model.Client;
import CNJV.lab10.model.Product;
import CNJV.lab10.model.Staff;
import CNJV.lab10.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ClientService clientService;
//    @Autowired
//    StaffService staffService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @GetMapping({"", "/"})
    public ModelAndView adminPage() {
        return new ModelAndView("admin");
    }

    @GetMapping("/client")
    public ModelAndView adminClient() {
        Iterable<Client> clients = clientService.getAllClient();
        ModelAndView modelAndView = new ModelAndView("admin_client");
        modelAndView.addObject("clients", clients);
        return modelAndView;
    }

//    @GetMapping("/staff")
//    public ModelAndView adminStaff() {
//        Iterable<Staff> staffs = staffService.getAllStaff();
//        ModelAndView modelAndView = new ModelAndView("admin_staff");
//        modelAndView.addObject("staffs", staffs);
//        return modelAndView;
//    }

    @GetMapping("/user")
    public ModelAndView adminUser() {
        Iterable<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView("admin_user");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/product")
    public ModelAndView adminProduct() {
        Iterable<Product> products = productService.getAllProduct();
        ModelAndView modelAndView = new ModelAndView("admin_product");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
