package com.danilolosi.algafoodapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilolosi.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.danilolosi.algafoodapi.domain.model.Cidade;
import com.danilolosi.algafoodapi.domain.repository.CidadeRepository;
import com.danilolosi.algafoodapi.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listar(){
		List<Cidade> cidades = cidadeRepository.findAll();
		return ResponseEntity.ok(cidades);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscar(@PathVariable Long id){
		
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		
		if(cidade.isPresent()){
			return ResponseEntity.ok(cidade.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cidade cidade){
		
		try {
			cidade = cidadeService.salvar(cidade);
			return ResponseEntity.ok(cidade);
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
		
		try {
			
			Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
			
			if(cidadeAtual.isPresent()) {
				
				BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
				Cidade cidadeSalva = cidadeService.salvar(cidadeAtual.get());
				return ResponseEntity.ok(cidadeSalva);
			}
			
			return ResponseEntity.notFound().build();
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		try {
			cidadeService.remover(id);
			return ResponseEntity.noContent().build();
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
}
