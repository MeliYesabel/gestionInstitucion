package ar.edu.unlam.pb2.interfaz;

public enum MenuGestionMateriasSecundaria {
	VER("Ver materia"),AGREGAR("Agregar materias"), BORRAR("Eliminar materias"), VOLVER("Volver al menu prinicipal");
	
	
	private String etiqueta;

	MenuGestionMateriasSecundaria(String etiqueta) {
		this.setEtiqueta(etiqueta);
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

}
