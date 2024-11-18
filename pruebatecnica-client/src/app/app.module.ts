import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TableComponent } from './shared/components/table/table.component';
import { TitleComponent } from './shared/components/title/title.component';
import { EntidadComponent } from './features/entidad/entidad/entidad.component';
import { SidebarComponent } from './shared/components/sidebar/sidebar.component';
import { HttpClientModule } from '@angular/common/http';
import { EntityModalComponent } from './shared/components/entity-modal/entity-modal.component';
import { EntidadFormComponent } from './shared/components/entidad-form/entidad-form.component';
import { FormsModule } from '@angular/forms';
import { DeleteWargingComponent } from './shared/components/delete-warging/delete-warging.component';
import { DeleteModalComponent } from './shared/components/delete-modal/delete-modal.component';
import { LoginComponent } from './shared/components/login/login.component';
import { LoginPageComponent } from './features/login/login-page/login-page.component';

@NgModule({
  declarations: [
    AppComponent,
    TableComponent,
    TitleComponent,
    EntidadComponent,
    SidebarComponent,
    EntityModalComponent,
    EntidadFormComponent,
    DeleteWargingComponent,
    DeleteModalComponent,
    LoginComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
