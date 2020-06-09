var Bets4you = Bets4you || {};

Bets4you.GraficoRentabilidadeNoMes = (function() {

    function GraficoRentabilidadeNoMes() {
        now = new Date;
        this.meses = new Array ("janeiro", "fevereiro", "março", "abril", "Maio", "junho", "agosto", "outubro", "novembro", "dezembro")
        this.rentabilidadePorDia = $('#graficoRentabilidadePorDia')[0].getContext('2d');
        this.rentabilidadeDiaADia = $('#graficoRentabilidadeDiaADia')[0].getContext('2d');
        this.mesAtual = this.meses[now.getMonth()].toUpperCase();

    }

    GraficoRentabilidadeNoMes.prototype.iniciar = function() {
        $.ajax({
            url: '/rentabilidadePorDia',
            method: 'GET',
            success: graficoRentabilidadePorDia.bind(this)
        });

        $.ajax({
            url: '/rentabilidadeDiaADia',
            method: 'GET',
            success: graficoRentabilidadeDiaADia.bind(this)
        });
    }

    function graficoRentabilidadePorDia(rentabilidadePorDia){
        var dias = Object.keys(rentabilidadePorDia);
        var rentabilidades = Object.values(rentabilidadePorDia);

        var graficoRentabilidadeNoMes = new Chart(this.rentabilidadePorDia, {
            type: 'bar',
            data: {
                labels: dias,
                datasets: [{
                    label: 'Ganhos diários por dia em cima da banca em ' + this.mesAtual + ' (valores em %)',
                    backgroundColor: "rgba(26,179,148,0.5)",
                    pointBorderColor: "rgba(26,179,148,1)",
                    pointBackgroundColor: "#fff",
                    data: rentabilidades
                }]
            },
        });

    }

    function graficoRentabilidadeDiaADia(rentabilidadeDiaADia){
        var dias = Object.keys(rentabilidadeDiaADia);
        var rentabilidades = Object.values(rentabilidadeDiaADia);

        var graficoRentabilidadeDiaADia = new Chart(this.rentabilidadeDiaADia, {
            type: 'line',
            data: {
                labels: dias,
                datasets: [{
                    label: 'Rentabilidade dia a dia em ' + this.mesAtual + ' (valores em %)',
                    backgroundColor: "rgba(26,179,148,0.5)",
                    pointBorderColor: "rgba(26,179,148,1)",
                    pointBackgroundColor: "#fff",
                    data: rentabilidades
                }]
            },
        });

    }


    function totalDiasNoMesAtual(ano, mes) {
        let date = new Date(ano, mes, 0);

        let totalDiasNoMes = date.getDate();
        var arrayDiasDoMes = [];
        for(let i = 1;i<=totalDiasNoMes;i++){
            arrayDiasDoMes.push(i);
        }
        return arrayDiasDoMes;
    }

    return GraficoRentabilidadeNoMes;

}());

$(function() {
    var graficoRentabilidadeNoMes = new Bets4you.GraficoRentabilidadeNoMes();
    graficoRentabilidadeNoMes.iniciar();
});