package com.aluracursos.desafio_literalura;

import com.aluracursos.desafio_literalura.principal.Principal;
import com.aluracursos.desafio_literalura.repositorio.ILibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private ILibrosRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraluraApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.muestraElMenu();
	}
}
