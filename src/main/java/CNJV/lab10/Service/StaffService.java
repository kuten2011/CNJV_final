package CNJV.lab10.Service;

import CNJV.lab10.model.Staff;

public interface StaffService {
    Iterable<Staff> getAllStaff();
    Staff getStaff(Long id) throws Exception;
    Staff saveStaff(Staff staff);
    void removeStaff(Long id);
}
