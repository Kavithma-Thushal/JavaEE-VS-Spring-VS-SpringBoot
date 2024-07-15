package lk.ijse.gdse66.spring.config;

import lk.ijse.gdse66.spring.advisor.GlobalExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author : Kavithma Thushal
 * @project : Spring
 * @since : 4:50 PM - 7/15/2024
 **/
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {GlobalExceptionHandler.class}, basePackages = "lk.ijse.gdse66.spring.api")
public class WebAppConfig {
}