window.onload = function(){	
	if(document.getElementById("registProduct")) {
        const $registProduct = document.getElementById("registProduct");
        $registProduct.onclick = function() {
            location.href = "/product/regist";
        }
    }
}
