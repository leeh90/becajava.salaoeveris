package br.salaoeveris.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.salaoeveris.app.request.ServicoList;
import br.salaoeveris.app.request.ServicoRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.response.ServicoResponse;
import br.salaoeveris.app.service.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController extends BaseController {

	final ServicoService _service;// A variavel " _service " herda toda a classe ClienteService com seus metodos

	public ServicoController(ServicoService service) {
		_service = service;// Obrigando que a Classe ClienteService seja instanciada junto com a Classe
		// ClienteController via variavel _service
	}

	@PostMapping
	public ResponseEntity inserir(@RequestBody ServicoRequest servicoRequest) {
		try {
			BaseResponse response = _service.inserir(servicoRequest);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);

		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity obter(@PathVariable Long id) {
		try {
			ServicoResponse response = _service.obter(id);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

	@GetMapping
	public ResponseEntity listar() {
		try {
			ServicoList servicos = _service.listar();
			return ResponseEntity.status(HttpStatus.OK).body(servicos);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

}
