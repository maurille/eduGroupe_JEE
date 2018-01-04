package com.mau.mangamania.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mau.mangamania.metier.Manga;

public interface MangaRepository extends PagingAndSortingRepository<Manga, Long>{
 List<Manga> findByTitreContaining(String titre);
}
