package ar.edu.unlam.pb2.dominio;
import java.util.ArrayList;

public class Jardin {
	private String nombre;
	private ArrayList<Sala> salitas;
	private Integer cantidadSalitas;

	public Jardin(String nombre, Integer cantidadSalitas) {
		this.nombre = nombre;
		this.salitas = new ArrayList<>();
		this.cantidadSalitas = cantidadSalitas;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Sala> getSalitas() {
		return salitas;
	}

	public void setSalitas(ArrayList<Sala> salitas) {
		this.salitas = salitas;
	}
	
	public Boolean crearSalita(Sala sala) { 
		
		if (salitas.size() >= cantidadSalitas || verificarColorDeLaSalaSinUsar(sala)) {
			return false;
		}
		return salitas.add(sala);
	}
	
	public Boolean verificarColorDeLaSalaSinUsar(Sala sala) {
		for (Sala s : salitas) {
			if (s.getColor() == sala.getColor()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Jardin [nombre=" + nombre + ", salitas=" + salitas + "]";
	}


	public Integer getCantidadSalitas() {
		return cantidadSalitas;
	}

	public void setCantidadSalitas(Integer cantidadSalitas) {
		this.cantidadSalitas = cantidadSalitas;
	}
	
	
}
