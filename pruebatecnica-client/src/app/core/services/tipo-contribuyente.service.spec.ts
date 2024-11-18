import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EntidadService } from './entidad.service';
import { of } from 'rxjs';

describe('EntidadService', () => {
  let service: EntidadService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EntidadService],
    });
    service = TestBed.inject(EntidadService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('debería obtener las entidades con paginación', () => {
    const mockResponse = {
      content: [],
      totalElements: 10,
      totalPages: 2,
    };

    service.getEntities(0, 5).subscribe(response => {
      expect(response.content).toEqual([]);
      expect(response.totalElements).toBe(10);
      expect(response.totalPages).toBe(2);
    });

    const req = httpMock.expectOne((req) =>
      req.method === 'GET' && req.url === 'http://localhost:8080/api/v1/entidades'
    );
    expect(req.request.params.has('page')).toBeTrue();
    expect(req.request.params.get('page')).toBe('0');
    expect(req.request.params.has('size')).toBeTrue();
    expect(req.request.params.get('size')).toBe('5');
    req.flush(mockResponse);
  });

  it('debería hacer una solicitud POST cuando se llame a handleRequest con el método POST', () => {
    const mockPayload = { name: 'Nueva Entidad' };
    const mockUrlMethod = {
      url: 'http://localhost:8080/api/v1/entidades',
      method: 'POST',
      rel: 'self'
    };

    const postSpy = spyOn(service['http'], 'post').and.returnValue(of({}));

    service.handleRequest(mockUrlMethod, mockPayload);

    expect(postSpy).toHaveBeenCalledWith(mockUrlMethod.url, mockPayload);
  });

  it('debería hacer una solicitud PATCH cuando se llame a patchRequest', () => {
    const mockPayload = { name: 'Entidad Actualizada' };
    const mockUrl = 'http://localhost:8080/api/v1/entidades/1';

    // Espiamos el método patch del servicio HttpClient
    const patchSpy = spyOn(service['http'], 'patch').and.returnValue(of({}));

    service.patchRequest(mockUrl, mockPayload);

    expect(patchSpy).toHaveBeenCalledWith(mockUrl, mockPayload);
  });

  // Test para el método getRequest
  it('debería hacer una solicitud GET cuando se llame a getRequest', () => {
    const mockUrl = 'http://localhost:8080/api/v1/entidades/1';

    // Espiamos el método get del servicio HttpClient
    const getSpy = spyOn(service['http'], 'get').and.returnValue(of({}));

    service.getRequest(mockUrl);

    expect(getSpy).toHaveBeenCalledWith(mockUrl);
  });

  // Test para el método postRequest
  it('debería hacer una solicitud POST cuando se llame a postRequest', () => {
    const mockPayload = { name: 'Nueva Entidad' };
    const mockUrl = 'http://localhost:8080/api/v1/entidades';

    // Espiamos el método post del servicio HttpClient
    const postSpy = spyOn(service['http'], 'post').and.returnValue(of({}));

    service.postRequest(mockUrl, mockPayload);

    expect(postSpy).toHaveBeenCalledWith(mockUrl, mockPayload);
  });

  afterEach(() => {
    httpMock.verify();
  });
});
