import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleCicloComponent } from './detalle-ciclo.component';

describe('DetalleCicloComponent', () => {
  let component: DetalleCicloComponent;
  let fixture: ComponentFixture<DetalleCicloComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalleCicloComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleCicloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
