package com.desenvolvimento.bets4you.repository.helper.aposta;

import com.desenvolvimento.bets4you.model.Aposta;
import com.desenvolvimento.bets4you.model.Situacao;

import java.math.BigDecimal;
import java.util.List;

public interface ApostasQueries {

    public Long totalApostasNoMes();

    List<Aposta> findByApostasSituacaoVencida();

    public BigDecimal oddMediaNoMes();

    List<Aposta> findByApostasDoMesAtual();

    List<Aposta> findByApostasSituacaoPerdida();
}
