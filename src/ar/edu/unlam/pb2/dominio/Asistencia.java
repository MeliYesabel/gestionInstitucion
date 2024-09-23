package ar.edu.unlam.pb2.dominio;

public class Asistencia {
	
	private Fecha fecha;
	private Boolean asistencia;
	
	public Asistencia(Fecha fecha, Boolean asistencia) {		
		this.fecha = fecha;
		this.asistencia = asistencia;
	}

	
	public Boolean isAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Boolean asistencia) {
		this.asistencia = asistencia;
	}
	
	public Fecha getFecha() {
		return fecha;
	}


	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}


	@Override
	public String toString() {
		return "Asistencia [fecha=" + fecha + ", asistencia=" + asistencia + "]";
	}
	
	

}
