package API.controllers;

import API.domain.Customer;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Method to get a list with all the customers
     * @return - List of customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customers);
    }

    /**
     * Endpoint to get a customer by username
     * @param username - Username used to find a user
     * @return - The user with the username introduced or an error message instead
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getCustomerByUsername(@PathVariable String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) return ResponseEntity.ok(customer);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con username: " + username);
    }

    /**
     * Endpoint to register a new customer
     * @param customer - Customer to be registered
     * @return - A success message
     */
    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer created successfully: " + customer.getUsername());
    }

    /**
     * Endpoint to update all customer data
     * @param customer - All data customer
     * @return
     */
    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) {
        for(Customer c : customers) {
            if(c.getId() == customer.getId()) {
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());
                c.setName(customer.getName());
                return ResponseEntity.ok("Customer updated successfully: " + customer.getId());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found: " + customer.getId());
    }

    /**
     * Endpoint to Delete a user by its ID
     * @param id - ID from user to be deleted
     * @return - Operation status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id) {
        for (Customer customer : customers) {
            if(customer.getId() == id) {
                customers.remove(customer);
                return ResponseEntity.ok(HttpStatus.NO_CONTENT);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found: " + id);
    }
}
