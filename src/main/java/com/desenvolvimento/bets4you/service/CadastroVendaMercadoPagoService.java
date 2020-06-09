package com.desenvolvimento.bets4you.service;

import com.desenvolvimento.bets4you.model.Plano;
import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.repository.Planos;
import com.desenvolvimento.bets4you.repository.Usuarios;
import com.desenvolvimento.bets4you.security.UsuarioSistema;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Service
public class CadastroVendaMercadoPagoService {

    @Autowired
    private Planos planos;

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private CadastroUsuarioPlanoService cadastroUsuarioPlanoService;


    public Payment.Status venda(String token, String payment_method_id, Integer installments, String issuer_id, Long idPlano, UsuarioSistema usuario) throws MPException {

        Optional<Plano> plano = planos.findById(idPlano);
        Optional<Usuario> usuarioLogado = usuarios.findByEmailIgnoreCase(usuario.getUsername());

        if (plano.isPresent() && usuarioLogado.isPresent()){
            Float valorPlano = plano.get().getValorTotal().floatValue();


            MercadoPago.SDK.setAccessToken("TEST-3355084366349304-060401-2dad242761eda211677ed598ecb814ae-568143852");

            Payment payment = new Payment();
            payment
                    .setTransactionAmount((float) valorPlano)
                    .setToken(token)
                    .setDescription(plano.get().getDescricao())
                    .setInstallments(installments)
                    .setPaymentMethodId(payment_method_id)
                    .setIssuerId(issuer_id)
                    .setPayer(new Payer()
                            .setEmail(usuarioLogado.get().getEmail()))
                    .setBinaryMode(true); //fazendo com que o resultado seja aprovado ou reprovado (excluindo as opções pendentes, analisando, etc).
            // Armazena e envia o pagamento
            payment.save();


            Payment.Status status = validacaoCompra(payment.getStatus(), usuarioLogado.get(), plano.get());

            return status;
        }

        else {
            return Payment.Status.rejected;
        }


    }

    private Payment.Status validacaoCompra(Payment.Status codigoAutorizacao, Usuario usuarioLogado, Plano plano) {

        if(codigoAutorizacao == Payment.Status.approved){
            System.out.println("entrou");
            cadastroUsuarioPlanoService.addPlanoVip(usuarioLogado, plano);
            return codigoAutorizacao;
        }
        else if(codigoAutorizacao == null){
            return null;
        }
        else{
            return Payment.Status.rejected;
        }

    }



}
