package org.reportescrudtarea;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Reportescrudtarea {

	/// CONEXION A LA BD 
		private static Connection connection = null;
		private static String driver= "oracle.jdbc.driver.OracleDriver";
		private static String URL= "jdbc:oracle:thin:@localhost:1521:orcl";
		@SuppressWarnings("unused")
		private static void connectDataBaseOracle() throws IOException, SQLException{
			try {
				
				Class.forName(driver).newInstance();
				System.out.println("CARGO EL DRIVER CORRECTAMENTE: OJDBC6.JAR");
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception" + e.getMessage());
			}
			
			try {
				
				connection = DriverManager.getConnection(URL, "SYSTEM", "123456");
				System.out.println("CONECXION EXITOSA! :ORACLE 11G");
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception" + e.getMessage());
			}
		}
		// INSERTAR
		public static void insertReporte(int id, String nombre, String fecha, String descripcion) throws IOException, SQLException {
			try {
			connectDataBaseOracle();
			String sql = "INSERT INTO REPORTE (ID, NOMBRE, FECHA, DESCRIPCION) VALUES (?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3,fecha);
			ps.setString(4, descripcion);
			ps.executeQuery();
			System.out.println( "INSERTO EL REGISTRO " + id + "," +nombre + "," + fecha + "," + descripcion);
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception" + e.getMessage());
			}
		}
		
		// ACTUALIZAR
		public static void updateREPORTE(  String nombre, int id ) throws IOException, SQLException {
			try {
			connectDataBaseOracle();
			String sql = "UPDATE REPORTE SET NOMBRE = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, id);
			ps.executeQuery();
			
			System.out.println( "SE ACTULIZO EL REGISTRO " + id + "," +nombre);
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception" + e.getMessage());
			}
		}
		
		// ELIMINAR
				public static void deleteReporte( int id) throws IOException, SQLException {
					try {
					connectDataBaseOracle();
					String sql = "DELETE FROM REPORTE  WHERE ID = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, id);
					ps.executeQuery();
					
					System.out.println( "SE ELIMINO:  " + id );
					
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception" + e.getMessage());
					}
				}
		
				// por selecion por id
				public static void select_Reporte( int id) throws IOException, SQLException {
					try {
					connectDataBaseOracle();
					String sql = "SELECT * FROM REPORTE  WHERE ID = ?";
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, id);
					ResultSet rSet = ps.executeQuery();
					if(rSet.next()) {
						System.out.println(rSet.getInt("id")+ " , " + rSet.getString("nombre"));
					}
					
					
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception" + e.getMessage());
					}
				}
				
				// TODOS LOS REGISTROS 
				public static void selectALLS_Reporte( ) throws IOException, SQLException {
					try {
					connectDataBaseOracle();
					String sql = "SELECT * FROM REPORTE ";
					PreparedStatement ps = connection.prepareStatement(sql);
					ResultSet rSet = ps.executeQuery();
					while(rSet.next()) {
						System.out.println(rSet.getInt("id")+ " , " + rSet.getString("nombre") + rSet.getString("fecha") +"," + rSet.getString("descripcion"));
					}
					
					
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception" + e.getMessage());
					}
				}

  public static void main (String [] arg) throws IOException, SQLException {
	  //insertReporte(1, "REPORTE VIAL " , "15/05/2019 " , "ACCIDENTES MULTIPLES");
	  //insertReporte(2, "REPORTE MARITIMO " , "24/01/2013 " , "ACCIDENTES DE EMBARCACION");
	  //insertReporte(3, "REPORTE DE SALUD " , "01/07/2016 " , "COVID ");
	  //insertReporte(4, "REPORTE FORESTAL " , "10/08/2019 " , "INCENDIOS EN MONTAÑAS");
	  //insertReporte(5, "REPORTE CONTAMINACION " , "21/03/2023 " , "SUBIO UN 50% EL SMOCK");
	  updateREPORTE("REPORTE VIAL SEGUNDO " , 1);
	  //deleteReporte(5);
	  //select_Reporte(1);
	  //selectALLS_Reporte();
	  
	
}
}
