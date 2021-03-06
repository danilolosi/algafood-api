package com.danilolosi.algafoodapi.domain.repository;

import com.danilolosi.algafoodapi.domain.model.Permissao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRespository {

    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
}
