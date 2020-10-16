package com.danilolosi.algafoodapi.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErrorType {
	
	RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	ERRO_NEGOCIO("Violação de regra de negócio", "/erro-negocio"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível", "/mensagem-incompreensivel"),
	PARAMETRO_INVALIDO("Parâmetro inválido", "/parametro-invalido"),
	ERRO_DE_SISTEMA("Erro de sistema", "/erro-de-sistema");
	
	private String title;
	private String uri;
	
	ErrorType(String title, String path){
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

}
