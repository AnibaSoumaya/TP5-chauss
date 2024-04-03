package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import metier.entities.Chaussure;
import util.JPAutil;
public class ChaussureDaoImpl implements iChaussDao {
private EntityManager entityManager=JPAutil.getEntityManager("TP5_chauss");
@Override
public Chaussure save(Chaussure p) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(p);
	tx.commit();
	return p;
	}
	@Override
	public List<Chaussure> chaussuresParMC(String mc) {
	List<Chaussure> chauss =
	entityManager.createQuery("select c from Chaussure c where c.nomChauss like :mc").setParameter("mc", "%"+mc+"%").getResultList();

	return chauss;
	}
	@Override
	public Chaussure getChaussure(Long id) {
	return entityManager.find(Chaussure.class, id);
	}
	@Override
	public Chaussure updateChaussure(Chaussure p) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.merge(p);
	tx.commit();
	return p;
	}
	@Override
	public void deleteChaussure(Long id) {
		Chaussure produit = entityManager.find(Chaussure.class, id);
	entityManager.getTransaction().begin();
	entityManager.remove(produit);
	entityManager.getTransaction().commit();
	}
	
}