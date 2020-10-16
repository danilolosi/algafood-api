package com.danilolosi.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErrorType {
	
	ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada", "/entidade-nao-encontrada"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreensivel"),
	PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido");
	
	private String title;
	private String uri;
	
	ErrorType(String title, String path){
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

}
