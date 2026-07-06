import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreaScheda } from './crea-scheda';

describe('CreaScheda', () => {
  let component: CreaScheda;
  let fixture: ComponentFixture<CreaScheda>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreaScheda],
    }).compileComponents();

    fixture = TestBed.createComponent(CreaScheda);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
