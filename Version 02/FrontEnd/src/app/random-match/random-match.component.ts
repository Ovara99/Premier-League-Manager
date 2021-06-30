import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-random-match',
  templateUrl: './random-match.component.html',
  styleUrls: ['./random-match.component.css']
})
export class RandomMatchComponent implements OnInit {
  randomMatchData: any;

  constructor(private appService: AppService) { }

  ngOnInit() { }

  generateRandomMatch() {
    this.appService.randomMatch().subscribe((data: any[]) => {
      this.randomMatchData = data;
      if(this.randomMatchData == null) {
        alert("To generate a random football match there should be at least two football clubs" +
          " which are currently playing in the Premier League")
        this.randomMatchData = [];
      }
    })
  }
}
