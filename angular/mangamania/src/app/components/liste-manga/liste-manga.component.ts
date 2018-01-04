import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Manga } from '../../metier/manga';
import { MangaRepositoryService } from '../../services/manga-repository.service';

@Component({
  selector: 'app-liste-manga',
  templateUrl: './liste-manga.component.html',
  styleUrls: ['./liste-manga.component.css']
})
export class ListeMangaComponent implements OnInit {

  public mangas : Observable<Manga[]>;
  constructor(private _mangaRepository: MangaRepositoryService) { }

  ngOnInit() {
     // ecoute la liste des mangas
     this.mangas= this._mangaRepository.listeMangaObservable();
     // demande au service de rafraichir la liste à partir du backend rest
     this._mangaRepository.refreshListe();
  }

  public deleteManga(id : number): void{

    this._mangaRepository.deleteManga(id).then(m => {
              console.log("supprimer" + m.id);
              this._mangaRepository.refreshListe();
    });
  }

}
