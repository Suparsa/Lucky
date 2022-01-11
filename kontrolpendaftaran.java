package pendaftaran;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.sql.Statement;
//import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import pendaftaran.koneksi;


public class kontrolpendaftaran {
  
	//public void pendaftaran
	
	private viewpendaftaran Viewpendaftaran;
	private koneksi Koneksi;
	private modelpendaftaran Modelpendaftaran;
	private int select = -1;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public kontrolpendaftaran(viewpendaftaran Viewpendaftaran) {
		this.Viewpendaftaran = Viewpendaftaran;
		Modelpendaftaran = new modelpendaftaran();
		//kontrolpendaftaran trn = new kontrolpendaftaran(Viewpendaftaran);
		loaddata();
		pendaftaran();
		
	}
	
	
	
	public void pendaftaran(){
		// TODO Auto-generated method stub

		Viewpendaftaran.getBtnSimpan().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simpan();
			}
		});
		Viewpendaftaran.getBtnUbah().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ubahdata();
			}
		});
		
		Viewpendaftaran.getBtnHapus().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hapusdata();
			}
		});
		
		Viewpendaftaran.getTbpendaftaran().addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				selectdata();
			}
		});
		
		Viewpendaftaran.getBtnpembayaran().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewpembayaran viewpembayaran = new viewpembayaran ();
				viewpembayaran.setVisible(true);
				kontrolpembayaran b = new kontrolpembayaran(viewpembayaran);	
			}
		});
	}
	
	public void simpan() {	
		
		Modelpendaftaran.setnim(Viewpendaftaran.gettfnim().getText());
		Modelpendaftaran.setemail(Viewpendaftaran.gettfemail().getText());
		Modelpendaftaran.setnama(Viewpendaftaran.gettfnama().getText());
		Modelpendaftaran.setjurusan(Viewpendaftaran.getComboBoxjurusan().getSelectedItem().toString());
		Modelpendaftaran.setalamat(Viewpendaftaran.gettfalamat().getText());
		Modelpendaftaran.settanggal_lahir(Viewpendaftaran.gettanggal_lahir());
		Modelpendaftaran.setinstitusi(Viewpendaftaran.gettfinstitusi().getText());
		
		
		try {
			 Connection c = koneksi.getkoneksi();
		        String sql = "INSERT INTO pendaftaran VALUES (?,?,?,?,?,?,?)";
		        PreparedStatement p = c.prepareStatement(sql);
		        p.setString(1, Modelpendaftaran.getnim());
		        p.setString(2, Modelpendaftaran.getemail());
		        p.setString(3, Modelpendaftaran.getnama());
		        p.setString(4, Modelpendaftaran.getjurusan());
		        p.setString(5, Modelpendaftaran.getalamat());
		        p.setDate(6, new java.sql.Date(Modelpendaftaran.gettanggal_lahir().getTime()));
				p.setString(7, Modelpendaftaran.getinstitusi());
				
				
		        
		        p.executeUpdate();
		        p.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		finally {
			loaddata();
			clear();
		}
	}
	public void hapusdata() {
		if (select == -1) {
			JOptionPane.showMessageDialog(null, "Pilih Satu Data");
			return;
		}
		Modelpendaftaran.setnim(Viewpendaftaran.getModel().getValueAt(select, 0).toString());
		try {
			Connection c = koneksi.getkoneksi();
			String sql = "DELETE FROM pendaftaran WHERE nim = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, Modelpendaftaran.getnim());
			p.executeUpdate();
			p.close();	
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		finally {
			loaddata();
			Viewpendaftaran.gettfnim().enable();
			clear();
		}	
	}
	public void ubahdata() {
		

		if (select == -1) {
			JOptionPane.showMessageDialog(null, "Pilih Satu Data");
			return;
		}
		
		
		Modelpendaftaran.setnim(Viewpendaftaran.gettfnim().getText());
		Modelpendaftaran.setemail(Viewpendaftaran.gettfemail().getText());
		Modelpendaftaran.setnama(Viewpendaftaran.gettfnama().getText());
		Modelpendaftaran.setjurusan(Viewpendaftaran.getComboBoxjurusan().getSelectedItem().toString());
		Modelpendaftaran.setalamat(Viewpendaftaran.gettfalamat().getText());
		Modelpendaftaran.setinstitusi(Viewpendaftaran.gettfinstitusi().getText());
		
		
		
		try {
			Connection c = koneksi.getkoneksi();
			String sql = "UPDATE pendaftaran SET email = ?, nama = ?, jurusan = ?, alamat = ?, institusi = ? WHERE nim = ?";
			PreparedStatement p = c.prepareStatement(sql);
			
			    p.setString(6, Modelpendaftaran.getnim());
		        p.setString(1, Modelpendaftaran.getemail());
		        p.setString(2, Modelpendaftaran.getnama());
		        p.setString(3, Modelpendaftaran.getjurusan());
		        p.setString(4, Modelpendaftaran.getalamat());
				p.setString(5, Modelpendaftaran.getinstitusi());
				
			p.executeUpdate();
			p.close();	
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		finally {
			loaddata();
			Viewpendaftaran.gettfnim().enable();
			clear();
		}
	}

	public void loaddata() {
		
		Viewpendaftaran.getModel().getDataVector().removeAllElements();
		Viewpendaftaran.getModel().fireTableDataChanged();
		try {
			Connection c = koneksi.getkoneksi();
			Statement s = c.createStatement();

			String sql = "SELECT * FROM pendaftaran";
			ResultSet r = s.executeQuery(sql);
			
			while(r.next()) {
				
				Object [] o = new Object[7];
				o[0]=r.getString("nim");
				o[1]=r.getString("email");
				o[2]=r.getString("nama");
				o[3]=r.getString("jurusan");
				o[4]=r.getString("alamat");
				o[5]=r.getDate("tanggal_lahir");
				o[6]=r.getString("institusi");
				
				
				Viewpendaftaran.getModel().addRow(o);
			}
			s.close();
			r.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Load data gagal: "+e);
		}
	}

	 void selectdata() {
		 select = Viewpendaftaran.getTbpendaftaran().getSelectedRow();
		 
		 if(select == -1) {
			 JOptionPane.showMessageDialog(null, "Pilih Satu Data");
			 return;
		 }
		 
		 //----------------------------------------------------------------------------------//
		 Viewpendaftaran.gettfnim().setText(Viewpendaftaran.getModel().getValueAt(select, 0).toString());
		 
		 //----------------------------------------------------------------------------------//
		 Viewpendaftaran.gettfemail().setText(Viewpendaftaran.getModel().getValueAt(select, 1).toString());
		 
		 //----------------------------------------------------------------------------------//
		 Viewpendaftaran.gettfnama().setText(Viewpendaftaran.getModel().getValueAt(select, 2).toString());
		 
		 //----------------------------------------------------------------------------------//
		 
		 Viewpendaftaran.getComboBoxjurusan().setSelectedItem(Viewpendaftaran.getModel().getValueAt(select, 3).toString());
		 
		 //------------------------------------------------------------------------------------//
		 
		 Viewpendaftaran.gettfalamat().setText(Viewpendaftaran.getModel().getValueAt(select, 4).toString());
		 
		 //----------------------------------------------------------------------------------//
		 
		 Viewpendaftaran.gettfinstitusi().setText(Viewpendaftaran.getModel().getValueAt(select, 6).toString());
		 
		 //------------------------------------------------------------------------------------//
	 }

	 public void clear() {
		 Viewpendaftaran.gettfnim().setText("");
		 Viewpendaftaran.gettfemail().setText("");
		 Viewpendaftaran.gettfnama().setText("");
		 Viewpendaftaran.getComboBoxjurusan().getSelectedIndex();
		 Viewpendaftaran.gettfalamat().setText("");
		 Viewpendaftaran.gettfinstitusi().setText("");

	 }



	public koneksi getKoneksi() {
		return Koneksi;
	}



	public void setKoneksi(koneksi koneksi) {
		Koneksi = koneksi;
	}
}
