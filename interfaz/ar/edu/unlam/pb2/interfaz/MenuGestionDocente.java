package ar.edu.unlam.pb2.interfaz;

public enum MenuGestionDocente {

	INCORPORAR("Incorporacion"), DESINCOPORAR("Desincoporacion"), ACTUALIZAR("Actualizar experiecia de docente"),
	MODIFICAR("Corregir datos personales"), SALIR("Volver al menu principal");

	private String etiqueta;

	MenuGestionDocente(String etiqueta) {
		this.setEtiqueta(etiqueta);
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

}
