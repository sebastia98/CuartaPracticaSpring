package org.formacio.setmana2.repositori;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.springframework.stereotype.Repository;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */
@Repository
public class RepositoriEscola {
	
	@PersistenceContext
	private EntityManager em;
	
	public Curs carregaCurs(String nom) {
		return em.find(Curs.class, nom);
	}
	
	
	public Matricula apunta (String alumne, String curs) throws EdatIncorrecteException {
		
		Matricula matricula = new Matricula();
		Alumne nin = em.find(Alumne.class, alumne);
		Curs promo = em.find(Curs.class, curs);
		
		if (nin.getEdat() < promo.getEdatMinima()) {
			throw new EdatIncorrecteException();
		} 
		else {
			matricula.setAlumne(nin);
			matricula.setCurs(promo);
			em.persist(matricula);
		    return matricula;
		}
		
	}
	
	
}
