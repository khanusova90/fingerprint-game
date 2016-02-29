var canvas, context, material, offsetX, offsetY, circles;

function drawMap(){
	circles = [];
	canvas = document.getElementById("map");
	context = canvas.getContext("2d");
	var img = document.getElementById("imgsrc");
	
	context.drawImage(img, 0, 0);
	setMaterial();
	draw();
};

function setMaterial(){
	select = document.getElementById("material");
	material = select.options[select.selectedIndex].value;
};

function draw(){
	canvas.onclick = function(e){ 
	    x = e.pageX - canvas.offsetParent.offsetLeft - canvas.offsetLeft;
		y = e.pageY - canvas.offsetParent.offsetTop - canvas.offsetTop;
	    
		drawCircle(x,y);
		circles.push({"x": x, "y": y, "material": material});
	};
	
	function drawCircle(x, y){
		context.beginPath();
		context.arc(x, y, 10, 0, 2*Math.PI);
		
		var colour;
		switch(material){
		case "GOLD":
			colour = "#EAD00B";
			break;
		case "FOOD":
			colour = "#EA3B0B";
			break;
		case "WOOD":
			colour = "#90582C";
			break;
		case "STONE":
			colour = "#848484";
			break;
		default:
			colour = "#7C45BF";
		}
		
		context.fillStyle = colour;
		context.fill();
	};
};

function save(){
//	document.getElementById("circles").value = circles;
	$.ajax({
		headers: { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
	    },
		  method: "POST",
		  url: "./save",
		  dataType: "JSON",
		  data: JSON.stringify(circles)
	});
	
	document.getElementById("info").value = "Suroviny byly uloženy";
};