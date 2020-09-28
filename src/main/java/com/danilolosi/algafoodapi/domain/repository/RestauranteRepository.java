package com.danilolosi.algafoodapi.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danilolosi.algafoodapi.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryCustomized {
	
	

	//Exempo de JPQL com @Query
	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultaPorNome(String nome, @Param("id") Long cozinhaId);
	
	//Exemplo de query methods
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	List<Restaurante> findTop2ByNomeContaining(String nome);
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
	boolean existsByNome(String nome);
	int countByCozinhaId(Long cozinhaId);
	
}
