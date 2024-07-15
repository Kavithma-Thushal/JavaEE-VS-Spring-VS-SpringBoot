package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.entity.CustomerEntity;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-MySQL-CRUD
 * @since : 7:24 AM - 6/18/2024
 **/
public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    CustomerDTO searchCustomer(String id);

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String id);

    List<CustomerEntity> loadAllCustomers();
}
