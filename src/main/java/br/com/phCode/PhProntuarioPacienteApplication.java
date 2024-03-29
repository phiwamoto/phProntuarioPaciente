package br.com.phCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.phCode.models.Pessoa;
import br.com.phCode.repositories.PessoaRepository;

@CrossOrigin(origins  = "http://localhost:8080")
@SpringBootApplication
public class PhProntuarioPacienteApplication implements CommandLineRunner {

	@Autowired
	private PessoaRepository pessoaRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(PhProntuarioPacienteApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Pessoa pessoa = new Pessoa(null, "Pedro Henrique Iwamoto", "phiwamoto@hotmail.com", "06439573918", "$2a$10$PVuKdFq/lV9xfBaFGLaXx.STb3GGu90LHNvd6Ps94gHJEFUKmHtey");
		pessoaRepo.save(pessoa);
		
		
		// localhost:8080/login
		/*

			{
				"email": "phiwamoto@hotmail.com",
				"senha": "123"
			}

		 */
	}		

}
