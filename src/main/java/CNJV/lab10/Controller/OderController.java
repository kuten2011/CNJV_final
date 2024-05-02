package CNJV.lab10.Controller;

import CNJV.lab10.Service.*;
import CNJV.lab10.dto.OderDTO;
import CNJV.lab10.dto.OderProductDTO;
import CNJV.lab10.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @GetMapping("/api")
    public @NotNull Iterable<Oder> getAllOder() {
        return oderService.getAllOder();
    }

    @GetMapping("/{id}")
    public ModelAndView payOder(@PathVariable Long id) throws Exception{
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
        return modelAndView;
    }




    @PostMapping("/{id}")
    private ResponseEntity<Oder> addOder(@PathVariable Long id) throws Exception{
        Product product = productService.getProduct(id);

        Oder oder = new Oder(LocalDate.now(), OderStatus.Unprocessed, 1, product.getClient(), null, product);
        oderService.save(oder);
        return new ResponseEntity<Oder>(oder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteOder(@PathVariable Long id) {
        oderService.removeOder(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
