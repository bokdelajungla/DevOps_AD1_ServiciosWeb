'''
Servicio Web que escucha en el puerto 12345
y tiene dos endpoints
una que guarda la cadena que se le envia como parámetro en un fichero
y otro que devuelve el número de veces de una cadena aparece al menos una vez
en cada una de las lineas del fichero, ignorando mayúsculas y tildes

@autor: Jorge Sánchez-Alor

'''

# ***IMPORTS*** #
# Empleamos la biblioteca FLASK para implementar el servicio web
from flask import Flask
from flask import request
from flask import make_response
from flask import jsonify
# Hacemos uso de la biblioteca unicodedata para tratar las tildes y caracteres epeciales
import unicodedata

# Creamos la aplicación a partir de la clase Flask
app = Flask(__name__)

# Comprobamos que el fichero de persistencia existe y si no lo creamos
file = open("cadenas.txt", 'a')
file.close()


# Con @app.route() marcamos el comportamiento que llevará a cabo nuestra aplicación
@app.route("/")
def hello_world():
    return "<h1>Servicio Web para Cadenas</h1><br>"


# El endpoint "almacena"
'''
El endpoint recibe la cadena como parámetro y la guarda en una nueva línea del fichero cadenas.txt
El nombre del parámetro es 'string'
la petición tiene el formato /almacena?string
Devuelve: 
    * una respuesta HTML 200 OK con un json en el cuerpo indicando que el mensaje se ha creado correctamente
    * una respuesta HTML 400 BAD REQUEST con un json en el cuerpo si la petición no es correcta
'''


@app.route("/almacena", methods=['POST'])
def almacenar():
    if request.method == 'POST':
        if 'string' in request.args:
            cadena = request.args.get('string')
            with open("cadenas.txt", "a+") as f:
                f.write(cadena + '\n')
            data = {'code': 'SUCCES', 'message': cadena + ' ADDED'}
            return make_response(jsonify(data), 200)
        else:
            data = {'code': 'BAD REQUEST', 'message': 'No se ha encontrado el parámetro "string"'}
            return make_response(jsonify(data), 400)


# El endpoint "consulta"
'''
El endpoint recibe la cadena como parámetro y comprueba el número de veces que aparece dentro del fichero
cadenas.txt
El nombre del parámetro es 'string'
Devuelve:
Devuelve: 
    * una respuesta HTML 200 OK con un json en el cuerpo indicando el número de veces que se ha encontrado la palabra
    * una respuesta HTML 400 BAD REQUEST con un json en el cuerpo si la petición no es correctauna respuesta
'''


@app.route("/consulta", methods=['POST'])
def consultar():
    if request.method == 'POST':
        if 'string' in request.args:
            cadena = request.args.get('string')
            if " " not in cadena:
                with open("cadenas.txt", "r") as f:
                    contador = 0
                    for linea in f:
                        '''
                        Usamos unicodedata.normalize() para eliminar las tildes
                        con la opcion NFKD para que lo descomponga en caracteres simples + símbolos aditivos
                        lo codificamos a ASCII teniendo en cuenta sólo los caracteres simples (encode('ASCII','ignore'),
                        y lo convertimos de nuevo en cadena (decode('ASCII'))
                        Por último, usamos el método casefold() para ignorar mayúsculas
                        '''
                        cadenaAux = unicodedata.normalize('NFKD', cadena).encode('ASCII', 'ignore').decode(
                            'ASCII').casefold()
                        lineaAux = unicodedata.normalize('NFKD', linea).encode('ASCII', 'ignore').decode(
                            'ASCII').casefold()
                        if cadenaAux in lineaAux:
                            contador = contador + 1
                data = {'code': 'SUCCES', 'Lineas en las que aparece': contador}
                return make_response(jsonify(data), 200)
            else:
                data = {'code': 'BAD REQUEST', 'message': 'El parámetro debe ser una única palabra'}
                return make_response(jsonify(data), 400)
        else:
            data = {'code': 'BAD REQUEST', 'message': 'No se ha encontrado el parámetro string'}
            return make_response(jsonify(data), 400)


# Para que se inicie la aplicación al ejecutar el script
if __name__ == "__main__":
    app.run(host='127.0.0.1', port=12345)
