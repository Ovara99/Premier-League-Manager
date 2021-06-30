import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortByMatchDateComponent } from './sort-by-match-date.component';

describe('SortByMatchdateComponent', () => {
  let component: SortByMatchDateComponent;
  let fixture: ComponentFixture<SortByMatchDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SortByMatchDateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SortByMatchDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
