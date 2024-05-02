package CNJV.lab10.Controller;

import CNJV.lab10.Service.StaffService;
import CNJV.lab10.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping("/api")
    public Iterable<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/api/{id}")
    public Staff getStaffId(@PathVariable Long id) throws Exception{
        return staffService.getStaff(id);
    }
}
