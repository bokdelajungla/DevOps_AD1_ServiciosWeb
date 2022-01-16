package es.webService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevOpsAd1WebServiceApplication {

	public static void main(String[] args) {
		System.out.println("Servicio REST -> Cargando el contexto de Spring...");
		SpringApplication.run(DevOpsAd1WebServiceApplication.class, args);
		System.out.println("Servicio REST -> Contexto de Spring cargado");
	}

}
