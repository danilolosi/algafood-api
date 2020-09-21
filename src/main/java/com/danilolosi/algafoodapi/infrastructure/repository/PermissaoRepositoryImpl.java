package com.danilolosi.algafoodapi.infrastructure.repository;

import com.danilolosi.algafoodapi.domain.model.Permissao;
import com.danilolosi.algafoodapi.domain.repository.PermissaoRespository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRespository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> listar() {
        return manager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao buscar(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Override
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    public void remover(Permissao permissao) {
        Permissao permissaoFind = buscar(permissao.getId());
        manager.remove(permissaoFind);
    }
}
