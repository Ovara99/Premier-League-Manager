import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { RandomMatchComponent } from './random-match/random-match.component';
import { SortByMatchDateComponent } from './sort-by-match-date/sort-by-match-date.component';
import { SearchByMatchDateComponent } from './search-by-match-date/search-by-match-date.component';
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent},
  { path: 'league-table', component: LeagueTableComponent},
  { path: 'random-match', component: RandomMatchComponent},
  { path: 'sort-by-match-date', component: SortByMatchDateComponent},
  { path: 'search-by-match-date', component: SearchByMatchDateComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full'},
  { path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
