package ar.edu.unlam.pb2.interfaz;

import java.util.Scanner;
import ar.edu.unlam.pb2.dominio.Alumno;
import ar.edu.unlam.pb2.dominio.Anio;
import ar.edu.unlam.pb2.dominio.ColorDeSala;
import ar.edu.unlam.pb2.dominio.Docente;
import ar.edu.unlam.pb2.dominio.Fecha;
import ar.edu.unlam.pb2.dominio.Institucion;
import ar.edu.unlam.pb2.dominio.Materia;
import ar.edu.unlam.pb2.dominio.Numero;
import ar.edu.unlam.pb2.dominio.Sala;
import ar.edu.unlam.pb2.dominio.Grado;
import ar.edu.unlam.pb2.dominio.HistorialAcademico;

public class GestorDeInstituciones {
	static Scanner teclado = new Scanner(System.in);
	static char confirmacion;

	public static void main(String[] args) {
		Institucion nueva = new Institucion("Unlam chiquita");
		System.out.println("Bienvenido a su sistema de gestion de " + nueva.getNombre());

		crearSalitas(nueva);
		crearGrados(nueva);
		crearAnios(nueva);

		Integer index = 0;
		MenuPrincipal eleccion = MenuPrincipal.ASISTENCIA;

		do {
			imprimir("Menu");
			mostrarMenuPrincipal();
			index = pedirInt("Seleccione una opcion");

			eleccion = MenuPrincipal.values()[index - 1];

			switch (eleccion) {
			case ASISTENCIA:
				asistencia(nueva);
				break;
			case EVALUAR:
				evaluar(nueva);
				break;
			case GESTIONAR:
				gestion(nueva);
				break;
			case DOCENTE:
				gestionDocente(nueva);
				break;				
			case MATERIAS:
				gestionMaterias(nueva);
				break;
			case SALIR:
				break;
			default:
				imprimir("No ingresaste una opcion correcta");
				break;
			}

		} while (eleccion != MenuPrincipal.SALIR);
		System.out.println("Vuelva pronto");
	}

	private static void gestionMaterias(Institucion nueva) {
		
		MenuGestionMateriasSecundaria eleccion = MenuGestionMateriasSecundaria.AGREGAR;
		do {
			Anio aula;
			Integer index;
			
			imprimir("Menu gestion de materias");
			mostrarMenuPrimariaSecundaria();
			index = pedirInt("Indica la sala a gestionar.");
			aula = nueva.getSecundaria().getAnios().get(index - 1);
			mostrarMenugestionDeMaterias();
			index = pedirInt("Ingrese la opcion deseada");
			eleccion = MenuGestionMateriasSecundaria.values()[index - 1];

			switch (eleccion) {
			case VER:
				verMaterias(aula);
				break;
			case AGREGAR:
				agregarMateria(aula);
				break;
			case BORRAR:
				borrarMateria(aula);
				break;
			
			case VOLVER:
				break;
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}
		} while (eleccion != MenuGestionMateriasSecundaria.VOLVER);
	}

	private static void verMaterias(Anio aula) {
		if (aula.getMaterias() != null) {
			imprimir("Materias de " + aula.getAnio());
			Integer indice = 1;
			
			for (Materia materia : aula.getMaterias()) {
				imprimir(indice++ + " - " +  materia.getNombre());
			}
		}	
	}

