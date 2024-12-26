package API.controllers;

import API.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private List<Customer> customers = new ArrayList<Customer>(Arrays.asList(
            new Customer(1, "juan", "jjms", "123"),
            new Customer(2, "Ricardo", "rgms", "123"),
            new Customer(3, "Marisol", "msf", "123")
    ));

    @GetMapping
    public List<Customer> getCustomers() {
        return customers;
    }

    @GetMapping("/{username}")
    public Customer getCustomerByUsername(@PathVariable String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) return customer;
        }
        return new Customer();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        for(Customer c : customers) {
            if(c.getId() == customer.getId()) {
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());
                c.setName(customer.getName());
                return c;
            }
        }
        return new Customer();
    }

    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable int id) {
        for (Customer customer : customers) {
            if(customer.getId() == id) {
                customers.remove(customer);
                return customer;
            }
        }
        return new Customer();
    }
}
