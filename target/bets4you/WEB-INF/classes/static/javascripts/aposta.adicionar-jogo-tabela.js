$(function(){
	
	//setando valores no input qtdJogos
	var contadorQtdJogos = (function contadorJogos(){
		var valor = 0;
		var inputQtdJogos = $('#qtdJogos');
		inputQtdJogos.val('0');
		
		incrementar = function() {
			valor++;
			inputQtdJogos.val(valor);
		}
		
		decrementar = function() {
			valor--;
			inputQtdJogos.val(valor);
		}
		
		return {
			incrementar: incrementar,
			decrementar: decrementar
		}
		
		
	}());
	
	var contadorOddAposta = (function contadorOddAposta(){
		var valor = 1;
		var inputOddAposta = $('#oddAposta');
		
		incrementar = function(oddJogo){
			valor = valor*oddJogo;
			inputOddAposta.val(valor);
			inputOddAposta.maskMoney({ precision: 2, decimal:',', thousands: '.'});
		}
		
		decrementar = function(oddJogo){
			console.log("oddJogo", oddJogo);
			valor = valor/oddJogo;
		
			if(valor == 1){
				inputOddAposta.val("");
				return;
			}
			
			inputOddAposta.val(valor);
			inputOddAposta.maskMoney({ precision: 2, decimal:',', thousands: '.'});
		}
		
		return {
			incrementar: incrementar,
			decrementar: decrementar
		}
	}());
	
	var botaoAdicionarJogo = $(".js-btn-adicionar-jogo");
	var containerMensagemErro = $('.js-mensagem-cadastro-jogo');
	var tabelaAposta = $("table tbody");
	var btnCadastrarAposta = $('cadastrar-aposta');
	var index = 0;
	
		
	botaoAdicionarJogo.on('click', verificarJogo);
	
	function verificarJogo() {
		
		var nomeEquipeMandante = $("#mandante :selected").text();
		var nomeEquipeVisitante = $("#visitante :selected").text();
		var idMandante = $("#mandante").val();
		var idVisitante = $("#visitante").val();
		var sugestao = $("#sugestao").val();
		var odd = $("#oddJogo").val();
		
		/*TRATANDO CASOS EM QUE O USUÁRIO PREENCHERÁ INCORRETAMENTE OS DADOS DO TIME MANDANTE  
		 OU PREENCHERÁ INCORRETAMENTE OS DADOS DO TIME VISITANTE*/
		
		if(idMandante == "" || idVisitante == "" || sugestao == "" || odd == ""){
			var mensagemErro = "Selecione corretamente as equipes.";
			containerMensagemErro.removeClass('hidden');
			containerMensagemErro.html('<span>'+ mensagemErro + '</span>');
			return;
		}
		
		containerMensagemErro.addClass('hidden');
		var str = '<tr th:each="aposta, iteracao : ${aposta}">'+
						'<td class="idEquipe">'+
						' <input class="form-control" id="jogos' + index + '.mandante" name="jogos[' + index + '].mandante" type="text" value="' + idMandante + '" readonly> ' +
						'</td>'+
	
						'<td class="nome-equipe">'+
							'<input type="text" class="form-control" value="'+nomeEquipeMandante+'" readonly>'+
						'</td>' +
						
						'<td class="idEquipe">'+
							'<input type="text" class="form-control" id="jogos' + index + '.visitante" name="jogos[' + index + '].visitante" value="'+idVisitante+'" readonly>'+
						'</td>'+
						
						'<td class="nome-equipe">'+
							'<input type="text" class="form-control " value="'+nomeEquipeVisitante+'" readonly>'+
						'</td>' +
					
						'<td class="sugestao">'+
							'<input type="text" class="form-control " id="jogos' + index + '.sugestao" name="jogos[' + index + '].sugestao"}" value="'+sugestao+'" readonly>'+
						'</td>' +
						
						'<td class="odd">'+
							'<input type="text" class="form-control js-decimal" id="jogos' + index + '.odd" name="jogos[' + index + '].odd" value="'+odd+'" readonly>'+
						'</td>' +
						
						'<td class="text-center btnDeleteRow" >'+ 
								'<span class="glyphicon glyphicon-trash text-danger button-delete-row"></span>'+
							'</td>' +
					'</tr>'
						
		tabelaAposta.append(str);
		index++;
		contadorQtdJogos.incrementar(); //incrementando contador quantidade de jogos. 
		contadorOddAposta.incrementar(odd); //incrementando contador oddAposta.
		
	}	
		
	/*Como a row dos jogos são criadas após o carregamento da página, através da
	 * função append do método adicionarJogoTabela, eles não reconhecerão 
	 * se o evento click estiver direto no SPAN que traz a imagem de apagar a linha...
	 * Dessa maneira, houve a necessidade de chamar o evento 'click' via $document, pois
	 * o $document busca em toda página, inclusive nos elementos adicionados posteriormente
	 * ao carregamento da página. 
	 */
	
	$('table tbody ').on('click', '.button-delete-row', function(){
			var rowCurrent = $(this).closest('tr');
			var odd = rowCurrent.find('td:eq(5)').parent().find('.js-decimal').val();
			contadorQtdJogos.decrementar();
			contadorOddAposta.decrementar(odd);
			$(this).closest('tr').remove();
	});
	
});
