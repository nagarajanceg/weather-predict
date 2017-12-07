from flask import Flask, json, Response
import pandas as pd
import numpy as np
from statsmodels.tsa.arima_model import ARIMA
import requests
import datetime

app = Flask(__name__)

@app.route('/')
def home():
	return "hello world"
	
def predict(temp_list, last_date):
	temp_list = [float(i) for i in temp_list]
	ts = pd.Series(temp_list)

	ts.index = pd.to_datetime(ts.index,unit='D')
	model_ARIMA = ARIMA(ts, order=(4,1,0))
	results_ARIMA = model_ARIMA.fit(disp=0)

	forecast = results_ARIMA.forecast(steps=29)[0]
	forecast = forecast.round()
	print forecast

	date = datetime.datetime.strptime(last_date, '%m/%d/%Y')
	json_array = []
	for val in forecast:
		future_data_map = {}
		date += datetime.timedelta(days=1) 
		future_data_map['date'] = date
		future_data_map['temp'] = int(val)
		json_array.append(future_data_map)
	return json_array	


def prepare(data_map):
	tavg_list = []
	tmax_list = []
	tmin_list = []

	for item in data_map:
		tavg_list.append(item.values()[0])
		tmax_list.append(item.values()[4])
		tmin_list.append(item.values()[7])
	
	last_date = data_map[-1:][0].values()[6] 
	print last_date
	tavg_json = predict(tavg_list, last_date)
	tmax_json = predict(tmax_list, last_date)
	tmin_json = predict(tmin_list, last_date)
	
	prediction_jsons_list = []
		
	prediction_jsons_map = {}
	prediction_jsons_map['tavg'] = tavg_json
	prediction_jsons_map['tmax'] = tmax_json
	prediction_jsons_map['tmin'] = tmin_json

	prediction_jsons_list.append(prediction_jsons_map)
	return prediction_jsons_list


@app.route('/ModelGeneration/<cityIn>', methods=['GET','POST'])
def function(cityIn):
	response = requests.get('http://127.0.0.1:8080/getdata/1?city='+cityIn)
	print response.json()
	data_map = response.json()
	prediction_jsons_list = prepare(data_map)
	return Response(json.dumps(prediction_jsons_list), mimetype='application/json')

if __name__ == "__main__": 
	app.run(debug=True, host = '0.0.0.0')	