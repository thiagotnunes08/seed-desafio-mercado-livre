package br.com.desafio.deveficiente.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MercadoLivreApplication {
	public static void main(String[] args) {
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

}
