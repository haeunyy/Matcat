window.onload = function () {
    if (document.getElementById("byebye")) {
        const $element = document.getElementById("byebye");
        const $elementById = document.getElementById("loginSubmit");
        $elementById.onclick = function() {
            $element.submit();
        }
    }
}