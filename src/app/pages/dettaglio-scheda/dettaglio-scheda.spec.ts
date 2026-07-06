import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DettaglioScheda } from './dettaglio-scheda';

describe('DettaglioScheda', () => {
  let component: DettaglioScheda;
  let fixture: ComponentFixture<DettaglioScheda>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DettaglioScheda],
    }).compileComponents();

    fixture = TestBed.createComponent(DettaglioScheda);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
