package br.com.phCode.resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

//	@Autowired
//	private PessoaService service;
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Pessoa> find(@PathVariable Integer id) {
//		Pessoa obj = service.find(id);
//		return ResponseEntity.ok().body(obj);
//	}
//	
//	@RequestMapping(value="/email", method=RequestMethod.GET)
//	public ResponseEntity<Pessoa> find(@RequestParam(value="value") String email) {
//		Pessoa obj = service.findByEmail(email);
//		return ResponseEntity.ok().body(obj);
//	}	
//	
////	@RequestMapping(method=RequestMethod.POST)
////	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) { 
////		Pessoa obj = service.fromDTO(objDto);
////		obj = service.insert(obj);
////		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
////				.path("/{id}").buildAndExpand(obj.getId()).toUri();
////		
////		return ResponseEntity.created(uri).build();
////	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Void> update(@Valid @RequestBody PessoaDTO objDto, @PathVariable Integer id) {
//		Pessoa obj = service.fromDTO(objDto);
//		obj.setId(id); // garante que será alterado o objeto do ID
//		obj = service.update(obj);
//		
//		return ResponseEntity.noContent().build();
//		
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN')")	
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> delete(@PathVariable Integer id) {
//		service.delete(id);
//		return ResponseEntity.noContent().build();
//	}
//
//	@PreAuthorize("hasAnyRole('ADMIN')")	
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<List<PessoaDTO>> findAll() {
//		List<Pessoa> list = service.findAll();
//		List<PessoaDTO> listDto = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
//		return ResponseEntity.ok().body(listDto);
//	}
//	
//	@PreAuthorize("hasAnyRole('ADMIN')")	
//	@RequestMapping(value="/page", method = RequestMethod.GET)
//	public ResponseEntity<Page<PessoaDTO>> findPage(
//			@RequestParam(value="page", defaultValue="0") Integer page, 
//			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
//			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
//			@RequestParam(value="direction", defaultValue="ASC")String direction) {
//		Page<Pessoa> list = service.findPage(page, linesPerPage, orderBy, direction);
//		Page<PessoaDTO> listDto = list.map(obj -> new PessoaDTO(obj));
//		return ResponseEntity.ok().body(listDto);
//	}	
}
