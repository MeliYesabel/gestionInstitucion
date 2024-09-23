package ar.edu.unlam.pb2.interfaz;

public enum OpcionesDeModificacion {
	AGREGAR_ALUMNO("Agregar alumno"),
	AGREGAR_DOCENTE("Asignar docente"), 
	MODIFICAR_ALUMNO("Modificar alumno"),
	MODIFICAR_DOCENTE("Modificar docente"), 
	VOLVER("Volver al menu principal");
	
	private String etiqueta;

	private OpcionesDeModificacion(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getEtiqueta() {
		return etiqueta;
	}
	
	

}
