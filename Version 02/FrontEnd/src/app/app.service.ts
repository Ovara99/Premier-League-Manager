import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AppService {
  private sortLeagueTableByPointsUrl = 'http://localhost:8080/api/sortLeagueTable';
  private sortLeagueTableByGoalsScoredUrl = 'http://localhost:8080/api/sortLeagueTable';
  private sortLeagueTableByNoOfWinsUrl = 'http://localhost:8080/api/sortLeagueTable';
  private randomMatchUrl = 'http://localhost:8080/api/randomMatch';
  private sortMatchesUrl = 'http://localhost:8080/api/sortMatches';
  private searchMatchesUrl = 'http://localhost:8080/api/searchMatches';

  constructor(private httpClient: HttpClient) { }

  public sortLeagueTableByPoints(): Observable<any[]> {
    const params = new HttpParams().set('sortOption', 'Points');
    return this.httpClient.get<any[]>(this.sortLeagueTableByPointsUrl, {params});
  }

  public sortLeagueTableByGoalsScored(): Observable<any[]> {
    const params = new HttpParams().set('sortOption', 'Goals Scored');
    return this.httpClient.get<any[]>(this.sortLeagueTableByGoalsScoredUrl, {params});
  }

  public sortLeagueTableByNoOfWins(): Observable<any[]> {
    const params = new HttpParams().set('sortOption', 'No of wins');
    return this.httpClient.get<any[]>(this.sortLeagueTableByNoOfWinsUrl, {params});
  }

  public randomMatch(): Observable<any[]> {
    return this.httpClient.get<any[]>(this.randomMatchUrl);
  }

  public sortMatches(): Observable<any[]> {
    return this.httpClient.get<any[]>(this.sortMatchesUrl);
  }

  public searchMatches(searchDate: string): Observable<any[]> {
    const params = new HttpParams().set('searchDate', searchDate);
    return this.httpClient.get<any[]>(this.searchMatchesUrl, {params});
  }

}
