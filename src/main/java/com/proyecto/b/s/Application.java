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


	//	Usuario usuario1 = new Usuario(null, "mcarlaalexia@gmail.com", "1234", "Carla", "Marquez");
	//	Usuario usuario2 = new Usuario (null, "maxsauerbrey@gmail.com","12345","Max","Sauerbrey");
     //	Usuario usuario3 = new Usuario (null, "lalal@gmail.com","12345","La","Lala");

	//	repository.save(usuario1);
		//repository.save(usuario2);
		//repository.save(usuario3);
	}

}
