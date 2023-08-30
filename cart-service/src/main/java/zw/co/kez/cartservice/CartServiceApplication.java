package zw.co.kez.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}
	@Configuration
	public static class RestTemplateConfiguration {
		@Bean
		@LoadBalanced
		public RestTemplate registerRestTemplate() {
			return new RestTemplate();
		}
	}
}
