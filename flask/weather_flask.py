from flask import Flask, json, Response
from keras.models import model_from_json
from sklearn.preprocessing import MinMaxScaler
import requests
import numpy
import pickle
import datetime
import yaml

app = Flask(__name__) #root path

@app.route('/') #assigning an URL to python method
def index():
	return 'This is homepage'

def predictWeather(data, scaler, weights, model_json, last_date):
	json_file = open(model_json, 'r')
	loaded_model_json = json_file.read()
	json_file.close()

	model = model_from_json(loaded_model_json)
	model.load_weights(weights)	

	for i in range(30):
		val = data[-1:]
		current = model.predict(val)
		current = current.reshape(1,1,1)
		data = numpy.concatenate((data, current), axis = 0)
		print data.shape

	data = scaler.inverse_transform(data.reshape(1,-1))
	data = data.ravel().round().tolist()
	future_data = data[-29:]

	date = datetime.datetime.strptime(last_date, '%m/%d/%Y')
	json_array = []
	for val in future_data:
		future_data_map = {}
		date += datetime.timedelta(days=1) 
		future_data_map['date'] = date
		future_data_map['temp'] = int(val)
		json_array.append(future_data_map)

	return json_array	

@app.route('/PredictWeather', methods=['GET','POST'])
def doWeatherForecast():
	config = readYAML()
	response = requests.get(config['predict_addr'])
	data_objects = []
	filename = 'data.pickle'
	with open(filename,'rb') as file:
		while True:
			try:
				data_objects = pickle.load(file)	
			except EOFError:
				break

	last_date = str(data_objects[4])
	tavg_json = predictWeather(data_objects[0][0], data_objects[1][0], data_objects[2][0], data_objects[3][0], last_date)
	tmax_json = predictWeather(data_objects[0][1], data_objects[1][1], data_objects[2][1], data_objects[3][1], last_date)
	tmin_json = predictWeather(data_objects[0][2], data_objects[1][2], data_objects[2][2], data_objects[3][2], last_date)
	
	prediction_jsons_list = []
	
	prediction_jsons_map = {}
	prediction_jsons_map['tavg'] = tavg_json
	prediction_jsons_map['tmax'] = tmax_json
	prediction_jsons_map['tmin'] = tmin_json

	prediction_jsons_list.append(prediction_jsons_map)
	return Response(json.dumps(prediction_jsons_list), mimetype='application/json') 	

def readYAML():
	with open('config.yaml') as file:
		return yaml.load(file)

if __name__ == "__main__": 
	app.run(debug=True, host = '0.0.0.0', port = 5001)	# start this program or web server