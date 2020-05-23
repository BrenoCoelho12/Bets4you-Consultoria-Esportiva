var Bets4you = Bets4you || {};

Bets4you.CarregarEquipesPorPais = (function(){
	
	function CarregarEquipesPorPais(){
		this.nacionalidadeMandante = $(".js-nacionalidade-mandante");
		this.nacionalidadeVisitante = $(".js-nacionalidade-visitante");
		this.mandante = $("#mandante");
		this.visitante = $("#visitante");
	}
	
	CarregarEquipesPorPais.prototype.iniciar = function() {
		this.nacionalidadeMandante.on('change', onChangeNacionalidadeMandante.bind(this));
		this.nacionalidadeVisitante.on('change', onChangeNacionalidadeVisitante.bind(this));
	}
	
	function onChangeNacionalidadeMandante() {
		/*nao entendo o porquê do ["0"]. Consegui identifica-lo fazendo console.log("this", this)
		 * Através dessa análise, consegui verificar que a forma de identificar o value do 
		 * <option> selecionado, utilizando o module pattern, foi o this.nacionalidadeMandante["0"].value
		 */
		var idNacionalidadeMandante = this.nacionalidadeMandante["0"].value;
		$.ajax({
			url: '/equipe/buscar',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ id: idNacionalidadeMandante }),
			success: onReturnEquipesMandante.bind(this),
			beforeSend: adicionarCsrfToken
		});

	}
	
	function onReturnEquipesMandante(equipes) {
		this.mandante.html('<option value="">Selecione</option>');
		$.each(equipes, carregarEquipes.bind(this));
		
		function carregarEquipes(id, equipe){
			this.mandante.append($('<option>', {
                value: equipe.id,
                text: equipe.nome
            }));
		}
		
	}
	
	function onChangeNacionalidadeVisitante() {
		
		var idNacionalidadeVisitante = this.nacionalidadeVisitante["0"].value; 
		
		$.ajax({
			url: '/equipe/buscar',
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ id: idNacionalidadeVisitante }),
			success: onReturnEquipesVisitante.bind(this),
			beforeSend: adicionarCsrfToken
		});

	}
	
	function onReturnEquipesVisitante(equipes) {
		this.visitante.html('<option value="">Selecione</option>');
		$.each(equipes, carregarEquipes.bind(this));
		
		function carregarEquipes(id, equipe){
			this.visitante.append($('<option>', {
                value: equipe.id,
                text: equipe.nome
            }));
		}

	}
	
	function adicionarCsrfToken(xhr) {
		var token = $('input[name=_csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		xhr.setRequestHeader(header, token);
	}

	return CarregarEquipesPorPais;
	
}());

$(function(){
	var carregarEquipesPorPais = new Bets4you.CarregarEquipesPorPais();
	carregarEquipesPorPais.iniciar();
		
});