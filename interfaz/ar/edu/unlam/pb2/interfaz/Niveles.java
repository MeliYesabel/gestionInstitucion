package ar.edu.unlam.pb2.interfaz;

public enum Niveles {
JARDIN("Jardin"), PRIMARIA("Primaria"), SECUNDARIA("Secundaria"), FINALIZAR("Salir");

	private String etiqueta;
	
	Niveles (String etiqueta){
		this.etiqueta = etiqueta;
	}

	public String getEtiqueta() {
		return etiqueta;
	}
	
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	

}
