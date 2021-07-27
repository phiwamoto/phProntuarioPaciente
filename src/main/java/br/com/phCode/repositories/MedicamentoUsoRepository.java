package br.com.phCode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.phCode.models.MedicamentoUso;

@Repository
public interface MedicamentoUsoRepository extends JpaRepository<MedicamentoUso, Integer> {

	@Transactional(readOnly=true)
	public List<MedicamentoUso> findAllByOrderByDescricao();	
	
}