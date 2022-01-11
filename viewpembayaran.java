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
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import pendaftaran.kontrolpendaftaran;
import pendaftaran.kontrolpendaftaran;
import javax.swing.SwingConstants;

public class viewpembayaran extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldKode_tiket;
	private JTextField textFieldnama;
	private JTextField textFieldharga;
	private JTable tbpembayaran;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private DefaultTableModel model;
	private DefaultTableModel modeltrans;
	private JDateChooser dateChooserkalender;
	private JDateChooser Tanggal = new JDateChooser();
	private JButton btnhapus,btnubah,btnsimpan,btnpembayaran;
	private koneksi Koneksi;
	private kontrolpendaftaran Kontrolpendaftaran;
	private JLabel lblpembayaran;
	
	
	public viewpembayaran() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 10, 612, 405);
		getContentPane().setBackground(Color.BLACK);
		contentPane.add(tabbedPane);
		
		JPanel pembayaran = new JPanel();
		tabbedPane.addTab("Pembayaran Tiket Seminar", null, pembayaran, null);
		pembayaran.setLayout(null);
		
		lblpembayaran = new JLabel(" Pembayaran Tiket  ");
		lblpembayaran.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblpembayaran.setHorizontalAlignment(SwingConstants.CENTER);
		lblpembayaran.setBounds(162, 11, 291, 47);
		pembayaran.add(lblpembayaran);
		
		dateChooserkalender = new JDateChooser();
		dateChooserkalender.setBounds(174, 173, 103, 19);
		pembayaran.add(dateChooserkalender);
		
		JLabel lblKode_tiket = new JLabel("Kode Tiket");
		lblKode_tiket.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKode_tiket.setBounds(22, 71, 109, 23);
		pembayaran.add(lblKode_tiket);
		
		JLabel lblnama = new JLabel("Nama");
		lblnama.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblnama.setBounds(22, 105, 109, 23);
		pembayaran.add(lblnama);
		
		JLabel lblharga = new JLabel("Harga");
		lblharga.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblharga.setBounds(22, 139, 109, 23);
		pembayaran.add(lblharga);
		
		JLabel lbltanggal = new JLabel("Tanggal Pembelian");
		lbltanggal.setBounds(22, 173, 109, 19);
		pembayaran.add(lbltanggal);
		
		textFieldKode_tiket = new JTextField();
		textFieldKode_tiket.setColumns(10);
		textFieldKode_tiket.setBounds(174, 72, 163, 23);
		pembayaran.add(textFieldKode_tiket);

		textFieldnama = new JTextField();
		textFieldnama.setColumns(10);
		textFieldnama.setBounds(174, 106, 163, 23);
		pembayaran.add(textFieldnama);
		scrollPane = new JScrollPane();
		
		textFieldharga = new JTextField();
		textFieldharga.setColumns(10);
		textFieldharga.setBounds(174, 139, 163, 23);
		pembayaran.add(textFieldharga);
		
		scrollPane.setBounds(10, 226, 587, 93);
		pembayaran.add(scrollPane);
		
		tbpembayaran = new JTable();
		scrollPane.setViewportView(tbpembayaran);
		
		model = new DefaultTableModel();
		model.addColumn("Kode Tiket");
		model.addColumn("Nama");
		model.addColumn("Harga");
		model.addColumn("Tanggal");
		tbpembayaran.setModel(model);
		
		btnhapus = new JButton("Hapus");
		btnhapus.setBounds(417, 341, 85, 24);
		pembayaran.add(btnhapus);
		
		btnubah = new JButton("Ubah");
		btnubah.setBounds(322, 342, 85, 24);
		pembayaran.add(btnubah);
		
		btnsimpan = new JButton("Cetak");
		btnsimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnsimpan.setBounds(227, 342, 85, 24);
		pembayaran.add(btnsimpan);
		
		JButton btnkeluar = new JButton("Keluar");
		btnkeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnkeluar.setBounds(512, 342, 85, 24);
		pembayaran.add(btnkeluar);
		
		btnpembayaran = new JButton("Back");
		btnpembayaran.setBounds(10, 342, 109, 23);
		pembayaran.add(btnpembayaran);
	
	}
				
	
// method pembayaran
	
	public DefaultTableModel getModel() {
		return model;
	}
	public JTable getTbpembayaran() {
		return tbpembayaran;
	}
	
	public JTextField gettfKode_tiket() {
		return textFieldKode_tiket;
	}

	public JTextField gettfnama() {
		return textFieldnama;
	}
	
	public JTextField gettfharga() {
		return textFieldharga;
	}
	
	public JDateChooser getTanggal() {
		
		return dateChooserkalender;
	}
	
	
// button bawah

	public JButton getBtnSimpan() {
		return btnsimpan;
	}
	public JButton getBtnUbah() {
		return btnubah;
	}
	public JButton getBtnHapus() {
		return btnhapus;
	}

	
	public void setBtnHapus(JButton btnHapus) {
		this.btnhapus = btnHapus;
	}

	public void setBtnUbah(JButton btnUbah) {
		this.btnubah = btnUbah;
	}

	public void setBtnSimpan(JButton btnSimpan) {
		this.btnsimpan = btnSimpan;
	}
	
	public JButton getBtnpembayaran() {
		return btnpembayaran;
	}

	public void setBtnpembayaran(JButton btnpembayaran) {
		this.btnpembayaran = btnpembayaran;
	}


}
