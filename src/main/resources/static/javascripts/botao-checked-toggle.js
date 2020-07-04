var Bets4you = Bets4you || {};

Bets4you.ButtonCheckedToggle = (function(){

    function ButtonCheckedToggle() {
        this.buttonCheckedToggle = $("[data-idAposta]");
    }

    ButtonCheckedToggle.prototype.iniciar = function() {
        $('table thead ').on('change', '.toggle-event', function(){
            console.log("entrou botao status.")
            var rowCurrent = $(this).closest('tr'); //pegando a linha selecionada

            /*pegando os valores das colunas*/
            var idAposta = rowCurrent.find("td").eq(0).text();


            if($(this).prop('checked')){
                /*se o checkbox for selecionado enviar um ajax passando o id da aposta e, também, o status da aposta como TRUE*/
                $.ajax({
                    url: '/aposta/gerenciamentoApostas',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(
                        { 'id': idAposta,
                                'status': true
                        }),
                    beforeSend: adicionarCsrfToken
                });
            }

            else {
                /*se o checkbox for desativado enviar um ajax passando o id da aposta e, também, o status da aposta como FALSE*/
                $.ajax({
                    url: '/aposta/gerenciamentoApostas',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(
                        { 'id': idAposta,
                            'status': false
                        }),
                    beforeSend: adicionarCsrfToken
                });
            }
        });

        $('table thead ').on('change', '.toggle-event-situacao', function(){
            console.log("entrou botao situacao");
            var rowCurrent = $(this).closest('tr'); //pegando a linha selecionada

            /*pegando os valores das colunas*/
            var idAposta = rowCurrent.find("td").eq(0).text();


            if($(this).prop('checked')){
                /*se o checkbox for selecionado enviar um ajax passando o id da aposta e, também, o status da aposta como TRUE*/
                $.ajax({
                    url: '/aposta/atualizarSituacao',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(
                        { 'id': idAposta,
                            'situacao': 'VENCIDA'
                        }),
                    beforeSend: adicionarCsrfToken
                });
            }

            else {
                /*se o checkbox for desativado enviar um ajax passando o id da aposta e, também, o status da aposta como FALSE*/
                $.ajax({
                    url: '/aposta/atualizarSituacao',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(
                        { 'id': idAposta,
                            'situacao': 'PERDIDA'
                        }),
                    beforeSend: adicionarCsrfToken
                });
            }
        });

    }



    function adicionarCsrfToken(xhr) {
        var token = $('input[name=_csrf]').val();
        var header = $('input[name=_csrf_header]').val();
        xhr.setRequestHeader(header, token);
    }

    return ButtonCheckedToggle;

}());

$(function() {

    var buttonCheckedToggle = new Bets4you.ButtonCheckedToggle();
    buttonCheckedToggle.iniciar();

});
