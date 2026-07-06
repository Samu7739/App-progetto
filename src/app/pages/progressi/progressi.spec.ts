import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Progressi } from './progressi';

describe('Progressi', () => {
  let component: Progressi;
  let fixture: ComponentFixture<Progressi>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Progressi],
    }).compileComponents();

    fixture = TestBed.createComponent(Progressi);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
