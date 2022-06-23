package beans;

import java.util.ArrayList;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserBean {

	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String email;
	private String nTelefono;
	private String password;
	private String sesso; //puo valere F o M
	private java.sql.Date dataNascita;
	private ArrayList<MetodoPagamentoBean> elencoMetodiPagamento = new ArrayList<>();
	private ArrayList<IndirizzoBean> indirizziSpedizione;
	private Boolean admin = false;
	
	public UserBean() {

	}
	
	public String getDataNascitaAsString() {
		if(this.dataNascita == null)
			return "Impossibile leggere data";
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		String strDate= formatter.format(this.dataNascita);  
		return strDate;
	}
	
	public ArrayList<MetodoPagamentoBean> getElencoMetodiPagamento() {
		return elencoMetodiPagamento;
	}



	public ArrayList<IndirizzoBean> getIndirizziSpedizione() {
		return indirizziSpedizione;
	}


	public void setIndirizziSpedizione(ArrayList<IndirizzoBean> indirizziSpedizione) {
		this.indirizziSpedizione = indirizziSpedizione;
	}


	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public void setElencoMetodiPagamento(ArrayList<MetodoPagamentoBean> elencoMetodiPagamento) {
		this.elencoMetodiPagamento = elencoMetodiPagamento;
	}


	@Override
	public String toString() {
		return "UserBean [nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale + ", email="
				+ email + ", nTelefono=" + nTelefono + ", password=" + password + ", sesso=" + sesso + ", dataNascita="
				+ dataNascita + ", elencoMetodiPagamento=" + elencoMetodiPagamento + ", admin=" + admin + "]";
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getnTelefono() {
		return nTelefono;
	}
	public void setnTelefono(String nTelefono) {
		this.nTelefono = nTelefono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSesso() {
		return this.sesso;
	}
	
	public void setSesso(String sesso){
		this.sesso = sesso;
	}
	
	public java.sql.Date getDataNascita() {
		return this.dataNascita;
	}
	
	public void setDataNascita(java.sql.Date data) {
		this.dataNascita = data;
	}
	
	public void addAddress(IndirizzoBean i) {
		this.indirizziSpedizione.add(i);
	}


	public Boolean getAdmin() {
		return admin;
	}


	public void setAdmin(boolean b) {
		this.admin = b;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admin == null) ? 0 : admin.hashCode());
		result = prime * result + ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((elencoMetodiPagamento == null) ? 0 : elencoMetodiPagamento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((indirizziSpedizione == null) ? 0 : indirizziSpedizione.hashCode());
		result = prime * result + ((nTelefono == null) ? 0 : nTelefono.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (admin == null) {
			if (other.admin != null)
				return false;
		} else if (!admin.equals(other.admin))
			return false;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (elencoMetodiPagamento == null) {
			if (other.elencoMetodiPagamento != null)
				return false;
		} else if (!elencoMetodiPagamento.equals(other.elencoMetodiPagamento))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (indirizziSpedizione == null) {
			if (other.indirizziSpedizione != null)
				return false;
		} else if (!indirizziSpedizione.equals(other.indirizziSpedizione))
			return false;
		if (nTelefono == null) {
			if (other.nTelefono != null)
				return false;
		} else if (!nTelefono.equals(other.nTelefono))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sesso == null) {
			if (other.sesso != null)
				return false;
		} else if (!sesso.equals(other.sesso))
			return false;
		return true;
	}
	
	
	
	
	
}
