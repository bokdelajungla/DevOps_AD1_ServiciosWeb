package es.webService.model.persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DaoCadena {

	private String cad;
	private String txt;
	private String auxTxt;
	private String path;
	private String[] cadenas;
	private File file;
	private FileWriter fw;
	private BufferedWriter bw;
	
	@Autowired
	private ApplicationContext context;
	
	public DaoCadena() {
		// Ruta utilizada
		path = "texto.txt";
		
		// Creamos el fichero
			file = new File(path);
			if(!file.exists()) {
				System.out.println("DaoCadena -> Se ha creado el fichero");
			} else {
				System.out.println("DaoCadena -> El fichero ya existe, se procede a usarlo");
			}
	}
	
	public int almacenarTxt(String texto) {
		if(file != null) {
			// Generamos el texto y el array de cadenas correctamente
			txt = generarTxt(texto);
			
			// Abrimos el fichero, el buffer y escribimos
			try {
				fw = new FileWriter(path);
				bw = new BufferedWriter(fw);
				bw.write(txt);			
				System.out.println("DaoCadena -> Se ha escrito en el fichero");
				bw.close();
				fw.close();
			} catch (IOException e) {
				System.out.println("DaoCadena -> Error al acceder/escribir/cerrar el fichero");
				e.getMessage();
			}
			return 0;
		} else {				
			System.out.println("DaoCadena -> Se ha borrado el fichero");
			return -1;
		}
		
	}
	
	public int buscarCadena(String cad) {
		if(hayTexto()) {
			String auxCad = Normalizer.normalize(cad, Normalizer.Form.NFD);
			auxCad = auxCad.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
			// Hacemos uso únicamente de la primera palabra por ello utilizamos un split y el primer elemento del array
			String[] aux_cad = txt.split(" ");
			this.cad = aux_cad[0];
			// Contador de cadenas encontradas en el texto
			int num = 0;
			for(int i = 0; i < cadenas.length; i++) {
				if(this.cad.toUpperCase().equals(cadenas[i].toUpperCase())) {
					num++;
				}
			}
			System.out.println("DaoCadena -> Se han encontrado " + num + " coincidencias");
			return num;
		} else {
			System.out.println("DaoCadena -> No hay texto con que comprarar!");
			return -1;
		}
	}
	
	private boolean hayTexto() {
		if (!(txt == null)) {
			return true;
		} else {
			return false;
		}
	} 
	
	private String generarTxt(String txt) {
		cadenas = txt.split(" ");	
		for(int i = 0; i < cadenas.length; i++) {
			txt.concat(cadenas[i] + " ");
		}
		auxTxt = Normalizer.normalize(txt, Normalizer.Form.NFD);
		auxTxt = auxTxt.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		return txt.strip();
	}
	
	public void pararServicio() { 
		System.out.println("DaoCadena -> Servidor cerrado");
		SpringApplication.exit(context, () -> 0);
	}
	
	public String getTxt() {
		return txt;
	}
	
}
