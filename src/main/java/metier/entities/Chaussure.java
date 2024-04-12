package metier.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CHAUSSURES")

public class Chaussure implements Serializable{
	
	@Id
	@Column (name="ID_CHAUSS")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idChauss;
	
	@Column (name="NOM_CHAUSS")
	private String nomChauss;
	private double prix;
	
	@ManyToOne
	private Lieu lieu;
	public Chaussure(String nomChauss, double prix, Lieu lieu) {

		super();
		this.nomChauss = nomChauss;
		this.prix = prix;
		this.setLieu(lieu);
		}
		public Lieu getLieu() {
		return lieu;
		}
		public void setLieu(Lieu lieu) {
		this.lieu = lieu;
		}
	public Chaussure() {
	super();
	}
	public Chaussure(String nomChauss, double prix) {
	super();
	this.nomChauss = nomChauss;
	this.prix = prix;
	}
	public Long getIdChaussure() {
	return idChauss;
	}
	public void setIdChaussure(Long idChauss) {
	this.idChauss = idChauss;
	}
	public String getNomChaussure() {
	return nomChauss;
	}
	public void setNomChaussure(String nomChauss) {
	this.nomChauss = nomChauss;
	}
	public double getPrix() {
	return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
		}
	@Override
	public String toString() {
		return "Chaussure [idChauss=" + idChauss + ", nomChauss=" + nomChauss + ", prix=" + prix + "]";
	}
}

