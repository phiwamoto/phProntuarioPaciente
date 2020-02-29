package br.com.phCode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.phCode.models.Pessoa;
import br.com.phCode.repositories.PessoaRepository;
import br.com.phCode.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Pessoa pessoa = repo.findByEmail(email);
		if (pessoa == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSS(pessoa.getId(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getPerfis());
		
//		Set<Integer> perfis = new HashSet();
//		perfis.add(Perfil.ADMIN.getCod());
//		return new UserSS(1, "phiwamoto@hotmail.com", "123", perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()));
	}
}
