package com.proyecto.b.s;

import com.proyecto.b.s.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		ApplicationContext context =  SpringApplication.run(Application.class, args);
		UserRepository repository= context.getBean(UserRepository.class);


	}

}
