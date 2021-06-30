import { Component, OnInit } from '@angular/core';
import {faSearch} from "@fortawesome/free-solid-svg-icons";
import {AppService} from "../app.service";

@Component({
  selector: 'app-search-by-match-date',
  templateUrl: './search-by-match-date.component.html',
  styleUrls: ['./search-by-match-date.component.css']
})
export class SearchByMatchDateComponent implements OnInit {
  faSearch = faSearch;
  searchMatchData: any;

  constructor(private appService: AppService) { }

  ngOnInit() { }

  searchMatchByDate(searchDate: string) {
    this.appService.searchMatches(searchDate).subscribe((data: any[]) => {
      this.searchMatchData = data;
      if(this.searchMatchData == null) {
        alert("There aren't any matches played on the provided date in the Premier League")
        this.searchMatchData = [];
      }
    })
  }

}
