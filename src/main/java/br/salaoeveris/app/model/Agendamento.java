package br.salaoeveris.app.model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataHora;
	@ManyToOne
	@JoinColumn(name = "clienteId")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "servicoId")
	private Servico servico;

	public Long getId() {
		return id;

	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

}
