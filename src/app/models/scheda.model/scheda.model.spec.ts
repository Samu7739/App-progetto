import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedaModel } from './scheda.model';

describe('SchedaModel', () => {
  let component: SchedaModel;
  let fixture: ComponentFixture<SchedaModel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SchedaModel],
    }).compileComponents();

    fixture = TestBed.createComponent(SchedaModel);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
