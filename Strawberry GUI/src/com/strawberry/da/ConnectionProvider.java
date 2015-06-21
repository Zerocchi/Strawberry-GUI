package com.strawberry.da;

import java.sql.*;  

import static com.strawberry.da.Provider.*;
	  
public class ConnectionProvider {  
	private static Connection con=null;  
	  
	public static Connection getCon(){  
		if (con != null)
			return con;
	else{
		try{  
			Class.forName(DRIVER);  
			con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);  
		}catch(Exception e){
			e.printStackTrace();
		}  
		}
		return con;
	}  
}  