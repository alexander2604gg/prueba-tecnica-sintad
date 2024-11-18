import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntidadComponent } from './features/entidad/entidad/entidad.component';
import { LoginPageComponent } from './features/login/login-page/login-page.component';
import { authGuard } from './auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  {path: 'entidades' , component : EntidadComponent ,  canActivate : [authGuard]},
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
