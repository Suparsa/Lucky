package pendaftaran;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import pendaftaran.kontrolpendaftaran;
import javax.swing.SwingConstants;

public class viewpendaftaran extends JFrame {

	private JPanel contentPane; 
	private JTable tbpendaftaran;
	private JTextField textFieldnim;
	private JTextField textFieldnama;
	private JTextField textFieldalamat;
	private JTextField textFieldinstitusi;
	private JTextField textFieldupload;
	private JTextField textFieldemail;
	private JDateChooser Tanggal_lahir = new JDateChooser();
	private JDateChooser dateChooserkalendertanggallahir;
	private JComboBox comboBoxjurusan;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JButton btnhapus,btnubah,btnsimpan,btnpembayaran,btnupload;
	private koneksi Koneksi;
	private kontrolpendaftaran Kontrolpendaftaran;
	private JLabel lbltanggal_lahir;

	//import pendaftaran.kontrolpendaftaran;

	
	public viewpendaftaran() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 10, 759, 600);
		contentPane.add(tabbedPane);
		
		JPanel pendaftaran = new JPanel();
		tabbedPane.addTab("Pendaftaran", null, pendaftaran, null);
		getContentPane().setBackground(Color.BLACK);
		pendaftaran.setLayout(null);
		
		JLabel lblNewformpendaftaranseminar = new JLabel("Form Pendaftaran Seminar ");
		lblNewformpendaftaranseminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewformpendaftaranseminar.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewformpendaftaranseminar.setBounds(265, 14, 262, 49);
		pendaftaran.add(lblNewformpendaftaranseminar);
		
		JLabel lblnim = new JLabel("Nim");
		lblnim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnim.setBounds(22, 74, 109, 23);
		pendaftaran.add(lblnim);

		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblemail.setBounds(22, 108, 109, 23);
		pendaftaran.add(lblemail);
		
		JLabel lblnama = new JLabel("Nama");
		lblnama.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnama.setBounds(22, 142, 109, 23);
		pendaftaran.add(lblnama);
		

		JLabel lbljurusan = new JLabel("Jurusan");
		lbljurusan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbljurusan.setBounds(22, 244, 109, 23);
		pendaftaran.add(lbljurusan);
		
		
		JLabel lblalamat = new JLabel("Alamat");
		lblalamat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblalamat.setBounds(22, 278, 109, 23);
		pendaftaran.add(lblalamat);

				
		JLabel lbltanggal_lahir = new JLabel("Tanggal Lahir");
		lbltanggal_lahir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltanggal_lahir.setBounds(22, 210, 109, 23);
		pendaftaran.add(lbltanggal_lahir);
		
		
		JLabel lblinstitusi = new JLabel("Institusi");
		lblinstitusi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblinstitusi.setBounds(22, 176, 109, 23);
		pendaftaran.add(lblinstitusi);
		

		
		textFieldnim = new JTextField();
		textFieldnim.setColumns(10);
		textFieldnim.setBounds(174, 74, 190, 23);
		pendaftaran.add(textFieldnim);
		
		textFieldemail = new JTextField();
		textFieldemail.setColumns(10);
		textFieldemail.setBounds(174, 109, 190, 23);
		pendaftaran.add(textFieldemail);
		
		textFieldnama = new JTextField();
		textFieldnama.setColumns(10);
		textFieldnama.setBounds(174, 142, 190, 23);
		pendaftaran.add(textFieldnama);
		
		comboBoxjurusan = new JComboBox();
		comboBoxjurusan.setModel(new DefaultComboBoxModel(new String[] {"Teknik Mesin", "Teknik Elektro", "Pariwisata", "Teknik Sipil"}));
		comboBoxjurusan.setBounds(173, 246, 109, 21);
		pendaftaran.add(comboBoxjurusan);		
		
		
		textFieldalamat = new JTextField();
		textFieldalamat.setColumns(10);
		textFieldalamat.setBounds(174, 279, 190, 23);
		pendaftaran.add(textFieldalamat);
		
		dateChooserkalendertanggallahir = new JDateChooser();
		dateChooserkalendertanggallahir.setBounds(174, 214, 108, 19);
		pendaftaran.add(dateChooserkalendertanggallahir);
	
		
		textFieldinstitusi = new JTextField();
		textFieldinstitusi.setColumns(10);
		textFieldinstitusi.setBounds(174, 178, 190, 23);
		pendaftaran.add(textFieldinstitusi);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 406, 734, 108);
		pendaftaran.add(scrollPane);
		
		tbpendaftaran = new JTable();
		scrollPane.setViewportView(tbpendaftaran);
		
		
		model = new DefaultTableModel();
		model.addColumn("Nim");
		model.addColumn("Email");
		model.addColumn("Nama");
		model.addColumn("Jurusan");
		model.addColumn("Alamat");
		model.addColumn("Tanggal_lahir");
		model.addColumn("Institusi");
		tbpendaftaran.setModel(model);
		
		btnhapus = new JButton("Hapus");
		btnhapus.setBounds(564, 525, 85, 24);
		pendaftaran.add(btnhapus);
		
		btnubah = new JButton("Ubah");
		btnubah.setBounds(469, 525, 85, 24);
		pendaftaran.add(btnubah);
		
		btnsimpan = new JButton("Simpan");
		btnsimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnsimpan.setBounds(374, 525, 85, 24);
		pendaftaran.add(btnsimpan);
		
		JButton btnkeluar = new JButton("Keluar");
		btnkeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnkeluar.setBounds(659, 525, 85, 24);
		pendaftaran.add(btnkeluar);
		
		btnpembayaran = new JButton("Pembayaran");
		btnpembayaran.setBounds(503, 74, 146, 23);
		pendaftaran.add(btnpembayaran);
		
	}		
				
	
