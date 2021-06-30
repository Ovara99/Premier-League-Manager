import { Component, OnInit } from '@angular/core';
import {faCaretDown} from '@fortawesome/free-solid-svg-icons';
import {AppService} from '../app.service';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})

export class LeagueTableComponent implements OnInit {
  faCaretDown = faCaretDown;
  leagueTableData: any;

  constructor(private appService: AppService) { }

  ngOnInit() { }

  sortTableByPoints() {
    this.appService.sortLeagueTableByPoints().subscribe((data: any[]) => {
      this.leagueTableData = data;
      if(this.leagueTableData == null) {
        alert("There aren't any football clubs in the Premier League at the moment. " +
          "Therefore, there are no statistics to show in the Premier League Table")
        this.leagueTableData = [];
      }
    })
  }

  sortTableByGoalsScored() {
    this.appService.sortLeagueTableByGoalsScored().subscribe((data: any[]) => {
      this.leagueTableData = data;
      if(this.leagueTableData == null) {
        alert("There aren't any football clubs in the Premier League at the moment. " +
          "Therefore, there are no statistics to show in the Premier League Table")
        this.leagueTableData = [];
      }
    })
  }

  sortTableByNoOfWins() {
    this.appService.sortLeagueTableByNoOfWins().subscribe((data: any[]) => {
      this.leagueTableData = data;
      if(this.leagueTableData == null) {
        alert("There aren't any football clubs in the Premier League at the moment. " +
          "Therefore, there are no statistics to show in the Premier League Table")
        this.leagueTableData = [];
      }
    })
  }

}
