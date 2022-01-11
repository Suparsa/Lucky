package pendaftaran;

import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class modelpembayaran {
	
	private modelpembayaran View;
	/**
	 * @wbp.parser.entryPoint
	 */
	public modelpembayaran() {	}
	
	private String Kode_tiket;
	private String nama;
	private String harga;
	private JDateChooser tanggal;
	private Date tgl;
	
	

	public String getKode_tiket() {
		return Kode_tiket;
	}
	public void setKode_tiket(String Kode_tiket) {
		this.Kode_tiket = Kode_tiket;
	}
	
	public String getnama() {
		return nama;
	}
	public void setnama(String nama) {
		this.nama = nama;
	}
	
	
	public String getharga() {
		return harga;
	}
	public void setharga(String harga) {
		this.harga = harga;
	}
	
	
	public Date gettgl() {
		return tgl;
	}
	public void settgl(Date tgl) {
		this.tgl = tgl;
	}


	public Date gettanggal() {
		
		return tgl;
	}
	public void settanggal(JDateChooser tanggal) {
		tgl = tanggal.getDate();
		
	}
	public JDateChooser getTanggal() {
		return tanggal;
	}
	public void setTanggal(JDateChooser tanggal) {
		this.tanggal = tanggal;
	}
	public modelpembayaran getView() {
		return View;
	}
	public void setView(modelpembayaran view) {
		View = view;
	}
	
	
}
