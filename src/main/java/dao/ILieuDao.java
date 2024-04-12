package dao;

import java.util.List;

import metier.entities.Lieu;

public interface ILieuDao {

	public Lieu save(Lieu lieu);
	public Lieu getLieu(Long id);
	public Lieu updateLieu(Lieu lieu);
	public void deleteLieu(Long id);
	public List<Lieu> getAllLieus();
}
