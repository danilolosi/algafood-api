package com.danilolosi.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.danilolosi.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.danilolosi.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.danilolosi.algafoodapi.domain.model.Estado;
import com.danilolosi.algafoodapi.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	
	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public void remover(Long id) {
	
		try {
			estadoRepository.remover(id);
			
		}catch (EmptyResultDataAccessException e) {	
			throw new EntidadeNaoEncontradaException(
					String.format("Estado com id: %d, não foi encontrado", id));
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Não foi possível excluir o estado com id: %d, pois está em uso", id));
		}
	}
	
}
