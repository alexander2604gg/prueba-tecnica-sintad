import { Component, Input, Output, EventEmitter, OnInit, OnChanges } from '@angular/core';

declare const bootstrap: any;

@Component({
  selector: 'app-entity-modal',
  templateUrl: './entity-modal.component.html',
  styleUrls: ['./entity-modal.component.css']
})

export class EntityModalComponent implements OnInit, OnChanges {

  @Input() isVissible: boolean = false; // Recibe la visibilidad desde el componente padre
  @Output() isVissibleChange = new EventEmitter<boolean>(); // Emite cambios hacia el componente padre
  tiposDocumentos: any[] = [];  // Aquí deberás cargar los tipos de documento
  tiposContribuyentes: any[] = [];  // Aquí deberás cargar los tipos de contribuyente
  //entidad: EntidadCreateDto = new EntidadCreateDto();

  modal: any;

  ngOnInit(): void {
    this.initializeModal();
  }

  ngOnChanges(): void {
    this.manageModalVisibility();
  }

  // Inicializa el modal solo una vez al iniciar
  private initializeModal(): void {
    const modalElement = document.getElementById('genericModal');
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
