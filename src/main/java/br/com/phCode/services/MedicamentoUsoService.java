package br.com.phCode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phCode.models.MedicamentoUso;
import br.com.phCode.repositories.MedicamentoUsoRepository;

@Service
public class MedicamentoUsoService {

	@Autowired
	private MedicamentoUsoRepository repo;

	public List<MedicamentoUso> findAll() {
		return repo.findAllByOrderByDescricao();
	}
}