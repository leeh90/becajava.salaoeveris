package br.salaoeveris.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.salaoeveris.app.request.AgendamentoList;
import br.salaoeveris.app.request.AgendamentoRequest;
import br.salaoeveris.app.response.AgendamentoResponse;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController extends BaseController {

	final AgendamentoService _service; // A variavel " _service " herda toda a classe ClienteService com seus metodos

	@Autowired
	public AgendamentoController(AgendamentoService service) {
		_service = service; // Obrigando que a Classe ClienteService seja instanciada junto com a Classe
							// ClienteController via variavel _service
	}

	@PostMapping
	public ResponseEntity inserir(@RequestBody AgendamentoRequest agendamentoRequest) {
		try {
			BaseResponse response = _service.inserir(agendamentoRequest);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);

		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity obter(@PathVariable Long id) {
		try {
			AgendamentoResponse response = _service.obter(id);
			return ResponseEntity.status(response.statusCode).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

	@GetMapping
	public ResponseEntity listar() {
		try {
			AgendamentoList agendamentos = _service.listar();
			return ResponseEntity.status(HttpStatus.OK).body(agendamentos);
		} catch (Exception e) {
			return ResponseEntity.status(errorBase.statusCode).body(errorBase);
		}
	}

}