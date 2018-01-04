import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import 'rxjs/add/operator/debounceTime';
import { MangaRepositoryService } from '../../services/manga-repository.service';

@Component({
  selector: 'app-search-manga',
  templateUrl: './search-manga.component.html',
  styleUrls: ['./search-manga.component.css']
})
export class SearchMangaComponent implements OnInit {

  public searchTerm: string 
  private searchTermSubject: Subject<string>;

  constructor(private _managaRepository: MangaRepositoryService) {
    this.searchTermSubject = new Subject();
   }

  ngOnInit() {
    this.searchTerm = "filtrer";
    this.searchTermSubject.asObservable()
                          .debounceTime(2000)
                          .subscribe(newTerm => this._managaRepository.changeSearch(newTerm));
  }
   changeTerm(newvalue) : void{

    this.searchTermSubject.next(newvalue);
      //console.log(newvalue);
     this.searchTerm = newvalue
   }
}
