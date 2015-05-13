$(document).ready(function() {
	
	// populates price with current default every time the product is selected
	$('.product').change(
			function(e){
				var price = getPrice($(this).val());
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

