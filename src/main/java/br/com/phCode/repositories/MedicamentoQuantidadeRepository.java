package br.com.phCode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.phCode.models.MedicamentoQuantidade;

@Repository
public interface MedicamentoQuantidadeRepository extends JpaRepository<MedicamentoQuantidade, Integer> {

	@Transactional(readOnly=true)
	public List<MedicamentoQuantidade> findAllByOrderByDescricao();	

	MedicamentoQuantidade findById(int id);
}