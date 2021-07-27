package br.com.phCode.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.phCode.models.MedicamentoQuantidade;
import br.com.phCode.services.MedicamentoQuantidadeService;

@RestController
@RequestMapping(value="/medicamentosUso")
public class MedicamentoQuantidadeResource {

	@Autowired
	private MedicamentoQuantidadeService service;


//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<List<MedicamentoQuantidade>> findAll() {
//		List<MedicamentoQuantidade> list = service.findAll();
//		//List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());  
//		return ResponseEntity.ok().body(list);
//		//return null;
//	}

//	@RequestMapping(value="/medicamentosquantidade", method=RequestMethod.GET)
//	public ResponseEntity<Optional<MedicamentoQuantidade>> findMedicamentoQuantidade(@PathVariable Integer medicamentoQuantidadeId) {
//		Optional<MedicamentoQuantidade> medicamentoQuantidade = service.findById(medicamentoQuantidadeId);
//		return ResponseEntity.ok().body(medicamentoQuantidade);
//	}
}