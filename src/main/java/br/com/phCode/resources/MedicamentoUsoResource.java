package br.com.phCode.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.phCode.dto.CidadeDTO;
import br.com.phCode.dto.EstadoDTO;
import br.com.phCode.services.MedicamentoUsoService;

@RestController
@RequestMapping(value="/medicamentosUso")
public class MedicamentoUsoResource {

	@Autowired
	private MedicamentoUsoService service;


	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
//		List<MedicamentoUso> list = service.findAll();
//		List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());  
//		return ResponseEntity.ok().body(listDto);
		return null;
	}

	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
//		List<Cidade> list = cidadeService.findByEstado(estadoId);
//		List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());  
//		return ResponseEntity.ok().body(listDto);
		return null;		
	}
}