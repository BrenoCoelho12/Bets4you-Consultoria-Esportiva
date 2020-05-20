var Bets4you = Bets4you || {};

Bets4you.MaskMoney = (function(){
	
	function MaskMoney(){
		this.decimal = $('.js-decimal');
		this.integer = $('.js-integer');
	}
	
	MaskMoney.prototype.enable = function(){
		this.decimal.maskMoney({ precision: 2, decimal:'.', thousands: ','}); //por default precision: 2
		this.integer.maskMoney({ precision: 0, thousands: ','});
	}
	
	return MaskMoney;
}());

$(function(){
	var maskMoney = new Bets4you.MaskMoney();
	maskMoney.enable();
});