	private static void borrarMateria(Anio aula) {
		
		do {
			String nombre = pedirString("Ingrese el nombre de la materia");
			
			if (aula.eliminarMateria(new Materia(nombre))) {
				imprimir("Materia eliminada correctamente");
			} else imprimir("Surgio un error al imprimir");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');

	}

	private static void agregarMateria(Anio aula) {
		do {				
			if (crearMateria(aula) != null) {
				imprimir("Materia agregada correctamente.");
			}		
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');

	}

	private static void gestionDocente(Institucion nueva) {

		Integer index;
		MenuGestionDocente seleccion = MenuGestionDocente.ACTUALIZAR;
		do {
			imprimir("Menu gestion de Docente");
			mostrarMenuGestionDocente();
			index = pedirInt("Ingrese opcion");
			seleccion = MenuGestionDocente.values()[index - 1];

			switch (seleccion) {
			case INCORPORAR:
				incoporacion(nueva);
				break;
			case ACTUALIZAR:
				actualizarExperiencia(nueva);
				break;
			case MODIFICAR:
				modificarDatosPersonales(nueva);
				break;
			case DESINCOPORAR:
				desincorporar(nueva);
				break;
			case SALIR:
				break;
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}

		} while (seleccion != MenuGestionDocente.SALIR);

	}

	private static void modificarDatosPersonales(Institucion nueva) {
		do {
			Integer dni = pedirInt("Ingrese dni del docente");
			char datoAModificar = pedirChar("N = Nombre\nD = Dni");

			if (nueva.buscarDocente(dni) != null) {

				switch (datoAModificar) {
				case 'N':
					String nuevoNombre = pedirString("Ingrese apellido");
					nuevoNombre = ", " + pedirChar("Ingrese nombre");
					nueva.buscarDocente(dni).setNombre(nuevoNombre);
					imprimir("Actualizado");
					break;
				case 'D':
					Integer dniCorregida = pedirInt("Ingrese dni");
					nueva.buscarDocente(dni).setDni(dniCorregida);
					imprimir("Actualizado");
					break;
				default:
					imprimir("Opcion ingresada desconocida.");
					break;
				}
			} else
				imprimir("El docente no existe.");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');

	}

	private static void incoporacion(Institucion nueva) {
		do {
			Docente docente = crearDocente();
			if (nueva.incoportarDocente(docente)) {
				imprimir("Docente incorporado correctamente.");
				docente.setExperiencia(crearExperiencia());
			}
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');
	}

	private static void actualizarExperiencia(Institucion nueva) {
		do {
			Integer dni = pedirInt("Ingrese dni del docente");
			if (nueva.buscarDocente(dni) != null) {
				nueva.buscarDocente(dni).setExperiencia(crearExperiencia());
				imprimir("Experiencia actualizada.");
			} else
				imprimir("El docente no existe.");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');

	}

	private static void desincorporar(Institucion nueva) {
		do {
			Integer dni = pedirInt("Ingrese dni del docente");
			if (nueva.buscarDocente(dni) != null && nueva.bajaDocente(dni)) {
				imprimir("Docente dado de baja.");
			} else
				imprimir("El docente no existe. / No se dio de baja.");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');

	}

	private static void evaluar(Institucion nueva) {
		Integer dni = pedirInt("Ingresa tu dni de Docente");
		Docente docente = nueva.buscarDocente(dni);
		if (nueva.buscarDocente(dni) != null) {

			imprimir("¿De que nivel tomaras asistencia?");
			mostrarMenuDeNiveles();

			Integer indexx = 0;
			Niveles seleccion = Niveles.FINALIZAR;
			seleccion.setEtiqueta("Volver");
			do {
				mostrarPrimariaSecundaria();
				indexx = pedirInt("¿Que novel desea evaluar?");
				seleccion = Niveles.values()[indexx - 1];
				switch (seleccion) {
				case PRIMARIA:
					mostrarMenuPrimariaSecundaria();
					cargarNotaPrimaria(nueva, docente);
					break;
				case SECUNDARIA:
					mostrarMenuPrimariaSecundaria();
					//cargarNotaSecundaria(nueva, docente);
				case FINALIZAR:
					break;
				default:
					imprimir("No ingresaste una opcion correcta");
					break;
				}

			} while (seleccion != Niveles.FINALIZAR);

		} else
			imprimir("No estas ingresado en el sistema como Docente");

	}

	private static void cargarNotaPrimaria(Institucion nueva, Docente docente) {
		Integer index = pedirInt("Ingrese numero");
		Grado curso = nueva.getPrimaria().getGrados()[index - 1];
		if (curso.tieneExperiencia(docente)) {
			for (Integer i = 0; i < curso.getAlumnos().size(); i++) {
				if (curso.getAlumnos().get(i) != null) {
					Integer nota;
					do {
						nota = pedirInt("Evalue de 1 a 10 a " + curso.getAlumnos().get(i).getNombre());
					} while (nota > 10 && nota < 0);

					curso.cargarNota(nota, curso.getAlumnos().get(i));
				}
			}
		} else
			imprimir("No puedes evaluar estos alumnos ya que no tienes los requerimientos.");

	}

//	private static void cargarNotaSecundaria(Institucion nueva, Docente docente) {
//		Integer index = pedirInt("Ingrese numero");
//		Anio curso = nueva.getSecundaria().getAnios().get(index -1);
//		mostrarMenuMaterias(curso);
//		String nombre = pedirString("Ingresa nombre de la materia a evaluar");
//
//		if (curso.buscarMateriaPorNombre(nombre) != null) {
//			if (curso.tieneExperienciaEnMateria(curso.buscarMateriaPorNombre(nombre))) {
//				for (Integer i = 0; i < curso.getAlumnos().length; i++) {
//					if (curso.getAlumnos()[i] != null) {
//						Integer nota;
//						do {
//							nota = pedirInt("Evalue de 1 a 10 a " + curso.getAlumnos()[i].getNombre());
//						} while (nota > 10 && nota < 0);
//
//						curso.cargarNota(nota, curso.getAlumnos()[i], curso.buscarMateriaPorNombre(nombre));
//					}
//				}
//			} else
//				imprimir("No puedes evaluar estos alumnos ya que no tienes los requerimientos.");
//
//		} else
//			imprimir("La materia que ingresaste no existe");
//
//	}

	public static void mostrarMenuMaterias(Anio anio) {
		Integer i = 0;
		for (Materia materia : anio.getMaterias()) {
			imprimir(i++ + " - " + materia.getNombre());
		}
		
	}

	private static void asistencia(Institucion nueva) {
		Fecha fecha = pedirFecha();

		Integer indexx = 0;
		Niveles seleccion = Niveles.FINALIZAR;
		seleccion.setEtiqueta("Volver");
		do {
			imprimir("Menu asistencia");
			imprimir("¿De que nivel tomaras asistencia?");
			mostrarMenuDeNiveles();
			indexx = pedirInt("");
			seleccion = Niveles.values()[indexx - 1];
			switch (seleccion) {
			case JARDIN:
				tomarListaJardin(nueva, fecha);
				break;
			case PRIMARIA:
				tomarListaPrimaria(nueva, fecha);
				break;
			case SECUNDARIA:
				tomarListaSecundaria(nueva, fecha);
			case FINALIZAR:
				break;
			default:
				imprimir("No ingresaste una opcion correcta");

				break;
			}

		} while (seleccion != Niveles.FINALIZAR);

	}

	private static void tomarListaSecundaria(Institucion nueva, Fecha fecha) {
		mostrarMenuPrimariaSecundaria();
		Integer index = pedirInt("Ingrese numero");

		Anio curso = nueva.getSecundaria().getAnios().get(index -1);
	
		for (Alumno alumno : curso.getAlumnos()) {
			Boolean confirmar = preguntarPresencia(alumno);
			if (!curso.tomarAsistencia(fecha, alumno, confirmar)) {
				imprimir("Ocurrio un error... Dejaras de tomar asistencia");
				break;
		}}
	}

	private static void tomarListaPrimaria(Institucion nueva, Fecha fecha) {
		mostrarMenuPrimariaSecundaria();
		Integer index = pedirInt("Ingrese numero");

		Grado curso = nueva.getPrimaria().getGrados()[index - 1];

		for (Alumno alumno : curso.getAlumnos()) {
			Boolean confirmar = preguntarPresencia(alumno);
			if (!curso.tomarAsistencia(fecha, alumno, confirmar)) {
				imprimir("Ocurrio un error... Dejaras de tomar asistencia");
				break;
		}}
	}

	private static void tomarListaJardin(Institucion nueva, Fecha fecha) {
		mostrarMenuDeColoresDeSala();
		Integer index = pedirInt("Ingrese numero");
		
		Sala salita = nueva.getJardin().getSalitas().get(index -1);

		for (Alumno alumno : salita.getAlumnos()) {
			Boolean confirmar = preguntarPresencia(alumno);
			if (!salita.tomarAsistencia(fecha, alumno, confirmar)) {
				imprimir("Ocurrio un error... Dejaras de tomar asistencia");
				break;
		}}
	}

	private static Boolean preguntarPresencia(Alumno alumno) {
		imprimir("" + alumno);
		char presencia;
		Boolean confirmar = false;
		do {
			presencia = pedirChar("Presentes / Ausente");
			switch (presencia) {
			case 'P':
				confirmar = true;
				break;
			case 'A':
				confirmar = false;
				break;
			default:
				imprimir("No ingresaste una opcion correcta");
				break;
			}

		} while (presencia != 'P' || presencia != 'A');

		return confirmar;
	}

	private static void gestion(Institucion nueva) {
		Integer index;
		Niveles seleccion = Niveles.JARDIN;
		do {
			imprimir("Menu de gestion");
			mostrarMenuDeNiveles();
			index = pedirInt("¿Que desea gestionar?");
			seleccion = Niveles.values()[index - 1];

			switch (seleccion) {
			case JARDIN:
				abmJardin(nueva);
				break;
			case PRIMARIA:
				abmPrimaria(nueva);
				break;
			case SECUNDARIA:
				abmSecundaria(nueva);
				break;
			case FINALIZAR:
				break;
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}

		} while (seleccion != Niveles.FINALIZAR);
	}

	private static void abmSecundaria(Institucion nueva) {
		OpcionesDeModificacion eleccion = OpcionesDeModificacion.AGREGAR_ALUMNO;

		do {
			Anio aula;
			Integer index;
			mostrarAniosExistentes(nueva);
			index = pedirInt("Indica la sala a gestionar.");
			aula = nueva.getSecundaria().getAnios().get(index -1);
			mostrarMenuModificaciones();
			index = pedirInt("Ingrese la opcion deseada");
			eleccion = OpcionesDeModificacion.values()[index - 1];

			switch (eleccion) {
			case AGREGAR_ALUMNO:
				agregarAlumno(aula);
				break;
			case AGREGAR_DOCENTE:
				//asignarDocente(aula, nueva);
				break;
			case MODIFICAR_ALUMNO:
				modificarAlumno(aula);
				break;
			case MODIFICAR_DOCENTE:
				//modificarDocente(aula);
				break;
			case VOLVER:
				break;
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}
		} while (eleccion != OpcionesDeModificacion.VOLVER);
	}

	private static void abmPrimaria(Institucion nueva) {
		OpcionesDeModificacion eleccion = OpcionesDeModificacion.AGREGAR_ALUMNO;

		do {
			Grado aula;
			Integer index;
			mostrarGradosExistentes(nueva);
			index = pedirInt("Indica la sala a gestionar.");
			aula = nueva.getPrimaria().getGrados()[index - 1];
			mostrarMenuModificaciones();
			index = pedirInt("Ingrese la opcion deseada");
			eleccion = OpcionesDeModificacion.values()[index - 1];

			switch (eleccion) {
			case AGREGAR_ALUMNO:
				agregarAlumno(aula);
				break;
			case AGREGAR_DOCENTE:
				asignarDocente(aula, nueva);
				break;
			case MODIFICAR_ALUMNO:
				modificarAlumno(aula);
				break;
			case MODIFICAR_DOCENTE:
				modificarDocente(aula);
				break;
			case VOLVER:
				break;
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}
		} while (eleccion != OpcionesDeModificacion.VOLVER);
	}

	private static void abmJardin(Institucion nueva) {
		OpcionesDeModificacion eleccion = OpcionesDeModificacion.AGREGAR_ALUMNO;

		do {
			Sala sala;
			Integer index;
			mostrarSalasExistentes(nueva);
			index = pedirInt("Indica la sala a gestionar.");
			sala = nueva.getJardin().getSalitas().get(index-1);
			mostrarMenuModificaciones();
			index = pedirInt("Ingrese la opcion deseada");
			eleccion = OpcionesDeModificacion.values()[index - 1];

			switch (eleccion) {
			case AGREGAR_ALUMNO:
				agregarAlumno(sala);
				break;
			case AGREGAR_DOCENTE:
				asignarDocente(sala, nueva);
				break;
			case MODIFICAR_ALUMNO:
				modificarAlumno(sala);
				break;
			case MODIFICAR_DOCENTE:
				modificarDocente(sala);
				break;
			case VOLVER:
				break;
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}
		} while (eleccion != OpcionesDeModificacion.VOLVER);

	}

	private static void modificarDocente(Sala sala) {
		do {
			Integer dni = pedirInt("Ingrese DNI del Docente");
			Docente maestro = sala.buscarMaestroPorDNI(dni);
			if (maestro != null) {
				
					if (sala.bajaDocente(dni)) {
						imprimir("Se elimino correctamente.");
					} else
						imprimir("Algo salio mal.");

			} else
				imprimir("El alumno ingresado no existe");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');

	}

	private static void modificarDocente(Grado grado) {
		if (grado.getDocente() != null) {
			
			if (grado.bajaDocente()) {
				imprimir("Se elimino correctamente.");
			} else
				imprimir("Algo salio mal.");
	} else
		imprimir("No hay un docente ingresado");
	imprimir("No hay un docente ingresado");

	}

//	private static void modificarDocente(Anio anio) {
//		if (anio.getProfesor() != null) {
//			
//				if (anio.bajaDocente()) {
//					imprimir("Se elimino correctamente.");
//				} else
//					imprimir("Algo salio mal.");
//		} else
//			imprimir("No hay un docente ingresado");
//
//	}

	private static void modificarAlumno(Sala sala) {
		Integer dni = pedirInt("Ingrese DNI de alumno");
		Alumno alumno = sala.buscarAlumnoDNI(dni);
		if (alumno != null) {
			char bajaModidificacion = pedirChar("B = Dar de baja\nM = Modificar");

			switch (bajaModidificacion) {
			case 'B':
			case 'D':
			case 'E':
				if (sala.bajaAlumno(dni)) {
					imprimir("Se elimino correctamente.");
				} else
					imprimir("Algo salio mal.");

				break;
			case 'M':
				char datoAModificar = pedirChar("N = Nombre\nE = Edad \nD = Dni");
				switch (datoAModificar) {
				case 'N':
					String nuevoNombre = pedirString("Ingrese apellido");
					nuevoNombre = ", " + pedirChar("Ingrese nombre");
					alumno.setNombre(nuevoNombre);
					imprimir("Actualizado");

					break;
				case 'E':
					Integer edadCorregida = pedirInt("Ingrese edad");
					alumno.setEdad(edadCorregida);
					imprimir("Actualizado");

					break;

				case 'D':
					Integer dniCorregida = pedirInt("Ingrese dni");
					alumno.setDni(dniCorregida);
					imprimir("Actualizado");

					break;

				default:
					imprimir("Opcion ingresada desconocida.");
					break;
				}
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}
		} else
			imprimir("El alumno ingresado no existe");

	}

	private static void modificarAlumno(Grado grado) {
		Integer dni = pedirInt("Ingrese DNI de alumno");
		Alumno alumno = grado.buscarAlumnoDNI(dni);
		if (alumno != null) {
			char bajaModidificacion = pedirChar("B = Dar de baja\nM = Modificar");

			switch (bajaModidificacion) {
			case 'B':
			case 'D':
			case 'E':
				if (grado.bajaAlumno(dni)) {
					imprimir("Se elimino correctamente.");
				} else
					imprimir("Algo salio mal.");

				break;
			case 'M':
				char datoAModificar = pedirChar("N = Nombre\nE = Edad \nD = Dni");
				switch (datoAModificar) {
				case 'N':
					String nuevoNombre = pedirString("Ingrese apellido");
					nuevoNombre = ", " + pedirChar("Ingrese nombre");
					alumno.setNombre(nuevoNombre);
					imprimir("Actualizado");

					break;
				case 'E':
					Integer edadCorregida = pedirInt("Ingrese edad");
					alumno.setEdad(edadCorregida);
					imprimir("Actualizado");

					break;

				case 'D':
					Integer dniCorregida = pedirInt("Ingrese dni");
					alumno.setDni(dniCorregida);
					imprimir("Actualizado");
					break;

				default:
					imprimir("Opcion ingresada desconocida.");
					break;
				}
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}
		} else
			imprimir("El alumno ingresado no existe");
		;

	}

	private static void modificarAlumno(Anio aula) {
		Integer dni = pedirInt("Ingrese DNI de alumno");
		Alumno alumno = aula.buscarAlumnoDNI(dni);
		if (alumno != null) {
			char bajaModidificacion = pedirChar("B = Dar de baja\nM = Modificar");

			switch (bajaModidificacion) {
			case 'B':
			case 'D':
			case 'E':
				if (aula.bajaAlumno(dni)) {
					imprimir("Se elimino correctamente.");
				} else
					imprimir("Algo salio mal.");

				break;
			case 'M':
				char datoAModificar = pedirChar("N = Nombre\nE = Edad \nD = Dni");
				switch (datoAModificar) {
				case 'N':
					String nuevoNombre = pedirString("Ingrese apellido");
					nuevoNombre = ", " + pedirChar("Ingrese nombre");
					alumno.setNombre(nuevoNombre);
					imprimir("Actualizado");

					break;
				case 'E':
					Integer edadCorregida = pedirInt("Ingrese edad");
					alumno.setEdad(edadCorregida);
					imprimir("Actualizado");

					break;

				case 'D':
					Integer dniCorregida = pedirInt("Ingrese dni");
					alumno.setDni(dniCorregida);
					imprimir("Actualizado");

					break;

				default:
					imprimir("Opcion ingresada desconocida.");
					break;
				}
			default:
				imprimir("Opcion ingresada desconocida.");
				break;
			}
		} else
			imprimir("El alumno ingresado no existe");
	}

	private static void asignarDocente(Sala sala, Institucion nueva) {
		Integer dni;
		do {			
			 dni = pedirInt("Ingresar dni del docente");
			if (nueva.buscarDocente(dni) != null && sala.asignarMaestro(nueva.buscarDocente(dni))) {
				imprimir("Se agrego correctamente");
			} else imprimir("Error al agregar");
			confirmacion = pedirChar("¿Continuar? Si, No");
		} while (confirmacion == 'S');

	}

	private static void asignarDocente(Grado grado, Institucion nueva) {
		Integer dni = pedirInt("Ingresar dni del docente");
		if (nueva.buscarDocente(dni) != null && grado.asignarDocente(nueva.buscarDocente(dni))) {
			imprimir("Se agrego correctamente");
		} else
			imprimir("Error al agregar");
	}

//	private static void asignarDocente(Anio anio, Institucion nueva) {	
//		Materia materia = crearMateria(anio);
//		Integer dni = pedirInt("Ingresar dni del docente");
//		if (nueva.buscarDocente(dni) != null && anio.asignarDocente(nueva.buscarDocente(dni), materia)) {
//			imprimir("Se agrego correctamente");
//		} else
//			imprimir("Error al agregar");
//	}

	private static void mostrarMenuPrincipal() {

		MenuPrincipal menu[] = MenuPrincipal.values();
		for (Integer i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i].getEtiqueta());
		}
	}

	private static void mostrarMenuDeNiveles() {

		Niveles menu[] = Niveles.values();
		for (Integer i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i].getEtiqueta());
		}
	}

	private static void mostrarPrimariaSecundaria() {

		Niveles menu[] = Niveles.values();
		for (Integer i = 1; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i].getEtiqueta());
		}
	}

	private static void mostrarMenuDeColoresDeSala() {

		ColorDeSala menu[] = ColorDeSala.values();
		for (Integer i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i]);
		}
	}

	private static void mostrarMenuPrimariaSecundaria() {

		Numero menu[] = Numero.values();
		for (Integer i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i]);
		}
	}

	private static void mostrarSalasExistentes(Institucion nueva) {
		imprimir("Salas existentes");
		for (Sala a : nueva.getJardin().getSalitas()) {
			imprimir(a.toString());
		}
	}

	private static void mostrarGradosExistentes(Institucion nueva) {
		imprimir("Grados:");
		for (Integer i = 0; i < nueva.getPrimaria().getGrados().length; i++) {
			if (nueva.getPrimaria().getGrados()[i] != null) {
				imprimir(i + 1 + " -" + nueva.getPrimaria().getGrados()[i].toString());
			}
		}
	}

	private static void mostrarAniosExistentes(Institucion nueva) {
		imprimir("Años:");
		Integer i = 0;
		
		for (Anio anio : nueva.getSecundaria().getAnios()) {
			imprimir(i++ + 1 + " -" + anio.toString());
		}
	}

	
	private static void mostrarMenuModificaciones() {

		OpcionesDeModificacion menu[] = OpcionesDeModificacion.values();
		for (Integer i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i].getEtiqueta());
		}
	}

	private static void mostrarMenuGestionDocente() {
		MenuGestionDocente menu[] = MenuGestionDocente.values();
		for (Integer i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i].getEtiqueta());
		}
	}
	
	private static void mostrarMenugestionDeMaterias() {
		MenuGestionMateriasSecundaria menu[] = MenuGestionMateriasSecundaria.values();
		for (Integer i = 0; i < menu.length; i++) {
			System.out.println(i + 1 + " - " + menu[i].getEtiqueta());
		}		
	}

	private static Integer pedirInt(String mensaje) {
		System.out.println(mensaje);
		Integer ingreso = teclado.nextInt();
		return ingreso;
	}

	private static char pedirChar(String mensaje) {
		System.out.println(mensaje);
		char ingreso = teclado.next().toUpperCase().charAt(0);
		return ingreso;
	}

	private static Boolean pedirBoolean(String mensaje) {

		char presencia;
		Boolean confirmar = false;
		do {
			presencia = pedirChar("SI / NO");
			switch (presencia) {
			case 'S':
				confirmar = true;
				break;
			case 'N':
				confirmar = false;
				break;
			default:
				imprimir("No ingresaste una opcion correcta");
				break;
			}

		} while (presencia != 'S' || presencia != 'N');

		return confirmar;
	}

	private static Fecha pedirFecha() {
		imprimir("Ingresa la fecha de hoy, si la fecha no esaceptable se volvera a pedir.");
		Fecha fecha = new Fecha(0, 0);
		Integer mes = 0, dia = 0;
		do {
			mes = pedirInt("Mes");
			dia = pedirInt("Dia");
		} while (!fecha.setMes(mes) || !fecha.setDia(dia));

		return fecha;

	}

	private static String pedirString(String mensaje) {
		System.out.println(mensaje);
		String ingreso = teclado.next();
		return ingreso;
	}

	private static void imprimir(String mensaje) {
		System.out.println(mensaje);
	}

	private static void crearSalitas(Institucion nueva) {
		ColorDeSala colores[] = ColorDeSala.values();
	
		for (Integer i = 0; i < colores.length ; i++) {
			Sala salita = new Sala(colores[i]);
			nueva.getJardin().crearSalita(salita);
		}
	}

	private static void crearGrados(Institucion nueva) {
		Numero anios[] = Numero.values();

		for (Integer i = 0; i < anios.length; i++) {
			Grado grado = new Grado(anios[i]);
			nueva.getPrimaria().crearGrados(grado);

		}
	}

	private static void crearAnios(Institucion nueva) {
		Numero anios[] = Numero.values();

		for (Integer i = 0; i < anios.length; i++) {
			Anio grado = new Anio(anios[i]);
			nueva.getSecundaria().crearAnios(grado);
		}
		
		
	}

	private static Alumno crearAlumno() {
		String nombre = pedirString("Ingrese apellido");
		nombre += ", " + pedirString("Ingrese nombre");
		Integer dni = pedirInt("Ingrese dni");
		Integer edad = pedirInt("Ingrese edad");

		Alumno alumnoCreado = new Alumno(nombre, dni, edad);

		return alumnoCreado;
	}

	private static Docente crearDocente() {
		String nombre = pedirString("Ingrese apellido");
		nombre += ", " + pedirString("Ingrese nombre");
		Integer dni = pedirInt("Ingrese dni");
		Docente docenteCreado = new Docente(nombre, dni);

		return docenteCreado;
	}

	private static HistorialAcademico crearExperiencia() {
		Boolean jardin = pedirBoolean("Experiencia como maestro jardiro");
		Boolean primaria = pedirBoolean("Experiencia en primaria");
		HistorialAcademico xp = new HistorialAcademico(jardin);
		xp.setPrimaria(primaria);
		if (primaria) {
			for (Integer i = 0; i < xp.getGrado().length; i++) {
				Boolean grado = pedirBoolean("Experiencia en " + Niveles.values()[i]);
				if (grado) {
					xp.getGrado()[i] = grado;
				}
			}
		}
		Boolean secundaria = pedirBoolean("Experiencia en secundaria");
		if (secundaria) {
			String materia = pedirString("Nombre de la materia en la que tienes experiencia");
			Boolean continuar;
			do {
				if (xp.agregarMateria(new Materia(materia))) {
					imprimir("Se agrego correctamente");
				} else
					imprimir("No se pudo ingresar");

				continuar = pedirBoolean("Seguir ingresando?");
			} while (continuar);
		}

		return xp;
	}

	private static Materia crearMateria(Anio anio) {
		String nombre = pedirString("Nombre de la materia");
		Materia materia = new Materia(nombre);
		if (anio.agregarMaterias(materia)) {
			return materia;
		}
		return materia;

	}

	private static void agregarAlumno(Sala sala) {
		do {
			if (sala.agregarAlumno(crearAlumno())) {
				imprimir("Se agrego correctamente");
			} else
				imprimir("Error al agregar");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');

	}

	private static void agregarAlumno(Grado grado) {
		do {
			if (grado.agregarAlumno(crearAlumno())) {
				imprimir("Se agrego correctamente");
			} else
				imprimir("Error al agregar");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');
	}

	private static void agregarAlumno(Anio anio) {
		do {
			if (anio.agregarAlumno(crearAlumno())) {
				imprimir("Se agrego correctamente");
			} else
				imprimir("Error al agregar");
			confirmacion = pedirChar("¿Continuar? Si");
		} while (confirmacion == 'S');
	}

}
