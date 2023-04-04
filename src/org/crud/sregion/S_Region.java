package org.crud.sregion;
/*
CRUD CREATE     - READ -- UPDATE  --- DELETE

*/

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class S_Region {
	
	/// CONEXION A LA BD 
	private static Connection connection = null;
	private static String driver= "oracle.jdbc.driver.OracleDriver";
	private static String URL= "jdbc:oracle:thin:@localhost:1521:orcl";
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
	//INSERTAR 
	public static void insertaS_Region(int id, String name) throws IOException, SQLException {
		try {
		connectDataBaseOracle();
		String sql = "INSERT INTO S_REGION (ID, NAME) VALUES (?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.executeQuery();
		System.out.println( "INSERTO EL REGISTRO " + id + "," +name);
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception" + e.getMessage());
		}
	}
	
	// actualizar 
	public static void updateS_Region( String name, int id) throws IOException, SQLException {
		try {
		connectDataBaseOracle();
		String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, id);
		ps.executeQuery();
		
		System.out.println( "ACTULIZO EL REGISTRO " + id + "," +name);
		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception" + e.getMessage());
		}
	}
	
	// ELIMINAR
		public static void deleteS_Region( int id) throws IOException, SQLException {
			try {
			connectDataBaseOracle();
			String sql = "DELETE FROM S_REGION  WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			
			System.out.println( "SE ELIMINO:  " + id );
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception" + e.getMessage());
			}
		}
		//  por selecion por id
		public static void selectS_Region( int id) throws IOException, SQLException {
			try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM S_REGION  WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();
			if(rSet.next()) {
				System.out.println(rSet.getInt("id")+ " , " + rSet.getString("name"));
			}
			
			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Exception" + e.getMessage());
			}
		}
		
		// TODOS LOS REGISTROS 
				public static void selectALLS_Region( ) throws IOException, SQLException {
					try {
					connectDataBaseOracle();
					String sql = "SELECT * FROM S_REGION ";
					PreparedStatement ps = connection.prepareStatement(sql);
					ResultSet rSet = ps.executeQuery();
					while(rSet.next()) {
						System.out.println(rSet.getInt("id")+ " , " + rSet.getString("name"));
					}
					
					
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception" + e.getMessage());
					}
				}
				
				// INVOCACION DEL PROCEDIMIENTO DE ALMACENADO PROC 
				public static void invocateProceduresS_Region( int id, String name) throws IOException, SQLException {
					try {
						
					connectDataBaseOracle();
					String sql = "CALL proc(?,?) ";
					CallableStatement cs = connection.prepareCall(sql);
					cs.setInt(1, id);
					cs.setString(2, name);
					cs.execute();
					System.out.println("SE EJECUTO CORRECTAMENTE EL PROCEDIMIENTO");
					
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("Exception" + e.getMessage());
					}
				}		
	
	public static void main(String []arg) throws IOException, SQLException{
	//es para verificar si ahi conecxion	// connectDataBaseOracle();
		//insertaS_Region(14, "OAXACA");
		//updateS_Region("MICHOACAN", 12);
		//deleteS_Region(12);
		//selectS_Region(12);
	    //selectALLS_Region();
		invocateProceduresS_Region(15, "HUAMANTLA");
	}

}
