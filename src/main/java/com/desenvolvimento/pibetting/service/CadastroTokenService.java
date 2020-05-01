package com.desenvolvimento.pibetting.service;

import com.desenvolvimento.pibetting.model.TokenValidation;
import com.desenvolvimento.pibetting.model.Usuario;
import com.desenvolvimento.pibetting.repository.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CadastroTokenService {

    @Autowired
    private Tokens tokens;

    @Transactional
    public String newToken(Usuario usuario) {
        TokenValidation token = new TokenValidation();

        String codigoToken = UUID.randomUUID().toString();
        token.setToken(codigoToken);
        token.setUsuario(usuario);

        tokens.save(token);

        return codigoToken;

    }

}
