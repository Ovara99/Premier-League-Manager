import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from "@angular/router";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LeagueTableComponent } from './league-table/league-table.component';
import { RandomMatchComponent } from './random-match/random-match.component';
import { SortByMatchDateComponent } from './sort-by-match-date/sort-by-match-date.component';
import { SearchByMatchDateComponent } from './search-by-match-date/search-by-match-date.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LeagueTableComponent,
    RandomMatchComponent,
    SortByMatchDateComponent,
    SearchByMatchDateComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
