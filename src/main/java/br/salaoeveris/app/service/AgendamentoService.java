package br.salaoeveris.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.salaoeveris.app.model.Agendamento;
import br.salaoeveris.app.repository.AgendamentoRepository;
import br.salaoeveris.app.request.AgendamentoList;
import br.salaoeveris.app.request.AgendamentoRequest;
import br.salaoeveris.app.response.AgendamentoResponse;
import br.salaoeveris.app.response.BaseResponse;

@Service
public class AgendamentoService {

	final AgendamentoRepository _repository;

	@Autowired
	public AgendamentoService(AgendamentoRepository repository) {
		_repository = repository;
	}

	public BaseResponse inserir(AgendamentoRequest agendamentoRequest) {
		Agendamento agendamento = new Agendamento();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (agendamentoRequest.getDataHora().equals("")) {
			base.message = "Campo data/Hora não foi preenchidos.";
			return base;
		}

		if (agendamentoRequest.getCliente().equals("")) {
			base.message = "Dados do cliente não foram preenchidos.";
			return base;
		}
		if (agendamentoRequest.getServico().equals("")) {
			base.message = "Dados do serviço não foram preenchidos.";
			return base;
		}

		agendamento.setDataHora(agendamentoRequest.getDataHora());
		agendamento.setCliente(agendamentoRequest.getCliente());
		agendamento.setServico(agendamentoRequest.getServico());

		_repository.save(agendamento);
		base.message = "Agendamento inserido com sucesso.";
		return base;

	}

	public AgendamentoResponse obter(Long id) {
		Optional<Agendamento> agendamento = _repository.findById(id);
		AgendamentoResponse agendamentoResponse = new AgendamentoResponse();

		if (agendamento == null) {
			agendamentoResponse.statusCode = 404;
			agendamentoResponse.message = "Agendamento não encontrado.";
			return agendamentoResponse;
		}

		agendamentoResponse.setDataHora(agendamento.get().getDataHora());
		agendamentoResponse.setCliente(agendamento.get().getCliente());
		agendamentoResponse.setServico(agendamento.get().getServico());

		agendamentoResponse.message = "Agendamento obtido com sucesso.";
		agendamentoResponse.statusCode = 200;

		return agendamentoResponse;

	}

	public AgendamentoList listar() {

		List<Agendamento> lista = _repository.findAll();

		AgendamentoList response = new AgendamentoList();
		response.setAgendamentos(lista);
		response.statusCode = 200;
		response.message = "Agendamentos obtidos com sucesso.";

		return response;
	}

}
