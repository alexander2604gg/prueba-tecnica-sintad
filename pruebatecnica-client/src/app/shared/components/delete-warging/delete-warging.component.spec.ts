import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteWargingComponent } from './delete-warging.component';

describe('DeleteWargingComponent', () => {
  let component: DeleteWargingComponent;
  let fixture: ComponentFixture<DeleteWargingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeleteWargingComponent]
    });
    fixture = TestBed.createComponent(DeleteWargingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
