var canvas, context, offsetX, offsetY, circles;

function drawMap(){
	canvas = document.getElementById("map");
	context = canvas.getContext("2d");
	var img = document.getElementById("imgsrc");
	
	context.drawImage(img, 0, 0);
	draw();
};

function draw(){
	canvas.onclick = function(e){ 
	    x = e.pageX - canvas.offsetParent.offsetLeft - canvas.offsetLeft;
		y = e.pageY - canvas.offsetParent.offsetTop - canvas.offsetTop;
	    
		drawCircle(x,y);
	};
	
	function drawCircle(x, y){
		context.beginPath();
		context.arc(x, y, 10, 0, 2*Math.PI);
		context.fillStyle = "#FF0000";
		context.fill();
	};
};

//window.onload = function () {
//	var map = document.getElementById("map");
//	map.setAttribute('height', 500);
//	map.setAttribute('width', 500);
//
//	map.onclick = function(e){
//		var x = e.pageX - this.offsetLeft;
//		var y = e-pageY - this.offsetTop;
//		var ctx = map.getContext("2d");
//		drawCircle(x, y, ctx);
//	}
//}
//
//function drawCircle(x, y, ctx){
//	ctx.fillStyle = "black";
//	ctx.beginPath();
//	ctx.moveTo(x,y);
//	ctx.arc(x, y, 5, 0, Math.PI * 2, false);
//	ctx.fill();
//}

//jQuery(document).ready(function() {
//  $('#map').attr('height', $('#map').css('height'));
//  $('#map').attr('width', $('#map').css('width'));
//
//  $("#map").click(function(e) {
//
//    var x = e.clientX - this.offsetLeft - this.scrollLeft;
//    var y = e.clientY - this.offsetTop - this.scrollTop;
////	  var x = e.pageX;
////	  var y = e.pageY;
//    
//    //var pole = [];
//    //pole.push[{
//
//    var ctx = this.getContext("2d");
//    ctx.beginPath();
//    ctx.arc(x, y, 10, 0, 2 * Math.PI);
//    ctx.stroke();
//    
//    //json object vratit
//    //musi se jmenovat stejne
//  });
//  
//  //na kliknuti na odkaz mapy zobrazit konkretni patro 
//  
//  //podivat se na metodu drawImage()
//})
