package ge.ibsu.demo.services;

import java.util.List;
import java.util.Date;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ge.ibsu.demo.dto.AddStaff;
import ge.ibsu.demo.dto.Paging;
import ge.ibsu.demo.dto.StaffSearch;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.Staff;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.repositories.StaffRepository;
import jakarta.transaction.Transactional;

/**
 * StaffService
 */

@Service
public class StaffService {

    StaffRepository staffRepository;
    AddressRepository addressRepository;

    StaffService(StaffRepository staffRepository, AddressRepository addressRepository) {
        this.staffRepository = staffRepository;
        this.addressRepository = addressRepository;
    }

    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @Transactional
    public Staff add(AddStaff staff) {
        Staff staffEntity = new Staff();
        Address address = new Address();
        address.setAddress(staff.getAddress());
        address = this.addressRepository.save(address);
        staffEntity.setFirstName(staff.getFirstName());
        staffEntity.setLastName(staff.getLastName());
        staffEntity.setEmail(staff.getEmail());
        staffEntity.setCreateDate(new Date());
        staffEntity.setAddress(address);
        staffEntity.setActive(staff.getActive());
        return staffRepository.save(staffEntity);

    }

    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Transactional
    public Staff editStaff(AddStaff staff, Long id) throws BadRequestException {
        if (id == null) {
            throw new BadRequestException("BadRequest");
        }
        Staff newStaff = new Staff();
        newStaff.setId(id);
        Address address = new Address();
        address.setAddress(staff.getAddress());
        address = this.addressRepository.save(address);
        newStaff.setEmail(staff.getEmail());
        newStaff.setActive(staff.getActive());
        newStaff.setFirstName(staff.getFirstName());
        newStaff.setLastName(staff.getLastName());
        newStaff.setAddress(address);

        return this.staffRepository.save(newStaff);
    }

    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }

    public Page<Staff> search(StaffSearch staffSearch, Paging page) {
        Pageable pageRequest = PageRequest.of(page.getPage() - 1, page.getSize());

        String searchText = "%" + staffSearch.getSearchString() + "%";
        return this.staffRepository.searchStaffs(searchText, pageRequest);
    }
}
