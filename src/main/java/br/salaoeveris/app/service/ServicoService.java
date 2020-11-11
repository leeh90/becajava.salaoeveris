package br.salaoeveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Servico;
import br.salaoeveris.app.repository.ServicoRepository;
import br.salaoeveris.app.request.ServicoList;
import br.salaoeveris.app.request.ServicoRequest;
import br.salaoeveris.app.response.*;

@Service 
public class ServicoService {

	// Metodos: Inserir, Obter e Listar

	final ServicoRepository _repository;

	@Autowired
	public ServicoService(ServicoRepository repository) {
		_repository = repository;
	}

	public BaseResponse inserir(ServicoRequest servicoRequest) {
		Servico servico = new Servico();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (servicoRequest.getNome() == "") {
			base.message = "O nome obrigatórios não foram preenchidos.";
			return base;
		}
		if (servicoRequest.getValor() == 0) {
			base.message = "O valor obrigatório não foi preenchido.";
			return base;
		}

		servico.setNome(servicoRequest.getNome());
		servico.setValor(servicoRequest.getValor());
		_repository.save(servico);

		base.statusCode = 200;
		base.message = "Serviço inserido com sucesso!";

		return base;

	}

	public ServicoResponse obter(Long id) {
		Optional<Servico> servico = _repository.findById(id);
		ServicoResponse servicoResponse = new ServicoResponse();

		if (servico == null) {
			servicoResponse.statusCode = 404;
			servicoResponse.message = "Cliente não encontrado.";
			return servicoResponse;
		}

		servicoResponse.setNome(servico.get().getNome());
		servicoResponse.setValor(servico.get().getValor());

		servicoResponse.message = "Cliente obtido com sucesso.";
		servicoResponse.statusCode = 200;

		return servicoResponse;

	}

	public ServicoList listar() {

		List<Servico> lista = _repository.findAll();

		ServicoList servicoResponse = new ServicoList();
		servicoResponse.setServico(lista);

		servicoResponse.statusCode = 200;
		servicoResponse.message = "Clientes obtidos com sucesso.";

		return servicoResponse;
	}

}
