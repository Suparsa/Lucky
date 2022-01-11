package pendaftaran;

import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class modelpendaftaran {
	
	private modelpendaftaran View;
	public modelpendaftaran() {	}
	
	private String nim;
	private String email;
	private String nama;
	private String jurusan;
	private String alamat;
	private String institusi;
	private JDateChooser tanggal_lahir;
	private Date tgllahir;
	private String nama_lama;
	
	
	
	
	//----------------------------------------------------------------------------------//
		public String getnim() {
			return nim;
		}
		public void setnim(String nim) {
			this.nim = nim;
		}
	
	//----------------------------------------------------------------------------------//
		
	       public String getemail() {
					return email;
				}
				public void setemail(String email) {
					this.email = email;
				}
			
		//----------------------------------------------------------------------------------//
		public String getnama() {
			return nama;
		}
		public void setnama(String nama) {
			this.nama = nama;
		}
	
	//----------------------------------------------------------------------------------//
		public String getjurusan() {
			return jurusan;
		}
		public void setjurusan(String jurusan) {
			this.jurusan = jurusan;
		}
		
		
	//----------------------------------------------------------------------------------//
		public String getalamat() {
			return alamat;
		}
		public void setalamat(String alamat) {
			this.alamat = alamat;
		}
	
	//----------------------------------------------------------------------------------//
		public Date gettgllahir() {
			return tgllahir;
		}
		public void settgllahir(Date tgllahir) {
			this.tgllahir = tgllahir;
		}

		public Date gettanggal_lahir() {
			return tgllahir;
		}
		public void settanggal_lahir(JDateChooser tanggal_lahir) {
			tgllahir = tanggal_lahir.getDate();
		}
	
	//----------------------------------------------------------------------------------//
		
		//----------------------------------------------------------------------------------//
				public String getinstitusi() {
					return institusi;
				}
				public void setinstitusi(String institusi) {
					this.institusi = institusi;
				}
			
			//----------------------------------------------------------------------------------//
	
	//----------------------------------------------------------------------------------//
		
	
	//----------------------------------------------------------------------------------//
		public String getnama_lama() {
			return nama_lama;
		}
		public void setnama_lama(String nama_lama) {
			this.nama_lama = nama_lama;
		}
		
}
