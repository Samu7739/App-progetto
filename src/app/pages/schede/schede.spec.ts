import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Schede } from './schede';

describe('Schede', () => {
  let component: Schede;
  let fixture: ComponentFixture<Schede>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Schede],
    }).compileComponents();

    fixture = TestBed.createComponent(Schede);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
