package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.*;

public class testSecundario {

	
	@Test
	public void agregarUnAnioASecundario() {
		String nombre = "Unlam";			
		Institucion institucion = new Institucion(nombre);
		Secundaria secundaria=institucion.getSecundaria();
		Anio anio1=new Anio(Numero.PRIMERO);
		assertTrue(secundaria.crearAnios(anio1));
	}
	
	@Test
	public void verificarQueUnAlumnoHayaPasadoASecundaria() {
		HistorialAcademico historial= new HistorialAcademico(true);
		Alumno alumno=new Alumno("Juan", 1111111, 12);
		alumno.setHistorial(historial);
		historial.setPrimaria(true);
		assertTrue(historial.isPrimaria());
	}
	
	@Test
	public void agregarUnAlumnoAPrimerAnioEnSecundario() {
		String nombre = "Unlam";			
		Institucion institucion = new Institucion(nombre);
		Secundaria secundaria=institucion.getSecundaria();
		Anio anio1=new Anio(Numero.PRIMERO);
		secundaria.crearAnios(anio1);	
		HistorialAcademico historial= new HistorialAcademico(true);
		Alumno alumno=new Alumno("Juan", 1111111, 12);
		alumno.setHistorial(historial);
		for(int i=0;i<historial.getAnio().length;i++) {
			historial.getAnio()[i]=false;
		}
		historial.setPrimaria(true);
		System.out.println(anio1.agregarAlumno(alumno));
		assertEquals(anio1.getAlumnos().get(0),alumno);
	}

	@Test
	public void agregarUnAlumnoAUnSegundoAnioEnSecundario() {		
		Institucion institucion = new Institucion("Unlam");
		Secundaria secundaria=institucion.getSecundaria();
		Anio anio1=new Anio(Numero.SEGUNDO);
		secundaria.crearAnios(anio1);	
		HistorialAcademico historial= new HistorialAcademico(true);
		Alumno alumno=new Alumno("Juan", 1111111, 12);
		alumno.setHistorial(historial);
		historial.getAnio()[0]=true;
		for(int i=1;i<historial.getAnio().length;i++) {
			historial.getAnio()[i]=false;
		}
		historial.setPrimaria(true);
		System.out.println(anio1.agregarAlumno(alumno));
		assertEquals(anio1.getAlumnos().get(0),alumno);
	}
	
	
	@Test 
	public void agregarUnDocenteAUnaMateria() {
		Anio anio1=new Anio(Numero.SEGUNDO);
		Docente docente = new Docente("Nicolas",11111);
		HistorialAcademico historialDocente = new HistorialAcademico();
		Materia quimica = new Materia("quimica");
		historialDocente.getMaterias()[0]=quimica;
		docente.setExperiencia(historialDocente);
		anio1.asignarDocente(docente, quimica);
		assertEquals(quimica.getDocente(), docente);
	}
	
	
	/*@Test	
	public void agregarUnaMateriaAUnAnio(){
		Institucion institucion = new Institucion("Unlam");
		Secundaria secundaria=institucion.getSecundaria();
		Anio anio1=new Anio(Numero.SEGUNDO);
		secundaria.crearAnios(anio1);
		Docente docente = new Docente("Nicolas",11111);
		Materia quimica = new Materia("quimica");
		anio1.agregarMaterias(quimica);
		assertEquals(anio1.getMaterias().get(0).getDocente(),docente);
	}*/
}
