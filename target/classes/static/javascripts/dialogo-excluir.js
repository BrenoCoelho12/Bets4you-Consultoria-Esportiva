var Pibetting = Pibetting || {};

Pibetting.DialogoExcluir = (function() {

    function DialogoExcluir() {
        this.exclusaoBtn = $('.js-exclusao-btn')
    }

    DialogoExcluir.prototype.iniciar = function() {
        this.exclusaoBtn.on('click', onExcluirClicado.bind(this));

        if (window.location.search.indexOf('excluido') > -1) {
            swal('Pronto!', 'Excluído com sucesso!', 'success');
        }
    }

    function onExcluirClicado(evento) {
        evento.preventDefault();
        var botaoClicado = $(evento.currentTarget);
        var url = botaoClicado.data('url');
        var objeto = botaoClicado.data('objeto');
        console.log(url);

        swal({
            title: 'Tem certeza?',
            text: 'Excluir \n"' + objeto + '"? \n Você não poderá recuperar depois.',
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: 'Sim, exclua agora!',
            closeOnConfirm: false
        }, onExcluirConfirmado.bind(this, url));
    }

    function onExcluirConfirmado(url) {
        $.ajax({
            url: url,
            method: 'DELETE',
            success: onExcluirSucesso.bind(this),
            error: onErrorExcluir.bind(this)
        })
    }

    function onErrorExcluir(e){
        swal('Oops!', e.responseText, 'error');
    }

    function onExcluirSucesso(){
        var urlAtual = window.location.href;
        var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
        var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';

        window.location = novaUrl;
    }

    return DialogoExcluir;

}());

$(function() {
    var dialogo = new Pibetting.DialogoExcluir();
    dialogo.iniciar();
});