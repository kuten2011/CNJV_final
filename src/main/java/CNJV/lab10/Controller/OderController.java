package CNJV.lab10.Controller;

import CNJV.lab10.Service.OderProductService;
import CNJV.lab10.Service.OderService;
import CNJV.lab10.Service.OderServiceImpl;
import CNJV.lab10.Service.ProductService;
import CNJV.lab10.dto.OderDTO;
import CNJV.lab10.dto.OderProductDTO;
import CNJV.lab10.model.Oder;
import CNJV.lab10.model.OderProduct;
import CNJV.lab10.model.OderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/oders")
public class OderController {
    @Autowired
    private OderService oderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OderProductService oderProductService;

    @GetMapping({"","/"})
    public @NotNull Iterable<Oder> getAllOder() {
        return oderService.getAllOder();
    }

    @GetMapping("/oderProducts/{id}")
    public @NotNull Iterable<OderProduct> getAllProductOder(@PathVariable Long id) {
        List<OderProduct> list = new ArrayList<>();
        for (OderProduct op : oderProductService.getAll()) {
            if (op.getOderId() == id) {
                list.add(op);
            }
        }
        return list;
    }

    @PostMapping
    public ResponseEntity<Oder> create(@RequestBody OderDTO oderDTO) throws Exception {
        List<OderProductDTO> formDTOs = oderDTO.getProductOrders();
        validateProductsExistence(formDTOs);
        Oder oder = new Oder();
        oder.setStatus(OderStatus.PAID);
        oder = this.oderService.save(oder);

        List<OderProduct> oderProducts = new ArrayList<OderProduct>();
        for (OderProductDTO dto : formDTOs) {
            oderProducts.add(oderProductService.create(new OderProduct(oder,
                    productService.getProduct(dto.getProduct().getId()),
                    dto.getQuantity())));
        }

        oder.setOderProducts(oderProducts);
        this.oderService.updateOder(oder);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/oders/{id}")
                .buildAndExpand(oder.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(oder, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OderProductDTO> orderProducts) {
        List<OderProductDTO> list = orderProducts
                .stream()
                .filter(op -> {
                    try {
                        return Objects.isNull(productService.getProduct(op
                                .getProduct()
                                .getId()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new Exception("Product not found");
        }
    }

    @GetMapping("/{id}")
    private Oder getOderById(@PathVariable Long id) throws Exception{
        return oderService.getOder(id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Oder> putOder(@PathVariable Long id, @RequestBody OderDTO oderDTO) throws Exception{
        Oder oder = oderService.getOder(id);
        if (oderDTO.getStatus() != null) oder.setStatus(oderDTO.getStatus());
        oderService.updateOder(oder);
        return new ResponseEntity<>(oder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteOder(@PathVariable Long id) {
        oderService.removeOder(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
