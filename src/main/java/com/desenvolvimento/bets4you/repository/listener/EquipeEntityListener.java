package com.desenvolvimento.bets4you.repository.listener;

import com.desenvolvimento.bets4you.model.Equipe;
import com.desenvolvimento.bets4you.storage.FotoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.persistence.PostLoad;

/* Essa classe foi criada para que seja possível recuperar o valor da urlFoto e urlThumbnail de cada equipe.
* Como os dois atributos mencionados acima são @Transient, faz-se necessário que eles sejam inicializados para
* que o JPA possa carregar valores para eles. Essa classe listener será chamada para cada equipe presente no banco!
*
* A classe foi criada para que na view seja possível recuperar o caminho absoluto completa da foto de cada equipe!
*
* */
public class EquipeEntityListener {

    @Autowired
    private FotoStorage fotoStorage;

    @PostLoad
    public void postLoad(final Equipe equipe){

        /*Esse método do spring é carregado para que seja possível realizar a injeção de dependências
        da classe FotoStorage, pois como as entidades (@Entity) são carregadas pelo JPA,
        a classe FotoStorage ainda não teria sido instanciada pelo sistema. (NÃO ENTENDI BEM)

        É como se chamando esse método ele injetasse todas as classes necessárias para que e
        classe EquipeEntityLIstener funcione bem, pois sem ela a interface FotoStorage não conseguiria
        ser instanciada.
         */

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        /* o método getUrl(nomeFoto) retorna o caminho absoluto da foto, a partir do nome informado*/
        equipe.setUrlFoto(fotoStorage.getUrl(equipe.getFoto()));
        equipe.setUrlThumbnailFoto(fotoStorage.getUrl(FotoStorage.THUMBNAIL_PREFIX + equipe.getFoto()));
    }

}
