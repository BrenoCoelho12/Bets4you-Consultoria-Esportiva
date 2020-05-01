var Pibetting = Pibetting || {};

Pibetting.BotaoReenviarEmail = (function(){

    function BotaoReenviarEmail(){
        this.botaoReenviarEmail = $("#botaoReenviarEmail");
        this.token = $("#token")[0].innerText;  //valor do token
        this.texto30Segundos = $("#botao30Segundos");
    }

    BotaoReenviarEmail.prototype.iniciar = function() {
        this.botaoReenviarEmail.on('click', botaoReenviarEmail.bind(this));
    }

    function botaoReenviarEmail() {

        this.botaoReenviarEmail.attr("disabled", "disabled");
        this.texto30Segundos.removeClass("hidden");
        setTimeout(temporizadorBotao.bind(this), 3000);

        function temporizadorBotao(){
            this.botaoReenviarEmail.prop('disabled', false);
            this.texto30Segundos.addClass("hidden");
        }

        $.ajax({
            url:  'http://localhost:8080/pibetting/usuario/email/reenviar',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ token: this.token })
        });
    }



    return BotaoReenviarEmail;
}());

$(function(){

    var BotaoReenviarEmail = new Pibetting.BotaoReenviarEmail();
    BotaoReenviarEmail.iniciar();

});