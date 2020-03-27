package sistemasInteligentes;
/*************************************************************************************************************************
 * NOMBRE CLASE: Serializar
 *                                                                         
 * AUTOR: 
 *	- Eduardo Mora Gonz·lez 
 * 
 * PRINCIPAL FUNCION: 
 * 
 * 	Metodo para la encriptacion MD5
 * 	                                                                                                                                               
 ************************************************************************************************************************/
import java.security.MessageDigest;
public class Serializa {
	
	/**
	 * Clase creada a partir del codigo usado en el siguiente enlace:
	 * http://lineasycodigos.blogspot.com.es/2013/01/encriptacion-en-md5-usando-java.html
	 * Clase que implementa el m√©todo para serializar en MD5 un objeto
	 */
	
		private static final Object[] CONSTS_HEX = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
		
		public static String encriptarMD5(String mensaje) throws Exception{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(mensaje.getBytes());
			StringBuilder sb = new StringBuilder(2*bytes.length);
			for(int i = 0; i< bytes.length;i++){
				int bajo = (int)(bytes[i] & 0x0f);
				int alto = (int)((bytes[i] & 0xf0) >>4);
				sb.append(CONSTS_HEX[alto]);
				sb.append(CONSTS_HEX[bajo]);
			}
			return sb.toString();
		}
}
	


