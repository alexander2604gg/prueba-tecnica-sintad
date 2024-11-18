import { EntidadService } from 'src/app/core/services/entidad.service';
import { UrlMethod } from 'src/app/core/models/urlMethod';
import { Component , Input , Output , EventEmitter } from '@angular/core';

declare const bootstrap: any;

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent {

  @Input() urlMethod : UrlMethod = {} as UrlMethod;
  @Input() isVissible: boolean = false;
  @Output() isVissibleChange = new EventEmitter<boolean>();
  modal: any;

  constructor (private entidadService : EntidadService){}

  ngOnInit(): void {
    this.initializeModal();
  }

  ngOnChanges(): void {
    this.manageModalVisibility();
  }

  handleMethod (){
    this.entidadService.handleRequest(this.urlMethod , {})
    this.closeModal()
  }

  // Inicializa el modal solo una vez al iniciar
  private initializeModal(): void {
    const modalElement = document.getElementById('genericModal-warning');
    if (modalElement) {
      this.modal = new bootstrap.Modal(modalElement);
    }
  }

  // Gestiona la visibilidad del modal
  private manageModalVisibility(): void {
    if (this.isVissible) {
      this.modal.show();
    } else {
      this.modal.hide();
    }
  }

  // Método para cerrar el modal y emitir el cambio de visibilidad
  closeModal(): void {
    this.isVissible = false; // Cambia el estado a false para cerrar el modal
    this.isVissibleChange.emit(this.isVissible); // Emite el cambio al componente padre

  }


}
