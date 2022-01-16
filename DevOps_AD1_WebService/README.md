# AD-1 SERVICIOS WEB

## Descripción

Aplicación Java que proporciona un servicio web con dos endpoints. 

> El servidor corre en el puerto 12345.
> ![img-port](https://github.com/bokdelajungla/DevOps_AD1_ServiciosWeb/blob/adri/DevOps_AD1_WebService/imgs/port.png)

### Endpoint1

Almacena una cadena de texto en un fichero (guardado dentro del directorio del proyecto). Uso del verbo POST.
> Llamada: http://localhost:12345/almacena?string={texto}
Respuestas HTTP al cliente:

- 200 - OK si se ha almacenado el texto correctamente
- 404 - NOT FOUND si no se encuentra el fichero donde almacenar el texto


### Endpoint2

Almacena una cadena de texto en un fichero (guardado dentro del directorio del proyecto). Uso del verbo POST.
> Llamada: http://localhost:12345/consultar?string={palabra}
Respuestas HTTP al cliente:

- 200 - OK si se encuentra la palabra en el texto
- 204 - NO CONTENT si no se ha encontrado la palabra en el texto
- 404 - NOT FOUND si no se ha introducido un texto donde buscar la cadena

## Instrucciones

- Podemos acceder a las instrucciones del sericio mediante la llamada http://localhost:12345/readme (verbo GET).
- Para salir del servicio: http://localhost:12345/salir (método GET).

## Requerimientos

- Spring Boot version 2.6+

> Es posible descargarla desde el url: https://spring.io/tools
> ![img-spring1](https://github.com/bokdelajungla/DevOps_AD1_ServiciosWeb/blob/adri/DevOps_AD1_WebService/imgs/spring_install1.png)

> O bien, dede Eclipse Marketplace (en caso de usar dicho IDE)
> ![img-spring2](https://github.com/bokdelajungla/DevOps_AD1_ServiciosWeb/blob/adri/DevOps_AD1_WebService/imgs/spring_install2.png)

- Maven version 3.3+
> Descargarla desde el url: https://maven.apache.org/download.cgi

- Java version 11+

## Versiones del proyecto

![img-spring2](https://github.com/bokdelajungla/DevOps_AD1_ServiciosWeb/blob/adri/DevOps_AD1_WebService/imgs/versions.png)
