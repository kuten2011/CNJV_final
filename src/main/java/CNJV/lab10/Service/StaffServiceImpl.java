package CNJV.lab10.Service;

import CNJV.lab10.Repository.StaffRepository;
import CNJV.lab10.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService{
    @Autowired
    StaffRepository staffRepository;

    @Override
    public Iterable<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaff(Long id) throws Exception{
        return staffRepository.findById(id).orElseThrow(() -> new Exception("Staff not found"));
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void removeStaff(Long id) {
        staffRepository.deleteById(id);
    }
}
