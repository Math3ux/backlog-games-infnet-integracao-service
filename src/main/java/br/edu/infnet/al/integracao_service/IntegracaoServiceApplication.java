package br.edu.infnet.al.integracao_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IntegracaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegracaoServiceApplication.class, args);
	}

}
