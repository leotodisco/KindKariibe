package beans;

import java.util.ArrayList;
import java.util.Date;

public class UserBean {

	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String email;
	private String nTelefono;
	private String password;
	private String sesso; //puo valere F o M
	private Date dataNascita;
	private ArrayList<MetodoPagamentoBean> elencoMetodiPagamento = new ArrayList<>();
	private IndirizzoBean indirizzoFatturazione;
	private ArrayList<IndirizzoBean> indirizziSpedizione;
	private Boolean admin;
	
	public UserBean() {

	}
	
	public ArrayList<MetodoPagamentoBean> getElencoMetodiPagamento() {
    return elencoMetodiPagamento;
}
	
	public IndirizzoBean getIndirizzoFatturazione() {
		return indirizzoFatturazione;
	}


	public void setIndirizzoFatturazione(IndirizzoBean indirizzoFatturazione) {
		this.indirizzoFatturazione = indirizzoFatturazione;
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


	public void setAdmin(Boolean admin) {
		this.admin = admin;
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
	
	public Date getDataNascita() {
		return this.dataNascita;
	}
	
	public void setDataNascita(Date data) {
		this.dataNascita = data;
	}


	public Boolean getAdmin() {
		return admin;
	}


	public void setAdmin(boolean b) {
		this.admin = b;
	}
	
	
	
	
	
}
