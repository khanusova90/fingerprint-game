function f1() {
    var token1 = $('input#csrf-token').attr("content");
    $.ajax({
        type: "POST",
        beforeSend: function (request)
	    {
	        request.setRequestHeader("X-CSRF-TOKEN", token1);
	    }
    });
}