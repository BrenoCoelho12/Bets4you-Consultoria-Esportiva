package com.desenvolvimento.bets4you.repository.helper.usuarioPlano;

import com.desenvolvimento.bets4you.model.UsuarioPlano;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsuarioPlanosImpl implements UsuarioPlanosQueries{

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public void atualizarStatusUsuarioPlanoParaVencido(UsuarioPlano usuarioPlano) {
        manager.createQuery("UPDATE UsuarioPlano SET status = 'VENCIDO' WHERE id = :id")
                .setParameter("id", usuarioPlano.getId())
                .executeUpdate();
    }

    @Override
    public void atualizarStatusUsuarioPlanoParaAtivo(UsuarioPlano usuarioPlano) {
        manager.createQuery("UPDATE UsuarioPlano SET status = 'ATIVO' WHERE id = :id")
                .setParameter("id", usuarioPlano.getId())
                .executeUpdate();
    }

}
