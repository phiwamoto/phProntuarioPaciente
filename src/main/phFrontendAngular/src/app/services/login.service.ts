import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ConfiguracaoService } from './configuracao.service';
import { Login } from '../models/login.model';
import { UserSS } from '../models/UserSS';

@Injectable()
export class LoginService {
    private urlServico: string = '';
    private headers: HttpHeaders;

    constructor(private http: HttpClient, private configuracaoService: ConfiguracaoService) { 
        this.urlServico = configuracaoService.getUrlServicoLogin();
        this.headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8')
         .set('Access-Control-Allow-Origin', '*')
         .set('Access-Control-Expose-Headers', 'Authorization')
         .set('Access-Control-Allow-Headers', 'Accept, Content-Type, Content-Length, Accept-Encoding, X-CSRF-Token, Authorization')

         //.set('access-control-expose-headers', 'Authorization')               

        //  getHeaders() {
        //     // let username = this.variables.getUsername();
        //     // let password = this.variables.getPassword();
        //      let username = "username";
        //      let password = "password";
        //      let headers =  new Headers();
        //      //headers.append("Content-Type", "text/xml");
        //      headers.append("Access-Control-Allow-Origin", "*");
        //      headers.append("Access-Control-Allow-Credentials", "true"); 
        //      headers.append("Authorization", "Basic " + btoa(username + ":" + password));
        //      headers.append("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        //      headers.append("Content-Type", "application/json,application/x-www-form-urlencoded");
        //      headers.append("Access-Control-Request-Headers", "Content-type,X-Requested-With,Origin,accept");
        //      let options = new RequestOptions({headers: headers});
        //      console.log(JSON.stringify(options));
        //      return options;
        //  }         
    }
 
    logar(login: Login): Observable<HttpResponse<any>> {  
        let params = new HttpParams();
        let headers = this.headers;

        params.set('email', login.email);
        params.set('senha', login.senha);

        let options = { headers: this.headers, params: params};
        console.log('PHI: json login', JSON.stringify(login));

        let retorno = this.http.post<HttpResponse<any>>(this.urlServico, JSON.stringify(login), options);
        console.log(retorno)

        retorno.pipe(tap(resp=> {
            console.log(resp.headers);
            console.log(resp.headers.get('Authorization'));
            console.log(resp.body);
            return resp.body;
        }));

        // console.log(retorno.subscribe(
        //     resp => { 
        //         console.log(resp.headers)
        //     }
        // ));
    
        // console.log(retorno.pipe(
        //     tap( resp => {
        //       //const token = resp.headers.get('access-control-expose-headers')

        //         //console.log('TOKEN >>>>>>>>>>>>>>>>>>> ', token)
        //         console.log('TOKEN >>>>>>>>>>>>>>>>>>> ')

        //       //localStorage.setItem('token', token);
        //     })));

       
        
        return retorno;
        //return retorno
        
        //this.http.post<Response>(this.urlServico, JSON.stringify(login), options);

        
        

        // let options = { headers: this.headers };
        // console.log('XXXXXXXX', JSON.stringify(login));

        // return this.http.post<Response>(this.urlServico, JSON.stringify(login), options);        
    }
 }