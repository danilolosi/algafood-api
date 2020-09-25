package com.danilolosi.algafoodapi.domain.repository;

import com.danilolosi.algafoodapi.domain.model.Cidade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	
}
