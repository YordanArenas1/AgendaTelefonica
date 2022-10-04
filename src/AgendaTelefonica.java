
import java.util.*;
public class AgendaTelefonica {

	/**
	 * @author YordanFelipeArenas
	 *
	 */



	private Grupo    [] listaGrupos;
	private Reunion  [] listaReuniones;
	private Contacto [] listaContactos;



	public AgendaTelefonica(Grupo[] listaGrupos, Reunion[] listaReuniones, Contacto[] listaContactos) {
		super();
		this.listaGrupos = listaGrupos;
		this.listaReuniones = listaReuniones;
		this.listaContactos = listaContactos;
	}


	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}




	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}



	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}




	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}

	public Contacto[] getListaContactos() {
		return listaContactos;
	}


	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}


	@Override
	public String toString() {
		return "AgendaTelefonica [listaGrupos=" + Arrays.toString(listaGrupos) + ", listaReuniones="
				+ Arrays.toString(listaReuniones) + ", listaContactos=" + Arrays.toString(listaContactos) + "]";
	}


	public String aniadirContac(Contacto nuevoContacto){

		String mensaje = "";
		int posicionDisponible = -1;
		Contacto contactoEncontrado = null;

		try {
			contactoEncontrado = verificarExisteContac(nuevoContacto);
			posicionDisponible = obtenerPosicionDispoContac();
			listaContactos[posicionDisponible] = nuevoContacto;
			mensaje = "El contacto fue añadido exitosamente";
		} catch (ContactoException e) {
			mensaje = e.getMessage();
		}
		return mensaje;
	}


	/**
	 * esta funcion nos ayuda para saber la posicion disponible de los contactos
	 * @return posicionDisponible
	 * @throws ContactoException
	 */
	private int obtenerPosicionDispoContac() throws ContactoException {

		int posicionDisponible = -1;

		for (int i = 0; i < listaContactos.length && posicionDisponible == -1; i++) {
			if(listaContactos[i] == null){
				posicionDisponible = i;
			}
		}
		if(posicionDisponible == -1){
			throw new ContactoException("La agenda esta llena");
		}
		return posicionDisponible;
	}
	/**
	 * esta funcion nos ayuda a saber si el contacto ya existe
	 * @param telefono
	 * @return contactoEncontrado
	 * @throws ContactoException
	 */
	private Contacto verificarExisteContac (Contacto contactoBuscado) throws ContactoException {

		Contacto contactoEncontrado = null;

		for(Contacto contacto : listaContactos){
			if(contacto.equals(contactoBuscado)){
				contactoEncontrado = contacto;
				throw new ContactoException("El contacto ingresado ya existe");
			}
		}
		return contactoEncontrado;
	}
	/**
	 * esta funcion almacena en una lista todo los contactos
	 * @return contacto
	 * @throws ContactoException
	 */
	public ArrayList <Contacto> listarContac() throws ContactoException{
		  ArrayList<Contacto> contacto = new ArrayList<Contacto>();
		  if(listaContactos != null){
			  for (int i = 0; i < listaContactos.length; i++) {
				contacto.add(listaContactos[i]);
			}
		  }
		  else{
			  throw new ContactoException ("No hay contactos para mostrar");
		  }
		 return contacto;
	}
	/**
	 * esta funcion nos ayuda a saber si el contacto ya existe
	 * @param contactoVerificado
	 * @return mensaje
	 * @throws ContactoException
	 */
	public String existeContac(Contacto contactoVerificado) throws ContactoException{

		String mensaje = "El contacto no existe";

		for (Contacto contacto : listaContactos) {
			if(contacto.equals(contactoVerificado)){
				throw new ContactoException("El contacto ya existe");
			}
		}
		return mensaje;
	}
	/**
	 * esta funcion lo que hace es buscar el contacto
	 * @param nombre
	 * @return numeroTelefono
	 * @throws ContactoException
	 */
	public String buscarContac (String nombre) throws ContactoException{

		String numeroTelefono = "";

		for(Contacto contacto : listaContactos){
			if(contacto.getNombre().equals(nombre)){
				numeroTelefono = contacto.getTelefono();
			}
		}
		if(numeroTelefono == ""){
			throw new ContactoException("El contacto se ha encontrado");
		}
		return numeroTelefono;
	}
	/**
	 * funcion para eliminar el contacto
	 * @param contactoEliminar
	 * @return mensaje
	 * @throws ContactoException
	 */
	public String eliminarContac(Contacto contactoEliminar) throws ContactoException{

		String mensaje = "El contacto no se ha eliminado";
		int posicionContacto = obtenerPosicionContac(contactoEliminar);

		for (Contacto contacto : listaContactos) {
			if(contacto.equals(contactoEliminar)){
				listaContactos[posicionContacto] = null;
				mensaje = "El contacto se ha eliminado de manera exitosa";
			}
		if(posicionContacto == -1){
			throw new ContactoException ("El contacto no ha sido encontrado");
		}
		}
		return mensaje;
	}
	/**
	 * funcion para obtener la posicion del contacto
	 * @param contactoEliminar
	 * @return posicion
	 */
	private int obtenerPosicionContac(Contacto contactoEliminar) {

		int posicion = -1;
		for (int i = 0; i < listaContactos.length; i++) {
			if(listaContactos[i] != null && listaContactos[i].equals(contactoEliminar)){
				return i;
			}
		}
		return posicion;
	}
	/**
	 *esta funcion nos ayuda a saber si la agenda esta llena
	 * @return mensaje
	 */
	public String verificarAgendaLLena(){

		String mensaje = "Lo sentimos, no hay cupo en la agenda";

		for (int i = 0; i < listaContactos.length; i++) {
			if(listaContactos[i] == null){
				mensaje = "Si, hay espacio disponible en la agenda";
			}
		}
		return mensaje;
	}
	/**
	 * esta funcion nos indica cuantos espacios libres quedan en la agenda
	 * @return cantidad de espacios libres
	 */
	public int verificarCantEspaciosLibres(){

		int contador =  0;
		for (int i = 0; i < listaContactos.length; i++) {
			if(listaContactos[i] == null){
				contador ++;
			}
		}
		return contador;
	}

}
