package retoUD8_nivelintermedio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Gestion_Cine {
	
	static Scanner sc = new Scanner(System.in);
	static int opcion = 0;
	static String tabla = "";
	static String operacion = "";
	static Connection conexion = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			do {
				conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestor_cines", "root", "");
				
				do {
					System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
										"| Elige el número correspondiente a la operación que quieres realizar         |\n" +
										"|-----------------------------------------------------------------------------|\n" +
										"|  1. Insertar                                                                |\n" + 
										"|  2. Borrar                                                                  |\n" +
										"|  3. Modificar                                                               |\n" +
										"|  4. Visualizar                                                              |\n" +
										"-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n");
					
					RespuestaOperaciones();
				}while(opcion<1 || opcion>4);

				do {
					System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
										"| Elige el número correspondiente a la tabla a la que quieres agregar datos   |\n" +
										"|-----------------------------------------------------------------------------|\n" +
										"|  1. Cines                                                                   |\n" + 
										"|  2. Películas                                                               |\n" +
										"|  3. Salas                                                                   |\n" +
										"-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n");
			
					RespuestaTablas();
				}while(opcion<1 || opcion>3);

				do {
					System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-\n" +
										"| ¿Deseas realizar otra operación? |\n" +
										"|-----------------------------------\n" +
										"|  1. Sí                           |\n" + 
										"|  2. No                           |\n" +
										"-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n");
					
					RespuestaRepetirPrograma();
				}while(opcion<1 || opcion>2);
				
			}while(opcion==1);
			
			conexion.close();
			sc.close();	
			
		}catch (SQLException e) {
			System.err.println("Error al establecer la conexión: " + e.getMessage());
			}
	}

	public static void RespuestaRepetirPrograma() {
		try {
			opcion=sc.nextInt();
			if(opcion<1 || opcion>2) System.out.println("Sólo puedes introducir un número comprendido entre 1 y 2.");
			
		}catch (Exception ex) {
			System.out.println("Introduce números.");
			sc.next();
		}
	}
	
	public static void RespuestaOperaciones() throws SQLException {
		try {
			opcion=sc.nextInt();
			if(opcion<1 || opcion>4) System.out.println("Sólo puedes introducir un número comprendido entre 1 y 4.");
			
			switch(opcion) {
			case 1:
				operacion="INSERT INTO";
				break;
				
			case 2:
				operacion="DELETE FROM";
				break;
				
			case 3:
				operacion="UPDATE";
				break;
				
			case 4:
				operacion="SELECT * FROM";
				break;
			}
			
		}catch (Exception ex) {
			System.out.println("Introduce números.");
			sc.next();
		}
	}

	public static void RespuestaTablas() throws SQLException {
		try {
			opcion=sc.nextInt();
			if(opcion<1 || opcion>3) System.out.println("Sólo puedes introducir un número comprendido entre 1 y 3.");
			
			switch(opcion) {
			case 1:
				tabla="Cines";
				int identificador;
				String nombreCine, direccion;
				
				if (operacion.equalsIgnoreCase("INSERT INTO")) {
					System.out.println("Introduce el identificador del cine:");
					identificador=sc.nextInt();
					System.out.println("Introduce el nombre del cine:");
					sc.nextLine();
					nombreCine=sc.nextLine();
					System.out.println("Introduce la direccion del cine:");
					direccion=sc.nextLine();
					
					InsertarCine(identificador, nombreCine, direccion);
					break;
				}
				else if(operacion.equalsIgnoreCase("DELETE FROM")) {
					System.out.println("Introduce el 'Identificador' del cine que quieres eliminar:");
					identificador=sc.nextInt();
					
					EliminarDatos(identificador);
					break;
				}
				else if(operacion.equalsIgnoreCase("UPDATE")) {
					System.out.println("Introduce el 'Identificador' del cine que quieres modificar:");
					identificador=sc.nextInt();
					System.out.println("Introduce el nombre del cine:");
					sc.nextLine();
					nombreCine=sc.nextLine();
					System.out.println("Introduce la direccion del cine:");
					direccion=sc.nextLine();
					
					ModificarCine(identificador, nombreCine, direccion);
					break;
				}
				else {			
					VisualizarCines();
					break;
				}
				
			case 2:
				tabla="Peliculas";
				double precio;
				int duracion, clasificacion;
				String titulo, genero, director;
				
				if (operacion.equalsIgnoreCase("INSERT INTO")) {
					System.out.println("Introduce el identificador de la pelicula:");
					identificador=sc.nextInt();
					System.out.println("Introduce el titulo de la pelicula:");
					sc.nextLine();
					titulo=sc.nextLine();
					System.out.println("Introduce la duracion (minutos) de la pelicula:");
					duracion=sc.nextInt();
					System.out.println("Introduce el genero de la pelicula:");
					sc.nextLine();
					genero=sc.nextLine();
					System.out.println("Introduce el director de la pelicula:");
					director=sc.nextLine();
					System.out.println("Introduce la clasificacion por edad de la pelicula:");
					clasificacion=sc.nextInt();
					System.out.println("Introduce el precio de la entrada de la pelicula:");
					precio=sc.nextDouble();
					
					InsertarPelicula(identificador, titulo, duracion, genero, director, clasificacion, precio);
					break;
				}
				else if(operacion.equalsIgnoreCase("DELETE FROM")) {
					System.out.println("Introduce el 'Identificador' de la pelicula que quieres eliminar:");
					identificador=sc.nextInt();
					
					EliminarDatos(identificador);
					break;
				}
				else if(operacion.equalsIgnoreCase("UPDATE")) {
					System.out.println("Introduce el identificador de la pelicula:");
					identificador=sc.nextInt();
					System.out.println("Introduce el titulo de la pelicula:");
					sc.nextLine();
					titulo=sc.nextLine();
					System.out.println("Introduce la duracion (minutos) de la pelicula:");
					duracion=sc.nextInt();
					System.out.println("Introduce el genero de la pelicula:");
					sc.nextLine();
					genero=sc.nextLine();
					System.out.println("Introduce el director de la pelicula:");
					director=sc.nextLine();
					System.out.println("Introduce la clasificacion por edad de la pelicula:");
					clasificacion=sc.nextInt();
					System.out.println("Introduce el precio de la entrada de la pelicula:");
					precio=sc.nextDouble();
					
					ModificarPelicula(identificador, titulo, duracion, genero, director, clasificacion, precio);
					break;
				}
				else {			
					VisualizarPeliculas();
					break;
				}
				
			case 3:
				tabla="Salas";
				int capacidad, metros;
				
				if (operacion.equalsIgnoreCase("INSERT INTO")) {
					System.out.println("Introduce el identificador de la sala:");
					identificador=sc.nextInt();
					System.out.println("Introduce la capacidad de la sala:");
					capacidad=sc.nextInt();
					System.out.println("Introduce los metros cuadrados de la sala:");
					metros=sc.nextInt();
				
					InsertarSala(identificador, capacidad, metros);
					break;
				}
				else if(operacion.equalsIgnoreCase("DELETE FROM")) {
					System.out.println("Introduce el 'Identificador' de la sala que quieres eliminar:");
					identificador=sc.nextInt();
					
					EliminarDatos(identificador);
					break;
				}
				else if(operacion.equalsIgnoreCase("UPDATE")) {
					System.out.println("Introduce el 'Identificador' de la sala que quieres modificar:");
					identificador=sc.nextInt();
					System.out.println("Introduce la capacidad de la sala:");
					capacidad=sc.nextInt();
					System.out.println("Introduce los metros cuadrados de la sala:");
					metros=sc.nextInt();
					
					ModificarSala(identificador, capacidad, metros);
					break;
				}
				else {			
					VisualizarSalas();
					break;
				}
			}
		
		}catch (Exception ex) {
			System.out.println("Introduce números.");
			sc.next();
		}
	}
	
	public static void VisualizarCines() throws SQLException {
		try {
			String query=operacion + " " + tabla;
			pstmt=conexion.prepareStatement(query);
			rs=pstmt.executeQuery();
			if(!rs.isBeforeFirst())System.out.println("No hay cines registrados");
			else {
				while(rs.next()) {
					int identificador = rs.getInt("Identificador");
					String nombreCine = rs.getString("NombreCine");
					String direccion = rs.getString("Direccion");
					
					System.out.println("Cine:\n" +
										" Identificador: " + identificador + "\n" +
										" NombreCine: " + nombreCine + "\n" +
										" Direccion: " + direccion + "\n");
				}
			}
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	public static void VisualizarPeliculas() throws SQLException {
		try {
			String query=operacion + " " + tabla;
			pstmt=conexion.prepareStatement(query);
			rs=pstmt.executeQuery();
			if(!rs.isBeforeFirst())System.out.println("No hay peliculas registradas");
			else {
				while(rs.next()) {
					int identificador = rs.getInt("Identificador");
					String titulo = rs.getString("Titulo");
					int duracion = rs.getInt("DuracionMinutos");
					String genero = rs.getString("Genero");
					String director = rs.getString("Director");
					int clasificacion = rs.getInt("ClasificacionPorEdad");
					double precio = rs.getDouble("Precio");
					
					System.out.println("Película:\n" +
										" Identificador: " + identificador + "\n" +
										" Titulo: " + titulo + "\n" +
										" Duracion: " + duracion + "\n" + 
										" Genero: " + genero + "\n" +
										" Director: " + director + "\n" +
										" ClasificacionPorEdad: " + clasificacion + "\n" + 
										" Precio: " + precio + "\n");
				}
			}
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	public static void VisualizarSalas() throws SQLException {
		try {
			String query=operacion + " " + tabla;
			pstmt=conexion.prepareStatement(query);
			rs=pstmt.executeQuery();
			if(!rs.isBeforeFirst())System.out.println("No hay salas registradas");
			else {
				while(rs.next()) {
					int identificador = rs.getInt("Identificador");
					int capacidad = rs.getInt("Capacidad");
					int metros = rs.getInt("MetrosCuadrados");
					
					System.out.println("Sala:\n" +
										" Identificador: " + identificador + "\n" +
										" Capacidad: " + capacidad + "\n" +
										" MetrosCuadrados: " + metros + "\n");
				}
			}
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}

	private static void EliminarDatos(int identificador) throws SQLException{
		try {
			String query= operacion + " " + tabla + " WHERE Identificador = ?";
			pstmt=conexion.prepareStatement(query);
			pstmt.setInt(1, identificador);
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) System.out.println("Campos eliminados correctamente.");
			else System.out.println("No se ha podido eliminar el campo.");
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	private static void ModificarCine(int identificador, String nombreCine, String direccion) throws SQLException{
		try {
			String query= operacion + " " + tabla + " SET NombreCine=?, Direccion=? WHERE Identificador = ?";
			pstmt=conexion.prepareStatement(query);
			pstmt.setString(1, nombreCine);
			pstmt.setString(2, direccion);
			pstmt.setInt(3, identificador);
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) System.out.println("Campos modificados correctamente.");
			else System.out.println("No se han podido modificar los campos.");
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	private static void ModificarPelicula(int identificador, String titulo, int duracion, String genero, String director, int clasificacion, double precio) throws SQLException{
		try {
			String query= operacion + " " + tabla + " SET Titulo=?, DuracionMinutos=?, Genero=?, Director=?, ClasificacionPorEdad=?, Precio=? WHERE Identificador = ?";
			pstmt=conexion.prepareStatement(query);
			pstmt.setString(1, titulo);
			pstmt.setInt(2, duracion);
			pstmt.setString(3, genero);
			pstmt.setString(4, director);
			pstmt.setInt(5, clasificacion);
			pstmt.setDouble(6, precio);
			pstmt.setInt(7, identificador);
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) System.out.println("Campos modificados correctamente.");
			else System.out.println("No se han podido modificar los campos.");
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	private static void ModificarSala(int identificador, int capacidad, int metros) throws SQLException{
		try {
			String query= operacion + " " + tabla + " SET Capacidad=?, MetrosCuadrados=? WHERE Identificador = ?";
			pstmt=conexion.prepareStatement(query);
			pstmt.setInt(1, capacidad);
			pstmt.setInt(2, metros);
			pstmt.setInt(3, identificador);
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) System.out.println("Campos modificados correctamente.");
			else System.out.println("No se han podido modificar los campos.");
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}

	public static void InsertarCine(int identificador, String nombreCine, String direccion) throws SQLException {
		try {
			String query="INSERT INTO Cines (Identificador, NombreCine, Direccion) VALUES (?, ?, ?)";
			pstmt=conexion.prepareStatement(query);
			pstmt.setInt(1, identificador);
			pstmt.setString(2, nombreCine);
			pstmt.setString(3, direccion);
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) System.out.println("Campos insertados correctamente.");
			else System.out.println("No se ha podido completar el registro.");
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	public static void InsertarPelicula(int identificador, String titulo, int duracion, String genero, String director, int clasificacion, double precio) throws SQLException {
		try {
			String query="INSERT INTO Peliculas (Identificador, Titulo, DuracionMinutos, Genero, Director, ClasificacionPorEdad, Precio) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pstmt=conexion.prepareStatement(query);
			pstmt.setInt(1, identificador);
			pstmt.setString(2, titulo);
			pstmt.setInt(3, duracion);
			pstmt.setString(4, genero);
			pstmt.setString(5, director);
			pstmt.setInt(6, clasificacion);
			pstmt.setDouble(7, precio);
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) System.out.println("Campos insertados correctamente.");
			else System.out.println("No se ha podido completar el registro.");
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
	
	public static void InsertarSala(int identificador, int capacidad, int metros) throws SQLException {
		try {
			String query="INSERT INTO Salas (Identificador, Capacidad, MetrosCuadrados) VALUES (?, ?, ?)";
			pstmt=conexion.prepareStatement(query);
			pstmt.setInt(1, identificador);
			pstmt.setInt(2, capacidad);
			pstmt.setInt(3, metros);
			
			int rowsUpdated = pstmt.executeUpdate();
			if(rowsUpdated > 0) System.out.println("Campos insertados correctamente.");
			else System.out.println("No se ha podido completar el registro.");
		}finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
}
