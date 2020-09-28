package com.danilolosi.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.danilolosi.algafoodapi.domain.model.Restaurante;
import com.danilolosi.algafoodapi.domain.repository.RestauranteRepositoryCustomized;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomized {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
		var jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaInicial and :taxaFinal";
		
		return manager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%" + nome + "%" )
				.setParameter("taxaInicial", taxaFreteInicial)
				.setParameter("taxaFinal", taxaFreteFinal)
				.getResultList();
	}
	
	
}
