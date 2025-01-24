package ge.ibsu.demo.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ge.ibsu.demo.dto.AddStaff;
import ge.ibsu.demo.dto.RequestData;
import ge.ibsu.demo.dto.StaffSearch;
import ge.ibsu.demo.entities.Staff;
import ge.ibsu.demo.repositories.StaffRepository;
import ge.ibsu.demo.services.StaffService;

/**
 * StaffController
 */

@RestController
@RequestMapping(value = "/api/staff")
public class StaffController {
    StaffService staffService;

    StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Staff> getAll() {
        return this.staffService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Staff getById(@PathVariable Long id) {
        return staffService.getStaffById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json" })
    public Staff add(@RequestBody AddStaff staff) {
        return this.staffService.add(staff);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) {
        this.staffService.deleteStaffById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { "application/json" })
    public Staff editStaff(@RequestBody AddStaff staff, @PathVariable Long id) throws BadRequestException {
        return this.staffService.editStaff(staff, id);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = { "application/json" })
    public Page<Staff> search(@RequestBody RequestData<StaffSearch> requestData) {
        return this.staffService.search(requestData.getData(), requestData.getPaging());
    }
}
