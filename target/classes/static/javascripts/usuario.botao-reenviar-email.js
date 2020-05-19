var Pibetting = Pibetting || {};

Pibetting.BotaoReenviarEmail = (function(){

    function BotaoReenviarEmail(){
        this.botaoReenviarEmail = $("#botaoReenviarEmail");
        this.token = $("#token")[0].innerText;  //valor do token
        this.texto30Segundos = $("#texto30Segundos");
        this.cronometro = $("#cronometro");
        this.time = 60;
    }

    BotaoReenviarEmail.prototype.iniciar = function() {
        this.botaoReenviarEmail.on('click', botaoReenviarEmail.bind(this));
    }

    function botaoReenviarEmail() {

        $.ajax({
            url:  'http://localhost:8080/pibetting/usuario/email/reenviar',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ token: this.token })
        });

        this.time = 60;

        this.botaoReenviarEmail.addClass("hidden");
        this.texto30Segundos.removeClass("hidden");
        this.cronometro.removeClass("hidden");

        var interval = setInterval(cronometro.bind(this), 1000);

        function cronometro(){
            if(this.time == 1){
                this.botaoReenviarEmail.removeClass("hidden");
                this.texto30Segundos.addClass("hidden");
                this.cronometro.addClass("hidden");
                clearInterval(interval);
                this.cronometro.text(60);
            }
            else{
                this.time--;
                this.cronometro.text(this.time);
            }
        }

    }



    return BotaoReenviarEmail;
}());

$(function(){

    var BotaoReenviarEmail = new Pibetting.BotaoReenviarEmail();
    BotaoReenviarEmail.iniciar();

});