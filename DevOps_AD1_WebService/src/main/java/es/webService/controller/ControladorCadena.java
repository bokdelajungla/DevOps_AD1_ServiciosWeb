package es.webService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.webService.model.persistence.DaoCadena;

@RestController
public class ControladorCadena {
	
	// CRUD Incompleto -> Solo Create y Update
	
	// Inyectamos el DaoCadena
	@Autowired
	private DaoCadena daoCadena;
	
		// CREATE/UPDATE. Url de acceso: http://localhost:12345/consultar?string={cadena} y el metodo POST
		
		@PostMapping(path="consultar", consumes = MediaType.TEXT_PLAIN_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Integer> consulta(@RequestParam(value="string") String cadena) {
			System.out.println("Controlador -> Buscando coindicencias con la cadena: " + cadena);
			int numCad = daoCadena.buscarCadena(cadena);
			if(numCad == -1) {
				// Si no hay texto con el que comparar: retornamos 404 - NOT FOUND
				return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND); 
			} else if(numCad > 0) {
				// Si se encuentra al menos una cadena: retornamos 200 - OK
				return new ResponseEntity<Integer>(numCad, HttpStatus.OK); 
			} else {
				// Si no se encuentra ninguna coincidencia: retornamos 204 - NO CONTENT
				return new ResponseEntity<Integer>(numCad, HttpStatus.NO_CONTENT); 
			}
		}
		
		/* EJEMPLO CON RESPONSE BODY (Para mensaje de respuesta en el cuerpo de la página)
		@PostMapping(value = "/consultar")
		@ResponseBody
		public String consulta(@RequestParam(value="string") String string) {
			System.out.println("Controlador -> Buscando coindicencias con la cadena: " + string);
			int numCad = daoCadena.buscarCadena(string);
			if(numCad == -1) {
				// Si no hay texto con el que comparar: retornamos 404 - NOT FOUND
				return "[404 NOT FOUND]"; 
			} else if(numCad > 0) {
				// Si se encuentra al menos una cadena: retornamos 200 - OK
				return "[200 OK] " + numCad; 
			} else {
				// Si no se encuentra ninguna coincidencia: retornamos 204 - NO CONTENT
				return "[204 NOT FOUND] " + numCad; 
			}
		}
		*/
		
		// CREATE/UPDATE. Url de acceso: http://localhost:12345/almacena?={texto} y el metodo POST
		@PostMapping(path="almacena", consumes = MediaType.TEXT_PLAIN_VALUE, 
				produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> almacenar(@RequestParam(value="string") String cadena) {
			System.out.println("Controlador -> Almacenamos el texto: " + cadena);
			int err = daoCadena.almacenarTxt(cadena);
			if (err == 0) {
				// Si se han escrito los datos en el fichero: retornamos 200 - OK
				return new ResponseEntity<String>("Texto almacenado: " + daoCadena.getTxt(), HttpStatus.OK); 
			} else {
				// Si no se encuentra el fichero: retornamos 404 - NOT FOUND
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND); 
			}
		}
		
		/* EJEMPLO CON RESPONSE BODY (Para mensaje de respuesta en el cuerpo de la página)
		@PostMapping(value = "/almacena")
		@ResponseBody
		public String almacena(@RequestParam(value="string") String string) {
			System.out.println("Controlador -> Almacenamos el texto: " + string);
			int err = daoCadena.almacenarTxt(string);
			if (err == 0) {
				// Si se han escrito los datos en el fichero: retornamos 200 - OK
				return "[200 OK]";
			} else {
				// Si no se encuentra el fichero: retornamos 404 - NOT FOUND
				return "[404 NOT FOUND]";
			}
		}
		 */
		// README. Url de acceso: http://localhost:12345/readme y el metodo POST
		@GetMapping(path = "/readme", produces = MediaType.TEXT_HTML_VALUE)
		public String readmeHTML() {
		// Uso del String buffer para concatenar Strings
			StringBuffer buffer = new StringBuffer();
			buffer.append("<!DOCTYPE html>");
			buffer.append("<html>");
			buffer.append("<head>");
			buffer.append("<title>Readme</title>");
			buffer.append("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'>");
			buffer.append("</head>");
			buffer.append("<body style='background-color: #def6ff'>");
			buffer.append("<div class=\"container m-4\">");
			buffer.append("<h1 class=\"text-center text-primary\">INSTRUCCIONES</h1>");
			buffer.append("<div class=\"list-group mt-4\">");
			buffer.append("<li class=\"list-group-item list-group-item-action active\" aria-current=\"true\">Llamadas:</li>");
			buffer.append("<li class=\"list-group-item list-group-item-action\">Consultar una cadena. Ejemplo: http://localhost:12345/consultar?string=hola método POST</li>");
			buffer.append("<li class=\"list-group-item list-group-item-action\">Almacenar un texto. Ejemplo: http://localhost:12345/almacena?string=Hola+Mundo+! método POST</li>");
			buffer.append("<li class=\"list-group-item list-group-item-action\">Salir: http://localhost:12345/salir método GET</li>");
			buffer.append("<li class=\"list-group-item list-group-item-action\">Salir sin confirmación: http://localhost:12345/salir/true método GET</li>");
			buffer.append("</div>");
			buffer.append("</div>");
			buffer.append("</body>");
			buffer.append("</html>");
		
			return buffer.toString();
		}
		
		// README. Url de acceso: http://localhost:12345/readme y el metodo GET
		@GetMapping(path = "/salir", produces = MediaType.TEXT_HTML_VALUE)
		public String stopService() {
		// Uso del String buffer para concatenar Strings
			StringBuffer buffer = new StringBuffer();
			buffer.append("<!DOCTYPE html>");
			buffer.append("<html>");
			buffer.append("<head>");
			buffer.append("<title>Salir</title>");
			buffer.append("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'>");
			buffer.append("</head>");
			buffer.append("<body style='background-color: #def6ff'>");
			buffer.append("<div class=\"container m-4\">");
			buffer.append("<h1 class=\"text-center text-primary\">¿Desea Salir?</h1>");
			buffer.append("<div class=\"container mt-2\">");
			buffer.append("<div class=\"row\">");
			buffer.append("<a class=\"col-sm btn btn-outline-success m-2\" href=\"http://localhost:12345/salir/true\" role=\"button\">Si</a>");			
			buffer.append("<a class=\"col-sm btn btn-outline-danger m-2\" href=\"http://localhost:12345/readme\" role=\"button\">No</a>");
			buffer.append("</div>");
			buffer.append("</div>");
			buffer.append("</div>");
			buffer.append("</body>");
			buffer.append("</html>");
		
			return buffer.toString();
		}
		
		// STOP SERVICE. Url de acceso: http://localhost:12345/exit y el metodo GET
		@GetMapping(path = "/salir/true")
		public void exit() {
			daoCadena.pararServicio();
		}
}
