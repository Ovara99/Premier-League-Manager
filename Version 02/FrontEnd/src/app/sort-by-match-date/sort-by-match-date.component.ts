import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-sort-by-match-date',
  templateUrl: './sort-by-match-date.component.html',
  styleUrls: ['./sort-by-match-date.component.css']
})
export class SortByMatchDateComponent implements OnInit {
  matchTableData: any;

  constructor(private appService: AppService) { }

  ngOnInit() { }

  sortByMatchDate() {
    this.appService.sortMatches().subscribe((data: any[]) => {
      this.matchTableData = data;
      if(this.matchTableData == null) {
        alert("There aren't any football matches played in the Premier League yet.")
        this.matchTableData = [];
      }
    })
  }
}
