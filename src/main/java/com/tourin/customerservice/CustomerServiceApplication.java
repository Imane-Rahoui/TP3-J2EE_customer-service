package com.tourin.customerservice;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.tourin.customerservice.entities.Customer;
import com.tourin.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean // pr que ça s exec au demarrage
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){

		restConfiguration.exposeIdsFor(Customer.class);
		return args -> {
			customerRepository.save(new Customer(null,"Imane","imi@gmail.com"));
			customerRepository.save(new Customer(null,"Touria","tou@gmail.com"));
			customerRepository.save(new Customer(null,"Amine","amine@gmail.com"));
			customerRepository.findAll().forEach(
					c->{
						System.out.println(c.toString());
					}
			);
		};
	}
}
