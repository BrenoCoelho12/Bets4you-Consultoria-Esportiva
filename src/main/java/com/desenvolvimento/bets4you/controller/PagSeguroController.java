package com.desenvolvimento.bets4you.controller;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.builder.*;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;
import com.desenvolvimento.bets4you.model.Plano;
import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.model.UsuarioPlano;
import com.desenvolvimento.bets4you.repository.Planos;
import com.desenvolvimento.bets4you.repository.Usuarios;
import com.desenvolvimento.bets4you.security.UsuarioSistema;
import com.desenvolvimento.bets4you.service.CadastroVendaPagSeguroService;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.MercadoPago;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
public class PagSeguroController {

    @Autowired
    private Planos planos;

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private CadastroVendaPagSeguroService cadastroVendaPagSeguroService;

    private final String EMAIL = "consultoriabets4you@gmail.com";
    private final String TOKEN = "5FFEBAAFD9D346D39CE6A5150C51CAEA";

    @PostMapping(value = "/pagseguro-compra", consumes = { MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> compraPagSeguro(@RequestBody UsuarioPlano usuarioPlano) throws MPException {

        Plano plano = planos.findById(usuarioPlano.getPlano().getId());
        Usuario usuario = usuarios.findById(usuarioPlano.getUsuario().getId());

        String sellerEmail = "consultoriabets4you@gmail.com";
        String sellerToken = "5FFEBAAFD9D346D39CE6A5150C51CAEA";

        try {

            final PagSeguro pagSeguro = PagSeguro
                    .instance(new SimpleLoggerFactory(), new JSEHttpClient(),
                            Credential.sellerCredential(sellerEmail, sellerToken), PagSeguroEnv.SANDBOX);

            //Criando um checkout
            RegisteredCheckout registeredCheckout;
            registeredCheckout = pagSeguro.checkouts().register(
                    new CheckoutRegistrationBuilder()
                            .withCurrency(Currency.BRL)
                            .withReference("XXXXXX")
                            .withSender(new SenderBuilder()
                                    .withEmail(usuario.getEmail())
                                    .withName(usuario.getNome())
                                    .withPhone(new PhoneBuilder()
                                            .withAreaCode("84")
                                            .withNumber("996878281")))

                            .addItem(new PaymentItemBuilder()
                                    .withId("Consultoria plano " + plano.getId())
                                    .withDescription(plano.getDescricao())
                                    .withAmount((plano.getValor().multiply(new BigDecimal(plano.getDuracao()))))
                                    .withQuantity(1)));

            String code = registeredCheckout.getCheckoutCode();
            System.out.println(code);
            System.out.println(registeredCheckout.getRedirectURL());
            return ResponseEntity.ok(code);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
/*
    private Credentials getCredentials() throws PagSeguroServiceException {
        return new AccountCredentials(EMAIL, TOKEN);
    }

    private PaymentRequestItem getItem(Plano plano) {
        PaymentRequestItem item = new PaymentRequestItem();
        item.setId(String.valueOf(plano.getId()));
        item.setAmount(plano.getValor().multiply(new BigDecimal(plano.getDuracao())));
        item.setDescription(plano.getDescricao());
        item.setQuantity(1);
        item.

        return item;
    }


    private Sender getSender(Usuario usuario) {
        Sender sender = new Sender();
        sender.setName(usuario.getNome());
        sender.setEmail(usuario.getEmail());
        sender.setPhone(new Phone("84", "996878281"));
        return sender;
    }
*/
    @PostMapping("/mercadopago")
    public void compraMercadoPago
            (@AuthenticationPrincipal UsuarioSistema usuario, @PathVariable("idPlano") Long plano, HttpServletRequest request)
            throws MPException {

        String token = request.getParameter("token");
        String payment_method_id = request.getParameter("payment_method_id");
        Integer installments = Integer.valueOf(request.getParameter("installments"));
        Integer issuer_id = Integer.valueOf(request.getParameter("issuer_id"));

        MercadoPago.SDK.setAccessToken("TEST-3355084366349304-051415-b6a01f531aaa2242f02926497539b9df-568143852");

        Payment payment = new Payment();
        payment.setTransactionAmount(146f)
                .setToken(token)
                .setDescription("Consultoria - Bets4you")
                .setInstallments(installments)
                .setPaymentMethodId(payment_method_id)
                .setIssuerId(String.valueOf(issuer_id))
                .setPayer(new Payer()
                        .setEmail("consultoriabets4you@gmail.com"));
        // Armazena e envia o pagamento
        payment.save();

    }
}
