import { Component } from '@angular/core';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  state: boolean | undefined;

  handleLoginResult (isAuthenticate : boolean){
    this.state = isAuthenticate;
  }

}
