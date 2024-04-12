package web;

import java.util.ArrayList;
import java.util.List;

import metier.entities.Lieu;


public class LieuModel {

	List<Lieu> lieus = new ArrayList<>();
	public List<Lieu> getLieus() {
	return lieus;
	}
	public void setLieus(List<Lieu> lieus) {
	this.lieus = lieus;
	}
}
