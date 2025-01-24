package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddCustomer;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.entities.Customer;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.repositories.CityRepository;
import ge.ibsu.demo.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    public CustomerService(CustomerRepository customerRepository,
            AddressRepository addressRepository, CityRepository cityRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getOne(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Transactional
    public Customer addEditCustomer(AddCustomer addCustomer, Long id) {
        Customer customer = new Customer();
        if (id != null) {
            customer.setId(id);
        }
        customer.setFirstName(addCustomer.getFirstName());
        customer.setLastName(addCustomer.getLastName());
        customer.setCreateDate(new Date());

        Address address = addressRepository.findOneByAddress(addCustomer.getAddress());
        City city = cityRepository.findOneByCity(addCustomer.getCity());
        if (city == null) {
            City newCity = new City();
            newCity.setCity(addCustomer.getCity());
            city = cityRepository.save(newCity);
        }
        if (address == null) {
            Address newAddress = new Address();
            newAddress.setAddress(addCustomer.getAddress());
            newAddress.setCity(city);
            address = addressRepository.save(newAddress);
        }

        customer.setAddress(address);
        return customerRepository.save(customer);
    }
}
