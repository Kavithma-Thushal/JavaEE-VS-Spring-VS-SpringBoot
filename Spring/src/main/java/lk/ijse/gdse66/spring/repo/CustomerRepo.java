package lk.ijse.gdse66.spring.repo;

import lk.ijse.gdse66.spring.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-MySQL-CRUD
 * @since : 7:35 AM - 6/18/2024
 **/
public interface CustomerRepo extends JpaRepository<CustomerEntity, String> {
}
