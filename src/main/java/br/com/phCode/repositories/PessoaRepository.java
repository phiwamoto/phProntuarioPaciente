package br.com.phCode.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.phCode.models.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Pessoa findById(int id);
	
	@Transactional(readOnly=true)
	Pessoa findByEmail(String email);
}
