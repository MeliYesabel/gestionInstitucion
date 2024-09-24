package ar.edu.unlam.pb2.dominio;

public class Alumno extends Persona{
	private String nombre;
	private Integer dni;
	private Integer edad;
	private HistorialAcademico historial;
	private Asistencia asistencias[];
	private Evaluacion evaluaciones[];

	
	public Alumno(String nombre, Integer dni, Integer edad) {
		super(nombre, dni);
		this.edad = edad;
		this.asistencias = new Asistencia[190]; // segun el Ministerio de Relaciones Exteriores y Culto un ciclo escolar tiene 190 dias
		this.evaluaciones = new Evaluacion[5];
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public HistorialAcademico getHistorial() {
		return historial;
	}

	public void setHistorial(HistorialAcademico historial) {
		this.historial = historial;
	}

	

	public Asistencia[] getAsistencias() {
		return asistencias;
	}

	public void setAsistencia(Asistencia asistencia[]) {
		this.asistencias = asistencia;
	}
	@Override
	public String toString() {
		return "Alumno [Nombre=" + nombre + ", Dni=" + dni + ", Edad=" + edad + "]";
	}


	public Evaluacion[] getEvaluacion() {
		return evaluaciones;
	}

	public void setEvaluacion(Evaluacion evaluacion[]) {
		this.evaluaciones = evaluacion;
	}

	public Evaluacion[] getEvaluaciones() {
		return evaluaciones;
	}

	public void setEvaluaciones(Evaluacion evaluaciones[]) {
		this.evaluaciones = evaluaciones;
	}


}
