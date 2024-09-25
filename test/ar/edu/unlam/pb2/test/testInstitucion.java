package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import org.junit.Test;
import ar.edu.unlam.pb2.dominio.*;

public class testInstitucion {

	@Test
	public void crearUnaInstitucion() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		assertNotNull(ins);
		assertEquals(nombre, ins.getNombre());
		}	
	@Test	
	public void incorporarUnDocenteALaInstitucion(){
		Docente nuevo = new Docente("Melina", 11111111);
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		
		Boolean validacion = ins.incoportarDocente(nuevo);
		assertTrue(validacion);		
	}
	@Test
	public void buscarUnDocentePorSuDNI() {
		Integer dni = 11111111;
		Docente nuevo = new Docente("Melina", dni);
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		
		ins.incoportarDocente(nuevo);
		
		Docente buscado = ins.buscarDocente(dni);
		assertEquals(nuevo, buscado);		
	}	
	@Test
	public void darDeBajaUnDocente() {
		Integer dni = 11111111;
		Docente nuevo = new Docente("Melina", dni);
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		
		ins.incoportarDocente(nuevo);
		
		Docente buscado = ins.buscarDocente(dni);
		assertEquals(nuevo, buscado);		
	}	
	@Test	
	public void noIncorporarDosDocentesConElMismoDNI(){
		Integer dni = 1;
		Docente nuevo = new Docente("Melina", dni);
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		ins.incoportarDocente(nuevo);
		Docente segundo = new Docente(nombre, dni);
		Boolean validacion = ins.incoportarDocente(segundo);
		assertFalse(validacion);		
	}	
	@Test
	public void crearJardin() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		
		assertNotNull(ins.getJardin());
	}
	@Test
	public void crearSalaCELESTE() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Sala sala = new Sala(ColorDeSala.CELESTE);		
		Boolean validacion = ins.getJardin().crearSalita(sala);
		
		assertTrue(validacion);
	}	
	@Test
	public void crearSalaCELESTEYSalaRoja() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Sala sala = new Sala(ColorDeSala.CELESTE);	
		Boolean validacionCELESTE = ins.getJardin().crearSalita(sala);
		Sala salaroja = new Sala(ColorDeSala.ROJO);
		Boolean validacionRoja = ins.getJardin().crearSalita(salaroja);
		assertTrue(validacionCELESTE);
		assertTrue(validacionRoja);
		
	}	
	@Test 
	public void noCrearDosSalasCELESTEDosVeces() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Sala sala = new Sala(ColorDeSala.CELESTE);		
		ins.getJardin().crearSalita(sala);
		Boolean validacion = ins.getJardin().crearSalita(sala);
		
		assertFalse(validacion);
	}	
	@Test
	public void asignoUnMaestroASalaCELESTERegistradoComoDocenteDentroDeLaInstitucion(){
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Docente maestro = new Docente("Melina", 11111111);
		ins.incoportarDocente(maestro);
		Sala sala = new Sala(ColorDeSala.CELESTE);		
		ins.getJardin().crearSalita(sala);
		Boolean validacion = false;
		if (ins.buscarDocente(maestro.getDni()) != null) {
			validacion = sala.asignarMaestro(maestro);
		}
		assertTrue(validacion);	
	}	
	@Test
	public void asignoDosMaestroASalaCELESTERegistradoComoDocenteDentroDeLaInstitucion(){
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Docente maestro = new Docente("Melina", 11111111);
		ins.incoportarDocente(maestro);
		Docente segundoMaestro = new Docente("Melina", 1111111);
		ins.incoportarDocente(segundoMaestro);
		Sala sala = new Sala(ColorDeSala.CELESTE);		
		ins.getJardin().crearSalita(sala);
		Boolean validacion = false;
		if (ins.buscarDocente(maestro.getDni()) != null) {
			sala.asignarMaestro(maestro);
		}
		if (ins.buscarDocente(segundoMaestro.getDni()) != null) {
			validacion = sala.asignarMaestro(segundoMaestro);
		}
		assertTrue(validacion);	
	}	
	@Test
	public void noAsignoMaestroASalaCELESTESinoEstaEnLaInstitucion(){
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Docente maestro = new Docente("Melina", 11111111);
		Sala sala = new Sala(ColorDeSala.CELESTE);		
		ins.getJardin().crearSalita(sala);
		Boolean validacion = false;
		
		if (ins.buscarDocente(maestro.getDni()) != null) {
			sala.asignarMaestro(maestro);
			validacion = true;
		}
		assertFalse(validacion);	
	}	
	@Test
	public void noAsignarTresMaestrosAunqueEstenRegistradosEnLaInstitucion(){
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Docente maestro = new Docente("Melina", 11111111);
		ins.incoportarDocente(maestro);
		Docente segundoMaestro = new Docente("Melina", 1111);
		ins.incoportarDocente(segundoMaestro);
		Docente extra = new Docente("Melina", 111111);
		ins.incoportarDocente(extra);
		Sala sala = new Sala(ColorDeSala.CELESTE);		
		ins.getJardin().crearSalita(sala);
		Boolean validacion = true;

		if (ins.buscarDocente(maestro.getDni()) != null) {
			sala.asignarMaestro(maestro);
		}
		if (ins.buscarDocente(segundoMaestro.getDni()) != null) {
			sala.asignarMaestro(segundoMaestro);
		}
		if (ins.buscarDocente(extra.getDni()) != null) {
			validacion = sala.asignarMaestro(extra);			
		}		
		assertFalse(validacion);	
	}	
	public void agregarUnAlumnoASalaCELESTE() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Sala sala = new Sala(ColorDeSala.CELESTE);	
		ins.getJardin().crearSalita(sala);
		Integer dni,edad;
		dni = 11111;
		edad = 4;
		
		Alumno alumno = new Alumno(nombre, dni, edad);
		
		ins.incoportarAlumno(alumno);
		Boolean validacion = false;
		if (ins.buscarAlumno(dni).equals(alumno)) {
			validacion = sala.agregarAlumno(alumno);
		}		
		assertTrue(validacion);
	}
	@Test
	public void noAgregarDosAlumnoASalaCELESTEconElMismoDNI() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Sala sala = new Sala(ColorDeSala.CELESTE);	
		ins.getJardin().crearSalita(sala);
		Integer dni,edad;
		dni = 11111;
		edad = 4;
		Alumno alumno = new Alumno(nombre, dni, edad);
		ins.incoportarAlumno(alumno);
		Alumno segundoAlumno = new Alumno(nombre, dni, edad);
		ins.incoportarAlumno(segundoAlumno);
		Boolean validacion = true;
		if (ins.buscarAlumno(dni) != null) {
			sala.agregarAlumno(alumno);
		}
		if (ins.buscarAlumno(dni) != null) {
			validacion = sala.agregarAlumno(segundoAlumno);
		}
		
		assertFalse(validacion);		
	}	
	public void noSuperarLaCapacidadDeUnaSala() {
		String nombre = "Unlam";			
		Institucion ins = new Institucion(nombre);
		Sala sala = new Sala(ColorDeSala.CELESTE);	
		ins.getJardin().crearSalita(sala);
		Integer dni,edad;
		dni = 11111;
		edad = 4;
		Boolean validacion = true;
		for (int i = 0; i < 10; i++) {
			Alumno alumno = new Alumno(nombre, dni, edad++);
			ins.incoportarAlumno(alumno);
			if (ins.buscarAlumno(dni) != null) {
				validacion = sala.agregarAlumno(alumno);
			}
			
		}
		
		assertFalse(validacion);	
	}
	
	
	
	
	
}
