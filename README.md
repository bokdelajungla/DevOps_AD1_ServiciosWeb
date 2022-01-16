# DevOps AD 1 - Servicios Web
Repositorio para la actividad 1 de DevOps sobre Servicios Web

## Enunciado
El objetivo es programar un servicio web. Este servicio web escuchará en el puerto 12345 y expondrá dos endpoints:

    * El primero recibe una cadena de caracteres, de longitud arbitraria, y la almacena en un fichero en disco.
    * El segundo recibirá una única palabra (sin espacios). Se devolverá el número total de las cadenas del citado fichero que la contengan, sin tener en cuenta:
        ** Mayúsculas (CADENA == Cadena).
        ** Posibles acentos (avión == Avion).
        ** Múltiples apariciones en la misma cadena cuentan como una única.

Como requisito, el fichero donde se guardan los datos se debe persistir en disco y leerlo al arrancar el proceso. Si no existe, se creará vacío.

Si bien la elección del lenguaje de programación podría ser abierta, se exige que sea uno de los que se han mencionado hasta ahora:
* Java
* Python

Por sencillez, se recomienda usar Python, con el framework Flask (Enlaces a un sitio externo.). Pero es perfectamente posible usar el servicio Java expuesto en la lección para este ejercicio.

## Implementación
Se ha optado por realizar una implementación del servicio usando Python y Flask mediante peticiones POST al servidor.\
Se ha considerado que como se está enviando información al servidor el verbo HTTP correcto es POST.\
Por tanto se recomienda emplear un programa como Postman o Curl para comprobar su funcionamiento.\ 

### Dependencias
* Python 3.7+
* Flask
  
### Ejecución
Para iniciar la palicación ejecutar el comando: python server.py\
Para terminar la aplicación pulsar Ctrl+C\

## Funcionamiento
Los dos endpoints son 
* /almacena
* /consulta

### Almacena
Para llevar a cabo el almacenamiento de una cadena en el fichero se debe realizar un POST e incluir el parámetro string con la cadena que queremos almacenar en el fichero:
  - ej: POST 127.0.0.1/almacena?string=Cadena+a+almacenar
 Si se emplea un verbo distinto de POST devolverá un error 405 Method Not Allowed.\
 Si no se incluye el parámetro "string" devolverá una respuesta 400 BAD REQUEST con un json en el cuerpo con información sobre el error (debe incluir el parámetro string).\
### Consulta
Para llevar a cabo la consulta de una palabra en el fichero se debe realizar un POST e incluir el parámetro string con la palabra que queremos almacenar en el fichero:
  - ej: POST 127.0.0.1/consulta?string=Cadena
 Si se emplea un verbo distinto de POST devolverá un error 405 Method Not Allowed.\
 Si no se incluye el parámetro "string" devolverá una respuesta 400 BAD REQUEST con un json en el cuerpo con información sobre el error (debe incluir el parámetro string).\
 Si se envía más de una palabra devolvera una respuesta 400 BAD REQUEST con un json en el cuerpo con información sobre el error (debe enviar una única palabra).\

## Autores ✒️

Los componentes del grupo:

* **Antonio De Gea Velasco**
* **Adrian Rodriguez Montesinos**
* **Jorge Sánchez-Alor Expósito**
