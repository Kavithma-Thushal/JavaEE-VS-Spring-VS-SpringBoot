package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.entity.CustomerEntity;
import lk.ijse.gdse66.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring
 * @since : 4:50 PM - 7/15/2024
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerAPI {

    @Autowired
    public CustomerService customerService;

    @PostMapping("/saveCustomer")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
    }

    @GetMapping("/searchCustomer/{id}")
    public CustomerDTO searchCustomer(@PathVariable String id) {
        return customerService.searchCustomer(id);
    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/loadAllCustomers")
    public List<CustomerEntity> loadAllCustomers() {
        return customerService.loadAllCustomers();
    }
}
