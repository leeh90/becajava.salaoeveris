package br.salaoeveris.app.response;

public class ServicoResponse extends BaseResponse {

	private String nome;
	private int valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
