package model;

import java.util.Date;

public class UserBean {

	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String email;
	private String nTelefono;
	private String password;
	private String via;
	private String citta;
	private String CAP;
	private String provincia; //provincia di residenza del cliente
	private String sesso; //puo valere F o M
	private String nCivico;
	private Date dataNascita;
	
	
	public UserBean() {

	}
	

	@Override
	public String toString() {
		return "UserBean [nome=" + nome + ", cognome=" + cognome + ", codiceFiscale=" + codiceFiscale + ", email="
				+ email + ", nTelefono=" + nTelefono + ", password=" + password + ", via=" + via + ", citta=" + citta
				+ ", CAP=" + CAP + ", provincia=" + provincia + ", sesso=" + sesso + ", nCivico=" + nCivico
				+ ", dataNascita=" + dataNascita + "]";
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
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCAP() {
		return CAP;
	}
	public void setCAP(String cAP) {
		CAP = cAP;
	}
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	public String getSesso() {
		return this.sesso;
	}
	
	public void setSesso(String sesso){
		this.sesso = sesso;
	}
	
	public String getnCivico() {
		return this.nCivico;
	}
	
	public void setnCivico(String valore) throws Exception {
		
		this.nCivico = valore;
	}

	public Date getDataNascita() {
		return this.dataNascita;
	}
	
	public void setDataNascita(Date data) {
		this.dataNascita = data;
	}
	
	
	
	
	
}
