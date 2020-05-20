var Bets4you = Bets4you || {};

Bets4you.ApiPagseguro = (function() {

    function ApiPagseguro() {
        this.planoEscolhido = $('.botao-plano');
    }

    ApiPagseguro.prototype.iniciar = function() {
        this.planoEscolhido.on('click', onPlanoEscolhido.bind(this));
    }

    function onPlanoEscolhido(evento){
        evento.preventDefault();
        var botaoClicado = $(evento.currentTarget);
        var plano = botaoClicado.data('plano');
        var usuario = botaoClicado.data('usuario');
        var json = {
            plano: {
                id: plano,
            },
            usuario: {
                id: usuario
            }
        }
        $.ajax({
            url:  '/pibetting/pagseguro-compra',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(json),
            success: onTeste.bind(this),
            beforeSend: adicionarCsrfToken,

        });

    }

    function onTeste(code){
        console.log(code);
        //Insira o código de checkout gerado no Passo 1
        var callback = {
            success : function(transactionCode) {
                //Insira os comandos para quando o usuário finalizar o pagamento.
                //O código da transação estará na variável "transactionCode"
                console.log("Compra feita com sucesso, código de transação: " + transactionCode);
            },
            abort : function() {
                //Insira os comandos para quando o usuário abandonar a tela de pagamento.
                console.log("abortado");
            }
        };
        //Chamada do lightbox passando o código de checkout e os comandos para o callback
        var isOpenLightbox = PagSeguroLightbox(code, callback);
        // Redireciona o comprador, caso o navegador não tenha suporte ao Lightbox
        if (!isOpenLightbox){
            location.href="https://pagseguro.uol.com.br/v2/checkout/payment.html?code=" + code;
            console.log("Redirecionamento")
        }
    }

    function adicionarCsrfToken(xhr) {
        var token = $('input[name=_csrf]').val();
        var header = $('input[name=_csrf_header]').val();
        xhr.setRequestHeader(header, token);
    }

    return ApiPagseguro;

}());

$(function() {
    var pagseguro = new Bets4you.ApiPagseguro();
    pagseguro.iniciar();
});



