import { TipoContribuyenteService } from './../../../core/services/tipo-contribuyente.service';
import { TipoDocumentoService } from './../../../core/services/tipo-documento.service';
import { Component , Input , Output , EventEmitter } from '@angular/core';
import { EntidadCreateDto } from 'src/app/core/models/entidadCreateDto';
import { TipoDocumentoResponseDto } from 'src/app/core/models/tipoDocumentoDto';
import { TipoContribuyenteResponseDto } from 'src/app/core/models/tipoContribuyenteResponseDto';
import { catchError } from 'rxjs/operators';
import { EntityModel } from 'src/app/core/models/entityModel';
import { Subscription , of } from 'rxjs';

@Component({
  selector: 'app-entidad-form',
  templateUrl: './entidad-form.component.html',
  styleUrls: ['./entidad-form.component.css']
})
export class EntidadFormComponent {

  @Input() entidad: EntidadCreateDto = {} as EntidadCreateDto;
  @Output() formSubmitted = new EventEmitter<EntidadCreateDto>();
  private subscription: Subscription = new Subscription();
  tiposDocumentos: any[] = [];
  tiposContribuyentes: any[] = [];

  constructor(
    private tipoDocumentoService: TipoDocumentoService,
    private tipoContribuyenteService: TipoContribuyenteService
  ) {}

  ngOnInit(): void {
    this.loadTiposDocumentos();
    this.loadTiposContribuyentes();
  }

  private loadTiposDocumentos(): void {
    const documentosSubscription = this.tipoDocumentoService.getAllTiposContribuyentes()
      .pipe(
        catchError(error => {
          return of([]);
        })
      )
      .subscribe(
        (response: EntityModel<TipoDocumentoResponseDto>[]) => {
          response
            .forEach((item) => (
              this.tiposDocumentos.push(item)
            ));
        }
      );

    this.subscription.add(documentosSubscription);
    console.log(this.tiposDocumentos)
}


private loadTiposContribuyentes(): void {
  const contribuyentesSubscription = this.tipoContribuyenteService.getAllTiposContribuyentes()
    .pipe(
      catchError(error => {
        console.error('Error cargando tipos de contribuyentes:', error);
        return of([]);
      })
    )
    .subscribe(
      (response: EntityModel<TipoContribuyenteResponseDto>[]) => {
        response.forEach((item) => (
          this.tiposContribuyentes.push(item)
        ));
      }
    );

  this.subscription.add(contribuyentesSubscription);
}


  onSubmit(): void {
    // Emitir los datos cuando el formulario sea enviado
    this.formSubmitted.emit(this.entidad);
  }

}
