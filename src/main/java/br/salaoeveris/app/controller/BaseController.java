package br.salaoeveris.app.controller;

import br.salaoeveris.app.response.BaseResponse;

public class BaseController {
	
	public BaseResponse errorBase = new BaseResponse();
	
	public BaseController() {
		errorBase.statusCode = 500;
		errorBase.message = "ERRO 500: Ocorreu um erro inesperado. Contate o Administrador.";
	}

}
