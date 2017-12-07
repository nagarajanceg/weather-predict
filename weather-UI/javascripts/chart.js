function load_chart(array, deg, tempType, dayCount, city) {
	console.log("in chart");

	console.log(array);

	console.log("city",city);

	ny_temp = [];
	bin_temp = [];
	sf_temp = [];

	if(tempType == "TMIN"){
		ny_temp = array[2].data[0].tmin;
		bin_temp = array[1].data[0].tmin;
		sf_temp = array[0].data[0].tmin;
	}else if(tempType == "TMAX"){
		ny_temp = array[2].data[0].tmax;
		bin_temp = array[1].data[0].tmax;
		sf_temp = array[0].data[0].tmax;
	}else{
		ny_temp = array[2].data[0].tavg;
		bin_temp = array[1].data[0].tavg;
		sf_temp = array[0].data[0].tavg;
	}


	ny_dataPts = [];
	
	var todaysDate = new Date();
	for (i = 0; i < dayCount; i++) { 
		ny_obj = {};
    	ny_obj["x"] = new Date(ny_temp[i].date);

    	if(deg == "C"){
    		ny_obj["y"] = (ny_temp[i].temp - 32)*(5/9);
    	}else{
    		ny_obj["y"] = ny_temp[i].temp;
    	}

    	if(city == "New_York"){
    		if(ny_obj["x"].setHours(0,0,0,0) == todaysDate.setHours(0,0,0,0)) {
    			deg_txt =  Math.round( ny_obj["y"]);
    			$('#t-dig').html(deg_txt);
			}
    	}
    	
    	ny_dataPts.push(ny_obj);
	}

	bin_dataPts = [];

	for (i = 0; i < dayCount; i++) { 
		bin_obj = {};
    	bin_obj["x"] = new Date(bin_temp[i].date);

    	if(deg == "C"){
    		bin_obj["y"] = (bin_temp[i].temp - 32)*(5/9);
    	}else{
    		bin_obj["y"] = bin_temp[i].temp;
    	}	

    	if(city == "Binghamton"){
    		if(bin_obj["x"].setHours(0,0,0,0) == todaysDate.setHours(0,0,0,0)) {
    			deg_txt_bin =  Math.round( bin_obj["y"]);
    			$('#t-dig').html(deg_txt_bin);
			}
    	}

    	bin_dataPts.push(bin_obj);
	}

	sf_dataPts = [];

	for (i = 0; i < dayCount; i++) { 
		sf_obj = {};
    	sf_obj["x"] = new Date(sf_temp[i].date);
    	if(deg == "C"){
    		sf_obj["y"] = (sf_temp[i].temp - 32)*(5/9);
    	}else{
    		sf_obj["y"] = sf_temp[i].temp;
    	}

    	if(city == "San_Francisco"){
    		if(sf_obj["x"].setHours(0,0,0,0) == todaysDate.setHours(0,0,0,0)) {
    			console.log("in sf");
    			deg_txt_sf =  Math.round( sf_obj["y"]);
    			$('#t-dig').html(deg_txt_sf);
			}
    	}

    	sf_dataPts.push(sf_obj);
	}

	//console.log("ny-dp: ", ny_dataPts);


var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	title:{
		text: "Weather Predictor - "+tempType
	},
	axisX: {
		valueFormatString: "DD MMM,YY"
	},
	axisY: {
		title: "Temperature (in °"+deg+")",
		includeZero: false,
		suffix: " °"+deg
	},
	legend:{
		cursor: "pointer",
		fontSize: 16,
		itemclick: toggleDataSeries
	},
	toolTip:{
		shared: true
	},
	data: [{
		name: "New York",
		type: "spline",
		yValueFormatString: "#0.## °"+deg,
		showInLegend: true,
		dataPoints: ny_dataPts
	},
	{
		name: "Binghamton",
		type: "spline",
		yValueFormatString: "#0.## °"+deg,
		showInLegend: true,
		dataPoints: bin_dataPts
	},
	{
		name: "San Francisco",
		type: "spline",
		yValueFormatString: "#0.## °"+deg,
		showInLegend: true,
		dataPoints: sf_dataPts
	}

	]
});
chart.render();

function toggleDataSeries(e){
	if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
		e.dataSeries.visible = false;
	}
	else{
		e.dataSeries.visible = true;
	}
	chart.render();
}

}