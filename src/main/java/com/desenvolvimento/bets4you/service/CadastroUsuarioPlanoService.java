package com.desenvolvimento.bets4you.service;

import com.desenvolvimento.bets4you.model.Plano;
import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.model.UsuarioPlano;
import com.desenvolvimento.bets4you.repository.Planos;
import com.desenvolvimento.bets4you.repository.UsuarioPlanos;
import com.desenvolvimento.bets4you.repository.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CadastroUsuarioPlanoService {

    @Autowired
    private UsuarioPlanos usuarioPlanos;

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private Planos planos;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Transactional
    public void addPlanoVip(Usuario usuario, Plano novoPlano){

        Boolean usuarioTemPlanoExistente = verificarSeUsuarioPossuiPlano(usuario);

        //Tratando o caso do usuário já possuir um plano vip
        if(usuarioTemPlanoExistente == true){
            UsuarioPlano usuarioPlano = usuarioPlanos.findByUsuario(usuario).get();

            LocalDate dataInicioPlano = LocalDate.now();
            LocalDate dataTerminoPlano = dataInicioPlano.plusMonths(novoPlano.getDuracao());
            usuarioPlano.setInicioPlano(dataInicioPlano);
            usuarioPlano.setTerminoPlano(dataTerminoPlano);
            usuarioPlano.setPlano(novoPlano);
            usuarioPlanos.save(usuarioPlano);
            usuarioPlanos.atualizarStatusUsuarioPlanoParaAtivo(usuarioPlano);
            /*setando o acesso vip para true*/
            usuarios.atualizarAcessoVip(usuario.getId());

        }

        //situação em que o usuário ainda não possui um plano
        else{
            /*Criando o plano do usuário*/
            UsuarioPlano usuarioPlano = new UsuarioPlano();
            LocalDate dataTerminoPlano = usuarioPlano.getInicioPlano().plusMonths(novoPlano.getDuracao());
            usuarioPlano.setPlano(novoPlano);
            usuarioPlano.setUsuario(usuario);
            usuarioPlano.setTerminoPlano(dataTerminoPlano);; //incrementando o tempo de duração do plano
            usuarioPlanos.save(usuarioPlano);

            /*setando o acesso vip para true*/
            usuarios.atualizarAcessoVip(usuario.getId());
        }


    }

    public String verificarPlanoUsuario(Usuario usuario){
        Optional<UsuarioPlano> usuarioPlano =  usuarioPlanos.findByUsuario(usuario);
        if(usuarioPlano.isPresent() && usuario.getAcessoVip()){
            return usuarioPlano.get().getPlano().getDescricao();
        }
        else{
            return "INATIVO";
        }
    }

    public Boolean validadeDoPlanoVip(Usuario usuario){
        UsuarioPlano usuarioPlano = usuarioPlanos.findByUsuario(usuario).get();

        LocalDate dataAtual = LocalDate.now();

        if(dataAtual.isAfter(usuarioPlano.getTerminoPlano())){
            usuarios.retirarAcessoVip(usuario.getId());
            usuarioPlanos.atualizarStatusUsuarioPlanoParaVencido(usuarioPlano);
            return false;
        }

        return true;
    }

    private Boolean verificarSeUsuarioPossuiPlano(Usuario usuario) {
        Optional<UsuarioPlano> usuarioPlano = usuarioPlanos.findByUsuario(usuario);

        if(usuarioPlano.isPresent()){
            return true;
        }
        else{
            return false;
        }

    }


}
