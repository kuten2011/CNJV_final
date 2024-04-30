package CNJV.lab10;

import CNJV.lab10.Service.OderProductService;
import CNJV.lab10.Service.OderService;
import CNJV.lab10.Service.ProductService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Lab10Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab10Application.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService, OderService oderService, OderProductService oderProductService, UserService userService) {
		return args -> {
			productService.save(new Product("TV Set 1", 300.00, "Samsung", "Black"));
			productService.save(new Product("TV Set 2", 350.00, "Sony", "Silver"));
			productService.save(new Product("Phone 1", 500.00, "Apple", "White"));
			productService.save(new Product("Phone 2", 450.00, "Samsung", "Blue"));
			productService.save(new Product("Laptop 1", 1200.00, "Dell", "Silver"));
			productService.save(new Product("Laptop 2", 1500.00, "HP", "Black"));
			productService.save(new Product("Tablet 1", 400.00, "Lenovo", "Grey"));
			productService.save(new Product("Tablet 2", 300.00, "Samsung", "White"));
			productService.save(new Product("Speaker 1", 100.00, "JBL", "Red"));
			productService.save(new Product("Speaker 2", 80.00, "Sony", "Black"));
		};
	}

}
