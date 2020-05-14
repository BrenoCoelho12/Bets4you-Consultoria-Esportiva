package com.desenvolvimento.pibetting.service;

import com.desenvolvimento.pibetting.model.UsuarioPlano;
import com.desenvolvimento.pibetting.repository.UsuarioPlanos;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioPlanoService {

    private UsuarioPlanos usuarioPlanos;

    public void cadastrar(UsuarioPlano usuarioPlano){
        usuarioPlano.getTerminoPlano().plusMonths(usuarioPlano.getPlano().getDuracao()); //incrementando o tempo de duração do plano
        usuarioPlanos.save(usuarioPlano);
    }
}
