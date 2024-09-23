package ar.edu.unlam.pb2.dominio;

public class Fecha {
	private final Integer ANIO = 2024;
	private Integer mes;
	private Integer dia;
	
	public Fecha(Integer mes, Integer dia) {
		this.mes = mes;
		this.dia = dia;
	}

	public Integer getMes() {
		return mes;
	}

	public Boolean setMes(Integer mes) {
		if (mes > 0 && mes <= 12) {
			this.mes = mes;
			return true;
		}
		return false;
	}

	public Integer getDia() {
		return dia;
	}

	public Boolean setDia(Integer dia) {
		if (dia > 0 && dia <= 31) {
			this.dia = dia;
			return true;
		}
		return false;
	}

	public Integer getAnio() {
		return ANIO;
	}

	@Override
	public String toString() {
		return "Fecha =" + dia + "/" + mes + "/" +  ANIO;
	}
	
	

}
