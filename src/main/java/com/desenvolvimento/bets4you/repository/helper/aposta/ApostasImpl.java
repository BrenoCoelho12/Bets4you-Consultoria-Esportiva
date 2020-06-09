package com.desenvolvimento.bets4you.repository.helper.aposta;

import com.desenvolvimento.bets4you.dto.ApostaDTO;
import com.desenvolvimento.bets4you.model.Aposta;
import com.desenvolvimento.bets4you.model.Situacao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.MonthDay;
import java.util.List;
import java.util.Optional;

public class ApostasImpl implements ApostasQueries{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Long totalApostasNoMes() {
        Optional<Long> optional = Optional.ofNullable(manager.createQuery("select count(odd) from Aposta where month(data) = :mes", Long.class)
                .setParameter("mes", MonthDay.now().getMonthValue()).getSingleResult());
        return optional.get();
    }

    @Override
    public BigDecimal oddMediaNoMes() {
        Long totalApostasNoMes = totalApostasNoMes();

        if(totalApostasNoMes == 0){
            return new BigDecimal(0);
        }

        Optional<BigDecimal> optional = Optional.ofNullable(
                manager.createQuery("select sum(odd) from Aposta where month(data) = :mes", BigDecimal.class)
                        .setParameter("mes", MonthDay.now().getMonthValue()).getSingleResult());

        BigDecimal somaDasOddsNoMes = optional.get();

        return somaDasOddsNoMes.divide(new BigDecimal(totalApostasNoMes), 2, RoundingMode.HALF_UP);
    }

    @Override
    public List<ApostaDTO> findByApostasDoMesAtual() {
        String jpql = "select new com.desenvolvimento.bets4you.dto.ApostaDTO(confianca, odd, data, situacao) "
                + "from Aposta where month(data) = :mes";
        List<ApostaDTO> apostasDoMes = manager.createQuery(jpql, ApostaDTO.class)
                .setParameter("mes", MonthDay.now().getMonthValue())
                .getResultList();

        return apostasDoMes;
    }

    @Override
    public List<ApostaDTO> findByApostasSituacaoPerdida() {
        String jpql = "select new com.desenvolvimento.bets4you.dto.ApostaDTO(confianca, odd, data, situacao) "
                + "from Aposta where month(data) = :mes and situacao = :situacao";
        List<ApostaDTO> apostasPerdidas = manager.createQuery(jpql, ApostaDTO.class)
                .setParameter("mes", MonthDay.now().getMonthValue())
                .setParameter("situacao", Situacao.PERDIDA)
                .getResultList();
        return apostasPerdidas;

    }

    @Override
    public List<ApostaDTO> findByApostasSituacaoVencida() {
        String jpql = "select new com.desenvolvimento.bets4you.dto.ApostaDTO(confianca, odd, data, situacao) "
                + "from Aposta where month(data) = :mes and situacao = :situacao";
        List<ApostaDTO> apostasVencidas = manager.createQuery(jpql, ApostaDTO.class)
                .setParameter("mes", MonthDay.now().getMonthValue())
                .setParameter("situacao", Situacao.VENCIDA)
                .getResultList();
        return apostasVencidas;
    }

}
