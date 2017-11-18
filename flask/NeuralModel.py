from flask import Flask, jsonify, send_file
import pandas
import numpy
import math
import requests
import pickle
import matplotlib.pyplot as plt
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LSTM
from sklearn.preprocessing import MinMaxScaler
from sklearn.metrics import mean_squared_error

app = Flask(__name__) #root path
tavg_model = None
tmax_model = None
tmin_model = None
last_date = None
last_element = []
scaler = []

def create_dataset(dataset, look_back=1):
	dataX, dataY = [], []
	for i in range(len(dataset)-look_back):
		a = dataset[i:(i+look_back), 0]
		dataX.append(a)
		dataY.append(dataset[i + look_back, 0])
	return numpy.array(dataX), numpy.array(dataY)

def generateModelHelper(weather_list):
	# fix random seed for reproducibility
	numpy.random.seed(7)

	# load the dataset
	dataframe = pandas.DataFrame(numpy.array(weather_list).reshape(len(weather_list),1))
	dataset = dataframe.values
	dataset = dataset.astype(float)

	# normalize the dataset
	scaler = MinMaxScaler(feature_range=(0, 1))
	# print scaler.data_max_
	dataset = scaler.fit_transform(dataset)

	# reshape into X=t and Y=t+1
	look_back = 1
	trainX, trainY = create_dataset(dataset, look_back)
	# reshape input to be [samples, time steps, features]
	trainX = numpy.reshape(trainX, (trainX.shape[0], 1, trainX.shape[1]))

	# create and fit the LSTM network
	model = Sequential()
	model.add(LSTM(4, input_shape=(1, look_back)))
	model.add(Dense(1))
	model.compile(loss='mean_squared_error', optimizer='adam')
	model.fit(trainX, trainY, epochs=1, batch_size=1, verbose=2)
	return model, trainX[-1:], scaler

@app.route('/ModelGeneration', methods=['GET','POST'])
def generateModel():
	global tavg_model
	global tmin_model
	global tmax_model
	global last_element
	global scaler
	global last_date

	response = requests.get('http://127.0.0.1:5002/')
	data_map = response.json()
	tavg_list = []
	tmax_list = []
	tmin_list = []

	for item in data_map:
		tavg_list.append(item.values()[0])
		tmax_list.append(item.values()[3])
		tmin_list.append(item.values()[6])
	
	last_date = data_map[-1:][0].values()[5] 
	tavg_model, tavg_last_element, tavg_scaler = generateModelHelper(tavg_list)
	tmax_model, tmax_last_element, tmax_scaler = generateModelHelper(tmax_list)
	tmin_model, tmin_last_element, tmin_scaler = generateModelHelper(tmin_list)
	last_element.append(tavg_last_element)
	last_element.append(tmax_last_element)
	last_element.append(tmin_last_element)

	scaler.append(tavg_scaler)
	scaler.append(tmax_scaler)
	scaler.append(tmin_scaler)
	return "success"
	
def writeModelToFile(modeljson, model):
	with open(modeljson, "w") as json_file:
		json_file.write(model.to_json())
	json_file.close()

@app.route('/GetModels')
def getModels():
	data_array = []
	weights = []
	models_json = []
	data_array.append(last_element)
	data_array.append(scaler)
	
	tavg_model_json = 'tavg_model.json'
	tmax_model_json = 'tmax_model.json'
	tmin_model_json = 'tmin_model.json'
	writeModelToFile(tavg_model_json, tavg_model)
	writeModelToFile(tmax_model_json, tmax_model)
	writeModelToFile(tmin_model_json, tmin_model)
	
	models_json.append(tavg_model_json)
	models_json.append(tmax_model_json)
	models_json.append(tmin_model_json)

	tavg_model_weights = 'tavg_model_weights.h5'
	tavg_model.save_weights(tavg_model_weights)
	tmax_model_weights = 'tmax_model_weights.h5'
	tmax_model.save_weights(tmax_model_weights)
	tmin_model_weights = 'tmin_model_weights.h5'
	tmin_model.save_weights(tmin_model_weights)
	

	weights.append(tavg_model_weights)
	weights.append(tmax_model_weights)
	weights.append(tmin_model_weights)

	data_array.append(weights)
	data_array.append(models_json)
	data_array.append(last_date)

	filename = 'data.pickle'
	with open(filename,'wb') as file:
		pickle.dump(data_array,file)
	file.close()
	return send_file(filename, mimetype='application/octet-stream')

if __name__ == "__main__": 
	app.run(debug=True, host = '0.0.0.0', port = 5000)	# start this program or web server