package com.danilolosi.algafoodapi.domain.repository;

import com.danilolosi.algafoodapi.domain.model.Estado;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{


}
