import { Component , Output , EventEmitter } from '@angular/core';

@Component({
  selector: 'app-delete-warging',
  templateUrl: './delete-warging.component.html',
  styleUrls: ['./delete-warging.component.css']
})
export class DeleteWargingComponent {

  @Output() confirmDelete: EventEmitter<boolean> = new EventEmitter<boolean>();

  // Método para confirmar la eliminación
  confirmDeletion(): void {
    this.confirmDelete.emit(true); // Emite true al componente padre
  }



}
