import { Component, OnInit } from '@angular/core';
import { Login } from 'src/app/models/login.model';
import { LoginService } from 'src/app/services/login.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'phi-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private login: Login = new Login();

  constructor(private loginService: LoginService) { 

  }

  ngOnInit() {

  }

  logar() {
    console.log('ENTRANDO NO LOGAR')
    this.loginService.logar(this.login).subscribe(response => {
      // validar o certo
      console.log('BOM')
      console.log(response)
      //console.log(response)
      //console.log(this.headers)
      //console.log(this.headers())
     }
     ,
     (error) => {   
      console.log('RUIM')
      // validar o erro
        //  console.log(' CRIAR PESSOA > ERRO 2')
        // alert(error);
     });
  }

  protected headers(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }
}
