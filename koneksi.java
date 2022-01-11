package pendaftaran;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

//import warung.koneksi_wrg;

public class koneksi {

	private static Connection koneksi;
	public static Connection getkoneksi() {
		if(koneksi== null){
		try {
			String url = "jdbc:mysql://localhost/pbo";
			String user = "root";
			String pass = "";
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			koneksi = DriverManager.getConnection(url, user, pass);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	return koneksi;
	}
	public void closeConn() {
		try {
			koneksi.close();
		}catch (Exception e) {
			Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE,null,e);
		}
	}
}
