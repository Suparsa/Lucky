package pendaftaran;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class kontrolpembayaran {
	
	private viewpembayaran Viewpembayaran;
	private koneksi Koneksi;
	private modelpembayaran modelpembayaran;
	private int select = -1;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public kontrolpembayaran (viewpembayaran Viewpembayaran) {
		this.Viewpembayaran = Viewpembayaran;
		modelpembayaran = new modelpembayaran();
		loaddata();
		pembayaran();	
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void pembayaran(){
		
		// TODO Auto-generated method stub

		Viewpembayaran.getBtnSimpan().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simpan();
			}
		});
		Viewpembayaran.getBtnUbah().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ubahdata();
			}
		});
		
		Viewpembayaran.getBtnHapus().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hapusdata();
			}
		});
		
		Viewpembayaran.getTbpembayaran().addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				super.mouseClicked(e);
				selectdata();
			}
		});
		
		Viewpembayaran.getBtnpembayaran().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewpendaftaran Viewpendaftaran = new viewpendaftaran ();
				Viewpendaftaran.setVisible(true);
				kontrolpendaftaran b = new kontrolpendaftaran(Viewpendaftaran);
			}
		});
	}
	
	public void simpan() {	
		
		modelpembayaran.setKode_tiket(Viewpembayaran.gettfKode_tiket().getText());
		modelpembayaran.setnama(Viewpembayaran.gettfnama().getText());
		modelpembayaran.setharga(Viewpembayaran.gettfharga().getText());
		modelpembayaran.settanggal(Viewpembayaran.getTanggal());
		
		try {
			Connection c = koneksi.getkoneksi();
			String sql = "INSERT INTO pembayaran VALUES (?,?,?,?)";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, modelpembayaran.getKode_tiket());
			p.setString(2, modelpembayaran.getnama());
			p.setString(3, modelpembayaran.getharga());
			p.setDate(4, new java.sql.Date(modelpembayaran.gettanggal().getTime()));
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
		modelpembayaran.setKode_tiket(Viewpembayaran.getModel().getValueAt(select, 0).toString());
		try {
			Connection c = koneksi.getkoneksi();
			String sql = "DELETE FROM pembayaran WHERE Kode_tiket = ?";
			PreparedStatement p = c.prepareStatement(sql);
			p.setString(1, modelpembayaran.getKode_tiket());
			p.executeUpdate();
			p.close();	
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		finally {
			loaddata();
			Viewpembayaran.gettfKode_tiket().enable();
			clear();
		}	
	}
	
	
	public void ubahdata() {
		
		if (select == -1) {
			JOptionPane.showMessageDialog(null, "Pilih Satu Data");
			return;
		}
		
		modelpembayaran.setKode_tiket(Viewpembayaran.gettfKode_tiket().getText());
		modelpembayaran.setnama(Viewpembayaran.gettfnama().getText());
		modelpembayaran.setharga(Viewpembayaran.gettfharga().getText());
		modelpembayaran.settanggal(Viewpembayaran.getTanggal());
		
		try {
			Connection c = koneksi.getkoneksi();
			String sql = "UPDATE pembayaran SET nama = ?, harga = ?, tanggal = ? WHERE Kode_tiket = ?";
			PreparedStatement p = c.prepareStatement(sql);
			
			p.setString(4, modelpembayaran.getKode_tiket());
			p.setString(1, modelpembayaran.getnama());
			p.setString(2, modelpembayaran.getharga());
			p.setDate(3, new java.sql.Date(modelpembayaran.gettanggal().getTime()));

			p.executeUpdate();
			p.close();	
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		finally {
			loaddata();
			Viewpembayaran.gettfKode_tiket().enable();
			clear();
		}
	}

	public void loaddata() {
		
		Viewpembayaran.getModel().getDataVector().removeAllElements();
		Viewpembayaran.getModel().fireTableDataChanged();
		try {
			Connection c = koneksi.getkoneksi();
			Statement s = c.createStatement();

			String sql = "SELECT * FROM pembayaran";
			ResultSet r = s.executeQuery(sql);
			
			while(r.next()) {
				
				Object [] o = new Object[4];
				o[0]=r.getString("Kode_tiket");
				o[1]=r.getString("nama");
				o[2]=r.getString("harga");
				o[3]=r.getDate("tanggal");
				
				Viewpembayaran.getModel().addRow(o);
			}
			s.close();
			r.close();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Load data gagal: "+e);
		}
	}

	 void selectdata() {
		 select = Viewpembayaran.getTbpembayaran().getSelectedRow();
		 
		 if(select == -1) {
			 JOptionPane.showMessageDialog(null, "Pilih Satu Data");
			 return;
		 }
		 Viewpembayaran.gettfKode_tiket().setText(Viewpembayaran.getModel().getValueAt(select, 0).toString());
		 Viewpembayaran.gettfnama().setText(Viewpembayaran.getModel().getValueAt(select, 1).toString());
		 Viewpembayaran.gettfharga().setText(Viewpembayaran.getModel().getValueAt(select, 2).toString());
		 Viewpembayaran.getTanggal().setDate((java.util.Date)Viewpembayaran.getModel().getValueAt(select, 3));
		 

		 
	 }

	 public void clear() {
		 Viewpembayaran.gettfKode_tiket().setText("");
		 Viewpembayaran.gettfnama().setText("");
		 Viewpembayaran.gettfharga().setText("");
		 Viewpembayaran.getTanggal().setDate(null);
		}


	public koneksi getKoneksi() {
		return Koneksi;
	}


	public void setKoneksi(koneksi koneksi) {
		Koneksi = koneksi;
	}

}