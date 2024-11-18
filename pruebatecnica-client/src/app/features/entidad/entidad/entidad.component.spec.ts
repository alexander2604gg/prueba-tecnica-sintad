import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EntidadComponent } from './entidad.component';
import { EntidadService } from 'src/app/core/services/entidad.service';
import { of } from 'rxjs';
import { EntityModel } from 'src/app/core/models/entityModel';
import { EntidadResponseDto } from 'src/app/core/models/entidadResponseDto';
import { Page } from 'src/app/core/models/page';

describe('EntidadComponent', () => {
  let component: EntidadComponent;
  let fixture: ComponentFixture<EntidadComponent>;
  let entidadService: EntidadService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EntidadComponent],
      imports: [HttpClientTestingModule],
      providers: [EntidadService]
    });
    fixture = TestBed.createComponent(EntidadComponent);
    component = fixture.componentInstance;
    entidadService = TestBed.inject(EntidadService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('debería inicializar correctamente la lista de entidades', () => {
    const mockResponse: Page<EntityModel<EntidadResponseDto>> = {
      content: [{
        data: {
          id: 1,
          tipoDocumento: 'DNI',
          nroDocumento: '12345678',
          razonSocial: 'Entidad 1',
          nombreComercial: 'Comercial 1',
          tipoContribuyente: 'Normal',
          direccion: 'Calle Trujillo 123',
          telefono: '123456789',
          estado: true
        },
        links: [{ rel: 'self', href: '/api/v1/entidades/1' }]
      }],
      totalElements: 10,
      totalPages: 2,
      pageable: { pageNumber: 0, pageSize: 5 },
      last: false
    };

    spyOn(entidadService, 'getEntities').and.returnValue(of(mockResponse));

    component.ngOnInit();
    fixture.detectChanges();  // Detectar cambios para asegurar que la vista se actualice

    expect(entidadService.getEntities).toHaveBeenCalledWith(0, 10);
    expect(component.entityModel.length).toBeGreaterThan(0);
    expect(component.totalPages).toBe(2);
    expect(component.currentPage).toBe(0);
  });

  it('debería cambiar la página cuando se llama a onPageChange', () => {
    const mockResponse: Page<EntityModel<EntidadResponseDto>> = {
      content: [{
        data: {
          id: 1,
          tipoDocumento: 'DNI',
          nroDocumento: '12345678',
          razonSocial: 'Entidad 1',
          nombreComercial: 'Comercial 1',
          tipoContribuyente: 'Normal',
          direccion: 'Calle Lima 123',
          telefono: '123456789',
          estado: true
        },
        links: [{ rel: 'self', href: '/api/v1/entidades/1' }]
      }],
      totalElements: 10,
      totalPages: 2,
      pageable: { pageNumber: 0, pageSize: 5 },
      last: false
    };

    spyOn(entidadService, 'getEntities').and.returnValue(of(mockResponse));

    component.onPageChange(2);

    expect(entidadService.getEntities).toHaveBeenCalledWith(1, 10);
  });

  afterEach(() => {
    httpMock.verify();
  });
});
