package CNJV.lab10.Controller;

import CNJV.lab10.Service.*;
import CNJV.lab10.dto.OderDTO;
import CNJV.lab10.dto.OderProductDTO;
import CNJV.lab10.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/oder")
public class OderController {
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


    @GetMapping("/api")
    public @NotNull Iterable<Oder> getAllOder() {
        return oderService.getAllOder();
    }

    @GetMapping("/{id}")
    public ModelAndView payOder(@PathVariable Long id) throws Exception{
        try {
            String name = loginService.get(1L).getUsername();
            if (name != null && !name.isEmpty()) {
                int size = 0;
                if (oderService.getAllOder() != null) {
                    // Chuyển đổi Iterable thành List để sử dụng phương thức size()
                    List<Oder> orderList = new ArrayList<>();
                    oderService.getAllOder().forEach(orderList::add);

                    size = orderList.size();
                }

                Product product = productService.getProduct(id);
                User user = userService.loadUserByUsername(loginService.get(1l).getUsername());
                ModelAndView modelAndView = new ModelAndView("HoaDon");
                modelAndView.addObject("tien", product.getPrice()*0.05);
                modelAndView.addObject("now", LocalDate.now());
                modelAndView.addObject("oderid", size+1);
                modelAndView.addObject("client", user);
                modelAndView.addObject("product", product);
                return modelAndView; // Sử dụng redirect:/index để chuyển hướng
            }
        } catch (Exception ex) {
            // Xử lý exception, ví dụ như ghi log
            ex.printStackTrace(); // Thông tin lỗi sẽ được in ra console
        }
        return new ModelAndView(new RedirectView("/login"));
    }

    @GetMapping("/get/{id}")
    public RedirectView doOder(@PathVariable Long id) throws Exception{
        Oder oder = oderService.getOder(id);
        oder.setStatus(OderStatus.Processing);
        oder.setStaff(userService.loadUserByUsername(loginService.get(1l).getUsername()).getStaff());
        oderService.updateOder(oder);
        return new RedirectView("/admin/oder");
    }

    @GetMapping("/success/{id}")
    public RedirectView successOder(@PathVariable Long id) throws Exception{
        Oder oder = oderService.getOder(id);
        oder.setStatus(OderStatus.Processed);
//        oder.setStaff
        oderService.updateOder(oder);

        Product product = oder.getProduct();
        product.setStatus(2);
        productService.save(product);
        return new RedirectView("/admin/oder");
    }

    @GetMapping("/cancel/{id}")
    public RedirectView cancelOder(@PathVariable Long id) throws Exception{
        Oder oder = oderService.getOder(id);
        oder.setStatus(OderStatus.Unprocessed);
        oder.setStaff(null);
        oderService.updateOder(oder);
        return new RedirectView("/admin/oder");
    }

    @PostMapping("/{id}")
    private ResponseEntity<Oder> addOder(@PathVariable Long id) throws Exception{
        Product product = productService.getProduct(id);

        Oder oder = new Oder(LocalDate.now(), OderStatus.Unprocessed, 1, userService.loadUserByUsername(loginService.get(1l).getUsername()).getClient(), null, product);
        oderService.save(oder);
        return new ResponseEntity<Oder>(oder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteOder(@PathVariable Long id) {
        oderService.removeOder(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    public ModelAndView payOder() throws Exception{
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
            return new ModelAndView( new RedirectView("/login"));
        }

        Iterable<Oder> oders = oderService.getAllOder();
        List<Oder> oderList = new ArrayList<>();
        for (Oder oder : oders) {
            if (oder.getClient().getUser().getUsername().equals(loginService.get(1l).getUsername())) {
                oderList.add(oder);
            }
        }

        ModelAndView modelAndView = new ModelAndView("oder");
        modelAndView.addObject("now", LocalDate.now());
        modelAndView.addObject("oders", oderList);

        return modelAndView;
    }
}