// LoadData ();
	
	public DefaultTableModel getModel() {
		return model;
	}
	public JTable getTbpendaftaran() {
		return tbpendaftaran;
	}
	
	//----------------------------------------------------------------------------------//
	public JTextField gettfnim() {
		return textFieldnim;
	}
	
	//----------------------------------------------------------------------------------//
	public JTextField gettfemail() {
		return textFieldemail;
	}
	
	//----------------------------------------------------------------------------------//
	public JTextField gettfnama() {
		return textFieldnama;
	}
	
	//----------------------------------------------------------------------------------//
	public JTextField gettfalamat() {
		return textFieldalamat;
	}
	
	//----------------------------------------------------------------------------------//
	public JDateChooser gettanggal_lahir() {
		return dateChooserkalendertanggallahir;
	 }
	
	//----------------------------------------------------------------------------------//
	public JComboBox<?> getComboBoxjurusan() {
		return comboBoxjurusan;
	}
	
	//----------------------------------------------------------------------------------//
	public JTextField gettfinstitusi() {
		return textFieldinstitusi;
	}
	
	//---------------------------------------------------------------------------------//
	

	
// BOTTON BAWAH FORM
	
	public JButton getBtnSimpan() {
		return btnsimpan;
	}
	
	public void setBtnSimpan(JButton btnSimpan) {
		this.btnsimpan = btnSimpan;
	}
	
	//----------------------------------------------------------------------------------//
	public JButton getBtnUbah() {
		return btnubah;
	}
	
	public void setBtnUbah(JButton btnUbah) {
		this.btnubah = btnUbah;
	}
	
	//----------------------------------------------------------------------------------//
	public JButton getBtnHapus() {
		return btnhapus;
	}
	
	public void setBtnHapus(JButton btnHapus) {
		this.btnhapus = btnHapus;
	}
	
	//----------------------------------------------------------------------------//
	
	public JButton getBtnupload() {
		return btnupload;
	}
	
	public void setBtnupload(JButton btnupload) {
		this.btnupload = btnupload;
	}
	//----------------------------------------------------------------------------------//
	public JButton getBtnpembayaran() {
		return btnpembayaran;
	}
	

	public void setBtnNewpembayaran(JButton btnpembayaran) {
		this.btnpembayaran = btnpembayaran;
	}
}
