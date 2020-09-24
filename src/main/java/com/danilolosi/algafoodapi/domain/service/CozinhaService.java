package com.danilolosi.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.danilolosi.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.danilolosi.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.danilolosi.algafoodapi.domain.model.Cozinha;
import com.danilolosi.algafoodapi.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
	
	public void remover(Long id) {
		try {
			cozinhaRepository.remover(id);
			
		}catch (EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(
					String.format("Cozinha de código %d não foi encontrada", id));
			
		}catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
            		String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
	}
	
}
