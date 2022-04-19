import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalePageComponent } from './locale-page.component';

describe('LocalePageComponent', () => {
  let component: LocalePageComponent;
  let fixture: ComponentFixture<LocalePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocalePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
