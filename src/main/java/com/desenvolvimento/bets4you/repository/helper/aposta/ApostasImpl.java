package com.desenvolvimento.bets4you.repository.helper.aposta;

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
    @Transactional(readOnly = true)
    public List<Aposta> findByApostasDoMesAtual() {
        return manager
                .createQuery("from Aposta where month(data) = :mes", Aposta.class)
                .setParameter("mes", MonthDay.now().getMonthValue())
                .getResultList();


    }

    @Override
    @Transactional(readOnly = true)
    public List<Aposta> findByApostasSituacaoPerdida() {
         return manager
                 .createQuery("from Aposta where month(data) = :mes and situacao = :situacao", Aposta.class)
                 .setParameter("mes", MonthDay.now().getMonthValue())
                 .setParameter("situacao", Situacao.PERDIDA)
                 .getResultList();


    }

    @Override
    @Transactional(readOnly = true)
    public List<Aposta> findByApostasSituacaoVencida() {
        return manager
                .createQuery("from Aposta where month(data) = :mes and situacao = :situacao", Aposta.class)
                .setParameter("mes", MonthDay.now().getMonthValue())
                .setParameter("situacao", Situacao.VENCIDA)
                .getResultList();
    }

}
