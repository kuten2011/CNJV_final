package CNJV.lab10;

import CNJV.lab10.Service.ClientService;
import CNJV.lab10.Service.ProductService;
import CNJV.lab10.Service.UserService;
import CNJV.lab10.model.Client;
import CNJV.lab10.model.Product;
import CNJV.lab10.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Lab10Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab10Application.class, args);
	}

	@Bean
	CommandLineRunner runner(ProductService productService, UserService userService, ClientService clientService) {
		return args -> {
			User user = new User("khoa", "lolo", "khoa", "0944", "khoavo006@gmail.com", 1, true);
			userService.update(user);

			Client client = new Client("khoa", "0944", "khoavo006@gmail.com", user);
			clientService.saveClient(client);

			// Tạo danh sách chứa 10 sản phẩm
			List<Product> products = Arrays.asList(
					new Product("TV Set 1", 1, 1000.0, "Area 1", "Duc Hoa, Long An", 200000.0, LocalDate.now(), client),
					new Product("TV Set 2", 0, 1200.0, "Area 2", "Tan An, Long An", 250000.0, LocalDate.now(), client),
					new Product("TV Set 3", 0, 1100.0, "Area 3", "My Tho, Tien Giang", 220000.0, LocalDate.now(), client),
					new Product("TV Set 4", 1, 1050.0, "Area 4", "Can Tho, Can Tho", 240000.0, LocalDate.now(), client),
					new Product("TV Set 5", 0, 1300.0, "Area 5", "Vinh Long, Vinh Long", 210000.0, LocalDate.now(), client),
					new Product("TV Set 6", 1, 1150.0, "Area 6", "Tra Vinh, Tra Vinh", 230000.0, LocalDate.now(), client),
					new Product("TV Set 7", 1, 1080.0, "Area 7", "Ben Tre, Ben Tre", 260000.0, LocalDate.now(), client),
					new Product("TV Set 8", 0, 1250.0, "Area 8", "Long Xuyen, An Giang", 270000.0, LocalDate.now(), client),
					new Product("TV Set 9", 1, 1120.0, "Area 9", "Rach Gia, Kien Giang", 280000.0, LocalDate.now(), client),
					new Product("TV Set 10", 0, 1180.0, "Area 10", "Ca Mau, Ca Mau", 290000.0, LocalDate.now(), client)
			);

			for (Product product : products) {
				productService.save(product);
			}

			Iterable<Product> listProducts = productService.getAllProduct();
		};
	}

}
