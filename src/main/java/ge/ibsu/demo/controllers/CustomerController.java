package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = { "application/json" })
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
    public Customer getOne(@PathVariable Long id) {
        return customerService.getOne(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json" })
    public Customer add(@RequestBody AddCustomer addCustomer) {
        return customerService.addEditCustomer(addCustomer, null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = { "application/json" })
    public Customer edit(@RequestBody AddCustomer addCustomer, @PathVariable Long id) {
        return customerService.addEditCustomer(addCustomer, id);
    }
}
