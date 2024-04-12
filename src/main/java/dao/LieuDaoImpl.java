package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import metier.entities.Lieu;
import util.JPAutil;

public class LieuDaoImpl implements ILieuDao {

	// TP6_JEE à replacer par votre persistence unit, consultez votre
	//fichier persistence.xml <persistence-unit name="TP6_JEE">
	private EntityManager entityManager=JPAutil.getEntityManager("TP5_chauss");
	@Override
	public Lieu save(Lieu lieu ) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(lieu);
	tx.commit();
	return lieu;
	}
	@Override
	public Lieu getLieu(Long id) {
	return entityManager.find(Lieu.class, id);
	}
	@Override
	public Lieu updateLieu(Lieu lieu) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.merge(lieu);
	tx.commit();
	return lieu;
	}
	@Override
	public void deleteLieu(Long id) {
		Lieu lieu = entityManager.find(Lieu.class, id);
	entityManager.getTransaction().begin();
	entityManager.remove(lieu);
	entityManager.getTransaction().commit();
	}
	@Override
	public List<Lieu> getAllLieus() {
	List<Lieu> cats =

	entityManager.createQuery("select l from Lieu l").getResultList();
	return cats;
	}
}
