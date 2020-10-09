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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
        List<Estado> estados = estadoRepository.findAll();
        return ResponseEntity.ok(estados);
    }
    
    @GetMapping("/{id}")
    public Estado buscar(@PathVariable Long id){
    	return estadoService.buscarOuFalhar(id);
    }
    
    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
    	
    	estado = estadoService.salvar(estado);
    	return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }
    
    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado){
    	
    	Estado estadoAtual = estadoService.buscarOuFalhar(id);
    	BeanUtils.copyProperties(estado, estadoAtual, "id");
    	return estadoService.salvar(estadoAtual); 	
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
    	estadoService.remover(id);
    }
}
