package br.com.phCode.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phCode.models.Endereco;
import br.com.phCode.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public List<Endereco> findAll() {
		return repo.findAll();
	}
}