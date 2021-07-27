package br.com.phCode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phCode.models.MedicamentoQuantidade;
import br.com.phCode.repositories.MedicamentoQuantidadeRepository;

@Service
public class MedicamentoQuantidadeService {

	@Autowired
	private MedicamentoQuantidadeRepository repo;

	public List<MedicamentoQuantidade> findAll() {
		return repo.findAll();
	}
}