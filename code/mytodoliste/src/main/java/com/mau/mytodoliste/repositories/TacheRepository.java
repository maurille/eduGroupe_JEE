package com.mau.mytodoliste.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.mau.mytodoliste.metier.Tache;

public interface TacheRepository extends PagingAndSortingRepository<Tache, Integer>{
	 List<Tache> findByLibelleContaining(String libelle);
}
