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
	
	private static final String MSG_ESTADO_EM_USO = "Não foi possível excluir o estado com id: %d, pois está em uso";
	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Estado com id: %d, não foi encontrado";
	@Autowired
	private EstadoRepository estadoRepository;

	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void remover(Long id) {
	
		try {
			estadoRepository.deleteById(id);
			
		}catch (EmptyResultDataAccessException e) {	
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, id));
		}
	}
	
	public Estado buscarOuFalhar(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}
	
	
}
