package com.desenvolvimento.pibetting.service;

import com.desenvolvimento.pibetting.model.Plano;
import com.desenvolvimento.pibetting.model.Usuario;
import com.desenvolvimento.pibetting.model.UsuarioPlano;
import com.desenvolvimento.pibetting.repository.Planos;
import com.desenvolvimento.pibetting.repository.UsuarioPlanos;
import org.apache.logging.log4j.core.pattern.UuidPatternConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroUsuarioPlanoService {

    @Autowired
    private UsuarioPlanos usuarioPlanos;

    @Autowired
    private Planos planos;

    @Transactional
    public void addPlanoVip(Usuario usuario){

        Plano plano = planos.findById(Long.valueOf(5)); //5 = id do plano VIP

        UsuarioPlano usuarioPlano = new UsuarioPlano();
        usuarioPlano.setPlano(plano);
        usuarioPlano.setUsuario(usuario);
        usuarioPlano.setTerminoPlano(usuarioPlano.getTerminoPlano().plusMonths(usuarioPlano.getPlano().getDuracao()));; //incrementando o tempo de duração do plano
        usuarioPlanos.save(usuarioPlano);

    }

    public String verificarPlanoUsuario(Usuario usuario){
        Optional<UsuarioPlano> usuarioPlano =  usuarioPlanos.findByUsuario(usuario);
        if(usuarioPlano.isPresent()){
            return usuarioPlano.get().getPlano().getDescricao();
        }
        else{
            return "PLANO GRATIS";
        }
    }
}
