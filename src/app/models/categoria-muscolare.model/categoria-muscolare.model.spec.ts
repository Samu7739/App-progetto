import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriaMuscolareModel } from './categoria-muscolare.model';

describe('CategoriaMuscolareModel', () => {
  let component: CategoriaMuscolareModel;
  let fixture: ComponentFixture<CategoriaMuscolareModel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoriaMuscolareModel],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoriaMuscolareModel);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
