package CNJV.lab10.Controller;

import CNJV.lab10.Service.*;
import CNJV.lab10.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/staff")
    public ModelAndView adminStaff() {
        Iterable<Staff> staffs = staffService.getAllStaff();
        ModelAndView modelAndView = new ModelAndView("admin_staff");
        modelAndView.addObject("staffs", staffs);
        return modelAndView;
    }

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

    @GetMapping("/oder")
    public ModelAndView payOder(@PathVariable Long id) throws Exception{
        Iterable<Oder> oders = oderService.getAllOder();
//        User user = userService.loadUserByUsername(loginService.get(1l).getUsername());
//
//        LocalDate dateCreate = LocalDate.now(); // Lấy ngày hiện tại
//        OderStatus status = OderStatus.Unprocessed; // Trạng thái chờ xử lý
//        Client client = new Client("John Doe", "0123456789", "johndoe@example.com", user);
//        Staff staff = new Staff("Jane Smith", "sales@example.com", "Sales Manager", 1, user);
//        List<Product> products = Arrays.asList(
//                new Product("TV Set 1", 1, 1000.0, "Area 1", "Duc Hoa, Long An", 200000.0, LocalDate.now(), client),
//                new Product("TV Set 2", 0, 1200.0, "Area 2", "Tan An, Long An", 250000.0, LocalDate.now(), client),
//                new Product("TV Set 3", 0, 1100.0, "Area 3", "My Tho, Tien Giang", 220000.0, LocalDate.now(), client),
//                new Product("TV Set 4", 1, 1050.0, "Area 4", "Can Tho, Can Tho", 240000.0, LocalDate.now(), client),
//                new Product("TV Set 5", 0, 1300.0, "Area 5", "Vinh Long, Vinh Long", 210000.0, LocalDate.now(), client),
//                new Product("TV Set 6", 1, 1150.0, "Area 6", "Tra Vinh, Tra Vinh", 230000.0, LocalDate.now(), client),
//                new Product("TV Set 7", 1, 1080.0, "Area 7", "Ben Tre, Ben Tre", 260000.0, LocalDate.now(), client),
//                new Product("TV Set 8", 0, 1250.0, "Area 8", "Long Xuyen, An Giang", 270000.0, LocalDate.now(), client),
//                new Product("TV Set 9", 1, 1120.0, "Area 9", "Rach Gia, Kien Giang", 280000.0, LocalDate.now(), client),
//                new Product("TV Set 10", 0, 1180.0, "Area 10", "Ca Mau, Ca Mau", 290000.0, LocalDate.now(), client),
//                new Product("TV Set 1", 1, 1000.0, "Area 1", "Duc Hoa, Long An", 200000.0, LocalDate.now(), client),
//                new Product("TV Set 2", 0, 1200.0, "Area 2", "Tan An, Long An", 250000.0, LocalDate.now(), client),
//                new Product("TV Set 3", 0, 1100.0, "Area 3", "My Tho, Tien Giang", 220000.0, LocalDate.now(), client),
//                new Product("TV Set 4", 1, 1050.0, "Area 4", "Can Tho, Can Tho", 240000.0, LocalDate.now(), client),
//                new Product("TV Set 5", 0, 1300.0, "Area 5", "Vinh Long, Vinh Long", 210000.0, LocalDate.now(), client),
//                new Product("TV Set 6", 1, 1150.0, "Area 6", "Tra Vinh, Tra Vinh", 230000.0, LocalDate.now(), client),
//                new Product("TV Set 7", 1, 1080.0, "Area 7", "Ben Tre, Ben Tre", 260000.0, LocalDate.now(), client),
//                new Product("TV Set 8", 0, 1250.0, "Area 8", "Long Xuyen, An Giang", 270000.0, LocalDate.now(), client),
//                new Product("TV Set 9", 1, 1120.0, "Area 9", "Rach Gia, Kien Giang", 280000.0, LocalDate.now(), client),
//                new Product("TV Set 10", 0, 1180.0, "Area 10", "Ca Mau, Ca Mau", 290000.0, LocalDate.now(), client),
//                new Product("TV Set 1", 1, 1000.0, "Area 1", "Duc Hoa, Long An", 200000.0, LocalDate.now(), client),
//                new Product("TV Set 2", 0, 1200.0, "Area 2", "Tan An, Long An", 250000.0, LocalDate.now(), client),
//                new Product("TV Set 3", 0, 1100.0, "Area 3", "My Tho, Tien Giang", 220000.0, LocalDate.now(), client),
//                new Product("TV Set 4", 1, 1050.0, "Area 4", "Can Tho, Can Tho", 240000.0, LocalDate.now(), client),
//                new Product("TV Set 5", 0, 1300.0, "Area 5", "Vinh Long, Vinh Long", 210000.0, LocalDate.now(), client),
//                new Product("TV Set 6", 1, 1150.0, "Area 6", "Tra Vinh, Tra Vinh", 230000.0, LocalDate.now(), client),
//                new Product("TV Set 7", 1, 1080.0, "Area 7", "Ben Tre, Ben Tre", 260000.0, LocalDate.now(), client),
//                new Product("TV Set 8", 0, 1250.0, "Area 8", "Long Xuyen, An Giang", 270000.0, LocalDate.now(), client),
//                new Product("TV Set 9", 1, 1120.0, "Area 9", "Rach Gia, Kien Giang", 280000.0, LocalDate.now(), client),
//                new Product("TV Set 10", 0, 1180.0, "Area 10", "Ca Mau, Ca Mau", 290000.0, LocalDate.now(), client)
//        );
//
//        Oder order = new Oder(dateCreate, status, 500000, client, staff, products.get(0));
//        Oder order1 = new Oder(dateCreate, status, 500000, client, staff, products.get(1));
//        oderService.save(order);
//        oderService.save(order1);
//        List<Oder> listOder = new ArrayList<>();
//        listOder.add(order1);
//        listOder.add(order);


        Product product = productService.getProduct(id);
        ModelAndView modelAndView = new ModelAndView("HoaDon");
//        modelAndView.addObject("tien", product.getPrice()*0.05);
        modelAndView.addObject("now", LocalDate.now());
        modelAndView.addObject("oders", oders);
//        modelAndView.addObject("client", user);
//        modelAndView.addObject("product", product);
        return modelAndView;
    }
}
