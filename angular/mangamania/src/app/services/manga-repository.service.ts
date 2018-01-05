import { Injectable } from '@angular/core';
import { Manga } from '../metier/manga';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Page } from '../metier/page';


@Injectable()
export class MangaRepositoryService {

  private mangaSubject : BehaviorSubject<Page<Manga>>;
  private searchTitre: String; // pour effectuer une recherche sur le titre
  private noPage: number;

  public setNoPage(no : number) :void{
    this.noPage = no;
    this.refreshListe();
  }

  constructor(private _http: HttpClient) {
          this.searchTitre="";
          this.mangaSubject = new BehaviorSubject(new Page([], 0, 0, 0, 0, 1,  true, false, null));
          this.noPage = 0;
                  
   }
   // pour modifier le filtrage de la listes des mangas en fonction du titre
   public changeSearch(searchTerm : string) : void{
     this.searchTitre = searchTerm;
     this.refreshListe();
   }

public listeMangaObservable() : Observable<Page<Manga>>{
      return this.mangaSubject.asObservable();
   }

   public refreshListe(): void {

      let url = "http://localhost:8080/mangamania/pmangas";
      if(this.searchTitre != ""){
        url+=`/search/${this.searchTitre}`;// attention c'est bien avec les bas de cote
      }
      url+=`?page=${this.noPage}`;
      this._http.get<Page<Manga>>(url)
                .toPromise()
                .then(mangas => this.mangaSubject.next(mangas))
   }

   public saveManga(manga : Manga) : Promise<Manga>{
      const httpoptions = {
        headers: new HttpHeaders({'Content-Type' : 'application/json'})
      };
      if (manga.id == 0){
        // il s'agit d'une insertion
        return this._http.post<Manga>("http://localhost:8080/mangamania/mangas", manga, httpoptions).toPromise();
      }
      else{
        // mise a jour
        return this._http.put<Manga>("http://localhost:8080/mangamania/mangas", manga, httpoptions).toPromise();
        //throw 'not supported';
      }
     
   }

   // trouver le manga avec l'id voulu
  findManga(id : number) : Promise<Manga> {
    // ici, on renvoie une promesse qui contiendra le manga renvoyé
    let url = `http://localhost:8080/mangamania/mangas/${id}`;
    return this._http.get<Manga>(url).toPromise();
  }


  deleteManga(id : number) : Promise<Manga> {
    let url = `http://localhost:8080/mangamania/mangas/${id}`;
    return this._http.delete<Manga>(url).toPromise();
  }
  
}
