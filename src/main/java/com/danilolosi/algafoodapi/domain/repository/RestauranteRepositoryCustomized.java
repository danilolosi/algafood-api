package com.danilolosi.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.danilolosi.algafoodapi.domain.model.Restaurante;

public interface RestauranteRepositoryCustomized {
	
	//Exemplo de consulta com uma implementação desta interface feita pelo Spring Data JPA
		List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
		
		List<Restaurante> findComFreteGratis(String nome);

}