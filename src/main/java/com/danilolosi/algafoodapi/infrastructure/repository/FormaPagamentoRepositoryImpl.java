package com.danilolosi.algafoodapi.infrastructure.repository;

import com.danilolosi.algafoodapi.domain.model.FormaPagamento;
import com.danilolosi.algafoodapi.domain.repository.FormaPagamentoRespository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRespository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> listar() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento buscar(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }

    @Override
    public void remover(FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoFind = buscar(formaPagamento.getId());
        manager.remove(formaPagamentoFind);
    }
}
