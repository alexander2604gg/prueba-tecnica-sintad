import { UrlMethod } from 'src/app/core/models/urlMethod';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit} from '@angular/core';
import { EntidadService } from 'src/app/core/services/entidad.service';
import { catchError } from 'rxjs/operators';
import { Page } from 'src/app/core/models/page';
import { EntityModel } from 'src/app/core/models/entityModel';
import { EntidadResponseDto } from 'src/app/core/models/entidadResponseDto';
import { Subscription , of } from 'rxjs';

@Component({
  selector: 'app-entidad',
  templateUrl: './entidad.component.html',
  styleUrls: ['./entidad.component.css']
})
export class EntidadComponent implements OnInit {

  urlMethod : UrlMethod = {} as UrlMethod;
  isModalEntidadVisible: boolean = false;
  isModalDeleteVisible: boolean = false;
  totalPages: number | undefined;
  currentPage: number | undefined;
  entidadPage: Page<EntityModel<EntidadResponseDto>> | null = null;
  private subscription: Subscription = new Subscription();
  title: string = "Lista de entidades";

  columns: string[] = ["razonSocial", "nombreComercial", "tipoDocumento", "nroDocumento", "tipoContribuyente", "links"];

  columnNamesMap: { [key: string]: string } = {
    razonSocial: "Razón Social",
    nombreComercial: "Nombre Comercial",
    tipoDocumento: "Tipo de Documento",
    nroDocumento: "Número de Documento",
    tipoContribuyente: "Tipo de Contribuyente",
    links: "Acciones",
  };

  entityModel: EntityModel<EntidadResponseDto>[] = [];

  constructor(private entidadService: EntidadService, private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchEntidad(0, 10);
  }

  fetchEntidad(page: number, size: number): void {
    const entitiesSubscription = this.entidadService.getEntities(page, size)
      .pipe(
        catchError(error => {
          return of({ content: [] } as Partial<Page<EntityModel<EntidadResponseDto>>>);
        })
      )
      .subscribe(
        (response: Partial<Page<EntityModel<EntidadResponseDto>>>) => {
          this.entityModel = response.content || [];
          this.totalPages = response.totalPages;
          this.currentPage = response.pageable?.pageNumber;
          console.log(this.entityModel);
        }
      );

    this.subscription.add(entitiesSubscription);
  }


  getUrl(urlMethod: UrlMethod): void {

    this.urlMethod = urlMethod;
    if (urlMethod.rel === "toggle-estado"){
      this.openDeleteModal()
    }
    if(urlMethod.rel === "update"){
      this.openEntidadModal()
    }

  }

  openEntidadModal(): void {
    this.isModalEntidadVisible = true;
  }

  openDeleteModal(): void {
    this.isModalDeleteVisible = true;
  }

  onPageChange(page: number): void {
    this.fetchEntidad(page - 1, 10);
  }

}
