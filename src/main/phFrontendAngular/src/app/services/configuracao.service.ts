export class ConfiguracaoService {
 
    private urlServico: string;
 
    constructor() {
         this.urlServico = 'http://localhost:8080';
    }
 
    getUrlServico(): string {
         return this.urlServico;
    }

    getUrlServicoLogin(): string {
        return this.getUrlServico() + '/login';
    }
}