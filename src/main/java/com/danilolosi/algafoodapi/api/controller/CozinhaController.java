package com.danilolosi.algafoodapi.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.danilolosi.algafoodapi.domain.model.Cozinha;
import com.danilolosi.algafoodapi.domain.repository.CozinhaRepository;
import com.danilolosi.algafoodapi.domain.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    
    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id){
    	return cozinhaService.buscarOuFalhar(id);
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody @Valid Cozinha cozinha){
    	Cozinha novaCozinha = cozinhaService.salvar(cozinha);
    	return ResponseEntity.status(HttpStatus.CREATED).body(novaCozinha);
    }

    @PutMapping("/{id}")
    public Cozinha atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha){
    	
    	Cozinha cozinhaAtual = cozinhaService.buscarOuFalhar(id);
    	
    	BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
    	return cozinhaService.salvar(cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long id){
        cozinhaService.remover(id);
    }
}
