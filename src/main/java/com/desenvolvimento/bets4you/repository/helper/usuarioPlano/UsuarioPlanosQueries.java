package com.desenvolvimento.bets4you.repository.helper.usuarioPlano;

import com.desenvolvimento.bets4you.model.UsuarioPlano;

public interface UsuarioPlanosQueries {

    public void atualizarStatusUsuarioPlanoParaVencido(UsuarioPlano usuarioPlano);

    void atualizarStatusUsuarioPlanoParaAtivo(UsuarioPlano usuarioPlano);
}
