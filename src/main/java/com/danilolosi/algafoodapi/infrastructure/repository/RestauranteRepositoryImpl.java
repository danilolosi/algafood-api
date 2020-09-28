package com.danilolosi.algafoodapi.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.danilolosi.algafoodapi.domain.model.Restaurante;
import com.danilolosi.algafoodapi.domain.repository.RestauranteRepositoryCustomized;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomized {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		
		var jpql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<>();
		
		jpql.append("from Restaurante where 0 = 0 ");
		
		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		
		if(taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaFreteInicial ");
			parametros.put("taxaFreteInicial", taxaFreteInicial);
		}
		
		if(taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFreteFinal");
			parametros.put("taxaFreteFinal", taxaFreteFinal);
		}
		
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
		
		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
		
		return query.getResultList();
	}
	
	
}
