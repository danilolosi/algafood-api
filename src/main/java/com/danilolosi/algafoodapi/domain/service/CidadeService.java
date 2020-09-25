package com.danilolosi.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.danilolosi.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.danilolosi.algafoodapi.domain.model.Cidade;
import com.danilolosi.algafoodapi.domain.model.Estado;
import com.danilolosi.algafoodapi.domain.repository.CidadeRepository;
import com.danilolosi.algafoodapi.domain.repository.EstadoRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadorepository;
	
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadorepository
				.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Estado com id: %d não foi encontrado", estadoId)));
		
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}
	
	public void remover(Long id) {
		
		try {
			cidadeRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade com id: %d não existe", id));
		}
		
	}

}
