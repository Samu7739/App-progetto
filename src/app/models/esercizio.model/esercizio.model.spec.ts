import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EsercizioModel } from './esercizio.model';

describe('EsercizioModel', () => {
  let component: EsercizioModel;
  let fixture: ComponentFixture<EsercizioModel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EsercizioModel],
    }).compileComponents();

    fixture = TestBed.createComponent(EsercizioModel);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
