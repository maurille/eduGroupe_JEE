package com.mau.nouvelAn.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mau.nouvelAn.metier.Message;

@Service
public class MessageDepot implements IMessageDepot {
	
	// injection de l'entity manager
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public List<Message> findAll(){
		//createQuery crée la requette et 
		// getResultList l'exécute
		return em.createQuery("from Message", Message.class).getResultList();
		
	}

	@Override
	@Transactional
	public Message save(Message m) {
		if (m.getId() == 0)
			em.persist(m);
		else
			m = em.merge(m);
		return null;
	}

}
