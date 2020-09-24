package com.danilolosi.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danilolosi.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.danilolosi.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.danilolosi.algafoodapi.domain.model.Estado;
import com.danilolosi.algafoodapi.domain.repository.EstadoRepository;
import com.danilolosi.algafoodapi.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> listar(){
        List<Estado> estados = estadoRepository.listar();
        return ResponseEntity.ok(estados);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id){
    	
    	Estado estado = estadoRepository.buscar(id);
    	
    	if(estado != null) {
    		return ResponseEntity.ok(estado);
    	}
    	
    	return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
    	
    	estado = estadoService.salvar(estado);
    	return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado){
    	
    	Estado estadoAtual = estadoRepository.buscar(id);
    	
    	if(estadoAtual != null) {
    		
    		BeanUtils.copyProperties(estado, estadoAtual, "id");
    		estadoAtual = estadoService.salvar(estadoAtual);
    		return ResponseEntity.ok(estadoAtual);
    	}
    	
    	return ResponseEntity.notFound().build();
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
    	
    	try {
    		estadoService.remover(id);
    		return ResponseEntity.noContent().build();
    		
    	}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}
    	
    }
}
