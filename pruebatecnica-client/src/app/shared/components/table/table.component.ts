import { Page } from 'src/app/core/models/page';
import { Component , Input , Output , EventEmitter} from '@angular/core';
import { UrlMethod } from 'src/app/core/models/urlMethod';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {

  @Input() columns: string[] = [];
  @Input() columnNamesMap: { [key: string]: string } = {};
  @Input() data: any[] = [];
  @Input() totalPages?: number = 1;
  @Input() currentPage: number = 1;

  @Output() pageChange = new EventEmitter<number>();
  @Output() urlSend = new EventEmitter<UrlMethod>();

  changePage(page: number): void {
    if (page > 0 && page <= this.totalPages!) {
      this.pageChange.emit(page);
    }
  }

  sendUrl (url : String , method : String , rel : String){
    const urlMethod: UrlMethod = { url, method , rel };
    this.urlSend.emit(urlMethod)
  }


  getIconClass(rel: string): string {
    switch (rel) {
      case 'update':
        return 'fa fa-edit';
      case 'delete':
        return 'fa fa-trash';
      case 'self':
        return 'fa fa-link';
      case 'toggle-estado':
        return 'fa fa-toggle-on';
      default:
        return 'fa fa-info-circle';
    }
  }

  getButtonClass(rel: string): string {
    switch (rel) {
      case 'update':
        return 'btn-success'; // Botón azul para actualizar
      case 'toggle-estado':
        return 'btn-danger'; // Botón amarillo para toggle
      default:
        return 'btn-light'; // Botón blanco por defecto
    }
  }


}
