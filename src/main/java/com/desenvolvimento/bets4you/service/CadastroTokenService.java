package com.desenvolvimento.bets4you.service;

import com.desenvolvimento.bets4you.model.TokenValidation;
import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.repository.Tokens;
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
