package br.com.phCode.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.phCode.dto.PessoaDTO;
import br.com.phCode.dto.PessoaNewDTO;
import br.com.phCode.exception.AuthorizationException;
import br.com.phCode.exception.ObjectNotFoundException;
import br.com.phCode.models.Cidade;
import br.com.phCode.models.Perfil;
import br.com.phCode.models.Pessoa;
import br.com.phCode.repositories.PessoaRepository;
import br.com.phCode.security.UserSS;

public class PessoaService {

	// Injeção de dependencia ou inversão de controle
	@Autowired
	private PessoaRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Pessoa find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Pessoa obj = repo.findById(id).get();
		
		if (obj == null) 
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName());
			
		return obj;
	}
	
	@Transactional
	public Pessoa insert(Pessoa obj) {
		obj.setId(null); // garante que será criação de objeto novo
		repo.save(obj);
//		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}

	public Pessoa update(Pessoa obj) {
		Pessoa newObj = find(obj.getId());
//		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		Pessoa Pessoa = find(id);
		try {
			repo.delete(Pessoa);
		} catch (DataIntegrityViolationException e) {
			//throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas");
		}
	}

	public List<Pessoa> findAll() {
		return repo.findAll();
	}
	
	public Pessoa findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Pessoa obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Pessoa.class.getName());
		}
		return obj;
	}
	
	public Page<Pessoa> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Pessoa fromDTO(PessoaDTO objDto) {
		return new Pessoa(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null); 
	}

	public Pessoa fromDTO(PessoaNewDTO objDto) {
		Pessoa cli = new Pessoa(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), pe.encode(objDto.getSenha()));

//		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
//		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), 
//				objDto.getCep(), cli, cid);
//		
//		cli.getEnderecos().add(end);
//		cli.getTelefones().add(objDto.getTelefone1());
//		if (objDto.getTelefone2() != null)
//			cli.getTelefones().add(objDto.getTelefone2());		
//
//		if (objDto.getTelefone3() != null)
//			cli.getTelefones().add(objDto.getTelefone3());		
		
		return cli; 
	}
	
//	private void updateData(Pessoa newObj, Pessoa obj) {
//		newObj.setNome(obj.getNome());
//		newObj.setEmail(obj.getEmail());
//	}	
//	
//	public URI uploadProfilePicture(MultipartFile multipartFile) {
//		UserSS user = UserService.authenticated();
//		if (user == null) {
//			throw new AuthorizationException("Acesso negado");
//		}	
//		
//		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
//		jpgImage = imageService.cropSquare(jpgImage);
//		jpgImage = imageService.resize(jpgImage, size);
//		
//		String fileName = prefix + user.getId() + ".jpeg";
//
//		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpeg"), fileName, "image");
//	}	
}	