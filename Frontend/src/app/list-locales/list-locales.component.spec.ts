import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListLocalesComponent } from './list-locales.component';

describe('ListLocalesComponent', () => {
  let component: ListLocalesComponent;
  let fixture: ComponentFixture<ListLocalesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListLocalesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListLocalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
