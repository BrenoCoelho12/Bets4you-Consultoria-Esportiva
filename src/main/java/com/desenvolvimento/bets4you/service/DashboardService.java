package com.desenvolvimento.bets4you.service;

import com.desenvolvimento.bets4you.model.Aposta;
import com.desenvolvimento.bets4you.model.Situacao;
import com.desenvolvimento.bets4you.repository.Apostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private Apostas apostas;

    public Hashtable calculoRentabilidadeDiaADia(){
        List<Aposta> todasApostasDoMesAtual = apostas.findByApostasDoMesAtual();
        int diaDaUltimaApostaCadastradaNoMes = todasApostasDoMesAtual.get(todasApostasDoMesAtual.size()-1).getData().getDayOfMonth();

        Hashtable<Integer, BigDecimal> tabelaHashRentabilidadePorDia = calculoRentabilidadePorDia();

        Hashtable<Integer, BigDecimal> tabelaHashRentabilidadeDiaADia = new Hashtable<>();

        //salvando rentabilidade do mes com o passar dos dias
        for(int i = 1;i<=diaDaUltimaApostaCadastradaNoMes;i++){
            if(i == 1){
                //salva no arquivo externo
                tabelaHashRentabilidadeDiaADia.put(i, tabelaHashRentabilidadePorDia.get(i));
                System.out.println("rentabilidade no dia " + i + ":" +tabelaHashRentabilidadeDiaADia.get(i));
            }
            else {
                BigDecimal rentabilidadeDoDiaAtual = tabelaHashRentabilidadePorDia.get(i);


                BigDecimal rentabilidadeDoDiaAnterior = tabelaHashRentabilidadeDiaADia.get(i-1);

                tabelaHashRentabilidadeDiaADia.put(i, rentabilidadeDoDiaAtual.add(rentabilidadeDoDiaAnterior));
            }
        }

        return tabelaHashRentabilidadeDiaADia;
    }

    public Hashtable calculoRentabilidadePorDia(){
        List<Aposta> todasApostasDoMesAtual = apostas.findByApostasDoMesAtual();
        int qtdDiasDoMesAtual = LocalDate.now().lengthOfMonth();

        Hashtable<Integer, BigDecimal> tabelaHash = new Hashtable<Integer, BigDecimal>();

        /*Inicializando a tabela  hash*/
        for(int i = 1;i<=qtdDiasDoMesAtual;i++){
            tabelaHash.put(i, new BigDecimal(0));
        }

        /*a rentabilidade dia-a-dia é calculada analisando as apostas do dia e verificando quais foram ganhas e quais foram perdidas...
        A estrutura da tabela hash permite criar uma estrutura(chave, valor). Com isso, podemos usar cada dia do mes como uma chave
        e atribuir a rentabilidade daquele dia ao valor da chave*/

        for(int i = 0;i<todasApostasDoMesAtual.size();i++){
            Aposta apostaAtual = todasApostasDoMesAtual.get(i);
            int diaDaAposta = apostaAtual.getData().getDayOfMonth();

            if(apostaAtual.getSituacao() == Situacao.PERDIDA){
                tabelaHash.put(diaDaAposta, tabelaHash.get(diaDaAposta).subtract(apostaAtual.getConfianca()));
            }

            else if(apostaAtual.getSituacao() == Situacao.VENCIDA){
                BigDecimal unidadesGanhas = (apostaAtual.getOdd().subtract(new BigDecimal(1))).multiply(apostaAtual.getConfianca());
                tabelaHash.put(diaDaAposta, tabelaHash.get(diaDaAposta).add(unidadesGanhas));
            }

        }

/*
        ///salvando rentabilidades do dia em um arquivo externo
        for(int i = 1;i<=qtdDiasDoMesAtual;i++){
            //salva no arquivo externo
            System.out.println("Valor na chave " + i + ":" + tabelaHash.get(i));
        }

        //salvando rentabilidade do mes com o passar dos dias
        for(int i = 1;i<=diaDaUltimaApostaCadastradaNoMes;i++){
            if(i == 1){
                //salva no arquivo externo
                System.out.println("rentabilidadeAcumulada do dia " + i + ":" + tabelaHash.get(i));
            }
            else {
                BigDecimal rentabilidadeDoDiaAtual = tabelaHash.get(i);
                BigDecimal rentabilidadeDoDiaAnterior = tabelaHash.get(i-1);
                tabelaHash.put(i, rentabilidadeDoDiaAtual.add(rentabilidadeDoDiaAnterior));
                System.out.println("rentabilidadeAcumulada do dia " + i + ":" + tabelaHash.get(i));
                //salva no arquivo externo;
            }
        }
*/
        return tabelaHash;
    }

    public BigDecimal calculoRentabilidadeNoMes(){
        List<Aposta> apostasGanhasNoMes = apostas.findByApostasSituacaoVencida();
        List<Aposta> apostasPerdidasNoMes = apostas.findByApostasSituacaoPerdida();

        BigDecimal unidadesGanhas = new BigDecimal(0);
        BigDecimal unidadesPerdidas = new BigDecimal(0);
        BigDecimal aux;

        //Calculando a quantidade de unidades ganhas
        for(int i = 0;i<apostasGanhasNoMes.size();i++){
            Aposta apostaAtual = apostasGanhasNoMes.get(i);
            aux = (apostaAtual.getOdd().subtract(new BigDecimal(1))).multiply(apostaAtual.getConfianca());
            unidadesGanhas = unidadesGanhas.add(aux);
        }
        //calculando a quantidade de unidades perdidas
        for(int i = 0;i<apostasPerdidasNoMes.size();i++){
            Aposta apostaAtual = apostasPerdidasNoMes.get(i);
            unidadesPerdidas = unidadesPerdidas.add(apostaAtual.getConfianca());
        }

        BigDecimal rentabilidade = unidadesGanhas.subtract(unidadesPerdidas);


        return rentabilidade;
    }
}
