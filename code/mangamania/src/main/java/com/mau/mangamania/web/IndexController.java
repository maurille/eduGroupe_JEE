package com.mau.mangamania.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.mau.mangamania.metier.Manga;
import com.mau.mangamania.repositories.MangaRepository;

@Controller
@RequestMapping(value="/")
public class IndexController {
	@Autowired
	private MangaRepository mangaRepository;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirectToIndex() {
		return "home";
	}
	
	@RequestMapping(value="/mangas", method=RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	 public List<Manga> listeManga(){
		ArrayList<Manga> data = new ArrayList<Manga>();
		mangaRepository.findAll().forEach(data::add);
		return data;
	 }

	
	@RequestMapping(value="/mangas/search/{search:.+}", method=RequestMethod.GET, produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	 public List<Manga> searchManga(@PathVariable("search") String search){
		return mangaRepository.findByTitreContaining(search);
	 }
	
	@RequestMapping(value="/mangas", method=RequestMethod.POST, 
					produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Manga saveManga(@RequestBody Manga manga) {
		return mangaRepository.save(manga);
	}
	
	@RequestMapping(value="/mangas/{id:[0-9]}", 
					method=RequestMethod.GET,
					produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	 public Manga findManga(@PathVariable("id") Long id){
			Manga m = mangaRepository.findOne(id);
			if(m ==null)
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "manga inconnu");
			return m;
	 }
	
	@RequestMapping(value="/mangas", method=RequestMethod.PUT, 
			produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Manga updateManga(@RequestBody Manga manga) {
		Manga m = mangaRepository.findOne(manga.getId());
		if(m ==null)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "manga inconnu");
return mangaRepository.save(manga);
}


	@RequestMapping(value="/mangas/{id:[0-9]}", 
			method=RequestMethod.DELETE,
			produces=org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public Manga deleteManga(@PathVariable("id") Long id){
	Manga m = mangaRepository.findOne(id);
	if(m ==null)
		throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "manga inconnu");
	mangaRepository.delete(m);
	return m;
}

}