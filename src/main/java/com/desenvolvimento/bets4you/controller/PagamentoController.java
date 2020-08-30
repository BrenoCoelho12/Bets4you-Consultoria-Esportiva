package com.desenvolvimento.bets4you.controller;

import br.com.uol.pagseguro.api.PagSeguro;
import br.com.uol.pagseguro.api.PagSeguroEnv;
import br.com.uol.pagseguro.api.checkout.CheckoutRegistrationBuilder;
import br.com.uol.pagseguro.api.checkout.RegisteredCheckout;
import br.com.uol.pagseguro.api.common.domain.builder.*;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.common.domain.enums.PaymentMethodGroup;
import br.com.uol.pagseguro.api.credential.Credential;
import br.com.uol.pagseguro.api.http.JSEHttpClient;
import br.com.uol.pagseguro.api.utils.logging.SimpleLoggerFactory;
import com.desenvolvimento.bets4you.model.Plano;
import com.desenvolvimento.bets4you.model.Usuario;
import com.desenvolvimento.bets4you.model.UsuarioPlano;
import com.desenvolvimento.bets4you.repository.Planos;
import com.desenvolvimento.bets4you.repository.Usuarios;
import com.desenvolvimento.bets4you.security.UsuarioSistema;
import com.desenvolvimento.bets4you.service.CadastroVendaMercadoPagoService;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.MercadoPago;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class PagamentoController {
    @Autowired
    private Planos planos;

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private CadastroVendaMercadoPagoService cadastroVendaMercadoPagoService;

    private final String EMAIL = "consultoriabets4you@gmail.com";
    private final String TOKEN = "5FFEBAAFD9D346D39CE6A5150C51CAEA";

    /*
    @PostMapping(value = "/pagseguro-compra", consumes = { MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<?> compraPagSeguro(@RequestBody UsuarioPlano usuarioPlano) throws MPException {

        Optional<Plano> plano = planos.findById(usuarioPlano.getPlano().getId());
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
                                            .withNumber("5584998372941")))


                            .addItem(new PaymentItemBuilder()
                                    .withId("Consultoria plano " + plano.get().getId())
                                    .withDescription(plano.get().getDescricao())
                                    .withAmount(plano.get().getValorTotal())
                                    .withQuantity(1))

                            //Definindo formas de pagamento



                            //definindo parcelamento
                            .addPaymentMethodConfig(new PaymentMethodConfigBuilder()
                                    .withPaymentMethod(new PaymentMethodBuilder()
                                            .withGroup(PaymentMethodGroup.CREDIT_CARD)
                                    )
                                    .withConfig(new ConfigBuilder()
                                            .withKey(ConfigKey.MAX_INSTALLMENTS_LIMIT)
                                            .withValue(new BigDecimal(plano.get().getDuracao()))
                                    )
                            ));



            String code = registeredCheckout.getCheckoutCode();
            System.out.println(code);
            System.out.println(registeredCheckout.getRedirectURL());
            return ResponseEntity.ok(code);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
*/
    @GetMapping("/detalhesPlano/{idPlano}")
    public ModelAndView detalhesCompra(@PathVariable("idPlano") Long idPlano){
        ModelAndView mv = new ModelAndView("/pagamentos/detalhes_compra");

        Optional<Plano> plano = planos.findById(idPlano);

        if(!plano.isPresent()){
            return new ModelAndView("500");
        }

        mv.addObject("plano", plano.get());

        return mv;
    }

    @PostMapping("/mercadopago-compra/{idPlano}")
    public ModelAndView compraMercadoPago(@AuthenticationPrincipal UsuarioSistema usuario, @PathVariable("idPlano") Long idPlano, HttpServletRequest request)
            throws MPException {

        String token = request.getParameter("token");
        String payment_method_id = request.getParameter("payment_method_id");
        Integer installments = Integer.valueOf(request.getParameter("installments"));
        String issuer_id = request.getParameter("issuer_id");

        Payment.Status status = cadastroVendaMercadoPagoService.venda(token, payment_method_id, installments, issuer_id, idPlano, usuario);
        System.out.println(status);

        if(status != null){
            switch (status) {
                case approved:
                    return new ModelAndView("pagamentos/aprovado");
                case rejected:
                    return new ModelAndView("pagamentos/rejeitado");
            }
        }
        else{

            return new ModelAndView("500");
        }

        return new ModelAndView("500");
    }
}



