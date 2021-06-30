import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByMatchDateComponent } from './search-by-match-date.component';

describe('SearchByMatchdateComponent', () => {
  let component: SearchByMatchDateComponent;
  let fixture: ComponentFixture<SearchByMatchDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchByMatchDateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchByMatchDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
