package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.*;

public class testSecundario {

	
	@Test
	public void agregarUnAlumnoASecundario() {
		String nombre = "Unlam";			
		Institucion institucion = new Institucion(nombre);
		Secundaria secundaria=institucion.getSecundaria();
		Anio anio1=secundaria.getAnios().get(0);
		Alumno alumno=new Alumno("Juan", 1111111, 12);
		//HistorialAcademico historial=alumno.getHistorial();
		//historial.setPrimaria(true);
		//System.out.println(historial.isPrimaria());
		assertTrue(anio1.agregarAlumno(alumno));
	}
	

	
	@Test	
	public void incorporarUnDocenteALaInstitucion(){
		Docente nuevo = new Docente("Melina", 11111111);
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		
		Boolean validacion = ins.incoportarDocente(nuevo);
		assertTrue(validacion);		
	}
}
