window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	title:{
		text: "Weather Prediction"
	},
	axisX: {
		valueFormatString: "DD MMM,YY"
	},
	axisY: {
		title: "Temperature (in °F)",
		includeZero: false,
		suffix: " °F"
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
		name: "Binghamton",
		type: "spline",
		yValueFormatString: "#0.## °F",
		showInLegend: true,
		dataPoints: [
			{ x: new Date(2017,6,24), y: 31 },
			{ x: new Date(2017,6,25), y: 31 },
			{ x: new Date(2017,6,26), y: 29 },
			{ x: new Date(2017,6,27), y: 29 },
			{ x: new Date(2017,6,28), y: 31 },
			{ x: new Date(2017,6,29), y: 30 },
			{ x: new Date(2017,6,30), y: 29 }
		]
	},
	{
		name: "New York",
		type: "spline",
		yValueFormatString: "#0.## °F",
		showInLegend: true,
		dataPoints: [
			{ x: new Date(2017,6,24), y: 20 },
			{ x: new Date(2017,6,25), y: 20 },
			{ x: new Date(2017,6,26), y: 25 },
			{ x: new Date(2017,6,27), y: 25 },
			{ x: new Date(2017,6,28), y: 25 },
			{ x: new Date(2017,6,29), y: 25 },
			{ x: new Date(2017,6,30), y: 25 }
		]
	},
	{
		name: "San Francisco",
		type: "spline",
		yValueFormatString: "#0.## °F",
		showInLegend: true,
		dataPoints: [
			{ x: new Date(2017,6,24), y: 22 },
			{ x: new Date(2017,6,25), y: 19 },
			{ x: new Date(2017,6,26), y: 23 },
			{ x: new Date(2017,6,27), y: 24 },
			{ x: new Date(2017,6,28), y: 24 },
			{ x: new Date(2017,6,29), y: 23 },
			{ x: new Date(2017,6,30), y: 23 }
		]
	}]
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