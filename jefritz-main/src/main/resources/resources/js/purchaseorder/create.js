$(document).ready(function() {
	$('.product').change(
			function(e){
				var price = getPrice($(this).val());
				alert(price);
				$(this).parent().next().contents().filter('.price').val(price);
			}
	);
});

function getPrice(productId){
	var price = null;
	$.each(priceList, function(index, obj){
		if (obj.product.productId === productId){
			price = obj.price;
			return false;
		}
		return true;
	});
	return price;
}

