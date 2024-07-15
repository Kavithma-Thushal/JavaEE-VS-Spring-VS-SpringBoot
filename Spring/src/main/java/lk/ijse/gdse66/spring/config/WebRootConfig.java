package lk.ijse.gdse66.spring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : Kavithma Thushal
 * @project : Spring
 * @since : 4:50 PM - 7/15/2024
 **/
@Configuration
@Import(JPAConfig.class)
@ComponentScan(basePackages = "lk.ijse.gdse66.spring.service")
public class WebRootConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}