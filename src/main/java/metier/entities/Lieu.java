package metier.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Lieu implements Serializable{

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idLieu;
	private String pays;
	
	@OneToMany (mappedBy="lieu")
	private List<Chaussure> chaussures;
	
	public Lieu() {
	super();
	}
	public Lieu(String pays) {
	super();
	this.pays = pays;
	}
	public Long getidLieu() {
	return idLieu;
	}
	public void setidLieu(Long idLieu) {
	this.idLieu = idLieu;
	}
	public String getpays() {
	return pays;
	}
	public void setpays(String pays) {
	this.pays = pays;
	}
	
	public List<Chaussure> getChaussures() {
	return chaussures;
	}
	public void setChaussures(List<Chaussure> chaussures) {
	this.chaussures = chaussures;
	}
}
