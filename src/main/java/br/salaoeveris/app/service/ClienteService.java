package br.salaoeveris.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import br.salaoeveris.app.request.ClienteList;
import br.salaoeveris.app.model.Cliente;
import br.salaoeveris.app.repository.ClienteRepository;
import br.salaoeveris.app.request.ClienteRequest;
import br.salaoeveris.app.response.BaseResponse;
import br.salaoeveris.app.response.ClienteResponse;

@Service
public class ClienteService {

	// Metodos: Inserir, Obter e Listar

	final ClienteRepository _repository;

	@Autowired
	public ClienteService(ClienteRepository repository) {
		_repository = repository;
	}

	public BaseResponse inserir(ClienteRequest clienteRequest) {
		Cliente cliente = new Cliente();
		BaseResponse base = new BaseResponse();
		base.statusCode = 400;

		if (clienteRequest.getNome() == "" || clienteRequest.getTelefone() == ""
				|| clienteRequest.getEndereco() == "") {
			base.message = "Os Campos obrigatórios não foram preenchidos.";
			return base;
		}

		cliente.setNome(clienteRequest.getNome());
		cliente.setTelefone(clienteRequest.getTelefone());
		cliente.setEndereco(clienteRequest.getEndereco());

		_repository.save(cliente);
		base.statusCode = 200;
		base.message = "Dados inserido com sucesso!";

		return base;

	}

	public ClienteResponse obter(Long id) {
		Optional<Cliente> cliente = _repository.findById(id);
		ClienteResponse clienteResponse = new ClienteResponse();
		
		if(cliente == null) {
			clienteResponse.statusCode = 404;
			clienteResponse.message ="Cliente não encontrado.";
			return clienteResponse;
		}
		
		clienteResponse.setNome(cliente.get().getNome());
		clienteResponse.setTelefone(cliente.get().getTelefone());
		clienteResponse.setEndereco(cliente.get().getEndereco());
		
		clienteResponse.message = "Cliente obtido com sucesso.";
		clienteResponse.statusCode = 200;
	
	return clienteResponse;
	
	}
	
	public ClienteList listar() {

		List<Cliente> lista = _repository.findAll();

		ClienteList response = new ClienteList();
		response.setClientes(lista);
		response.statusCode = 200;
		response.message = "Clientes obtidos com sucesso.";

		return response;
	}

}
