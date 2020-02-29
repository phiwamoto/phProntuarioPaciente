

http://www.ciceroednilson.com.br/desenvolvendo-uma-aplicacao-web-com-angular-4-e-spring-boot-parte-6-criando-o-crud/


> MYSQL

CREATE DATABASE crud_pessoa;

CREATE TABLE crud_pessoa.tb_pessoa
(
    id_pessoa         INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    ds_nome           VARCHAR(100) NOT NULL,
    fl_ativo          bit     NOT NULL
);




> STS

 - Então com o Spring Tool Suite aberto vamos clicar no menu File -> New -> Spring Starter Project. Preencher como mostra abaixo e Next:
    - name: CadastroPaciente
    - group: br.com.phcode
    - artifact: CadastroPaciente
    - description: Sistema de gestão de histórico evolutivo de paciente;
    - package: br.com.phcode
 
 - Marcar conforme abaixo e finish
    - Spring Data JPA (SQL)
    - Spring Data JDBC (SQL)	
    - MYSQL Driver (SQL)
    - Spring WEB (WEB)	

 - Abrir o "application.properties" que fica no diretório "src/main/resources"
    - Ajustar as configurações e MySQL e servidor Tomcat Embedded.

#PORTA ONDE VAI SER EXECUTADO O NOSSO TOMCAT
server.port = 8090
 
#INFORMAÇÕES PARA CONEXÃO COM O BANCO DE DADOS MYSQL
spring.datasource.url=jdbc:mysql://localhost:3306/crud_pessoa
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.jdbc.Driver


 - Agora vamos criar um pacote com o nome de "br.com.phcode.model", depois vamos adicionar uma classe com o nome de "PessoaModel"
    - Classe para mapear a nossa tabela:
 
package br.com.phcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Table(name="tb_pessoa")
@Entity
public class PessoaModel {
 
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id_pessoa")
	private Integer codigo;
 
	@Column(name="ds_nome")
	private String  nome;
 
	@Column(name="fl_ativo", columnDefinition="BIT")
	private boolean ativo;
 
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}	
 }
 
 
 - Agora vamos criar um pacote com o nome de "br.com.phcode.repository", depois vamos adicionar uma inteface com o nome de "PessoaRepository"
    - Esse pacote vai conter o nosso objeto que vai realizar a persistência no Banco de Dados:
 
package br.com.phcode.repository;

import java.util.List;
import org.springframework.data.repository.Repository;
import br.com.phcode.model.PessoaModel;
 
public interface PessoaRepository extends Repository<PessoaModel, Integer> {
 
	void save(PessoaModel pessoa);
 
	void delete(PessoaModel pessoa);
 
	List<PessoaModel> findAll();
 
	PessoaModel findOne(Integer id);
}
 
 
 - Voltando ao nosso pacote "br.com.phcode.model", vamos adicionar uma classe com o nome de ResponseModel
    - Essa classe vai retornar o resultado em Json da execução das nossas operações que vamos criar para a nossa API mais a frente

package br.com.phcode.model;

public class ResponseModel {
	 
	private int codigo;
	private String mensagem;
 
	public ResponseModel() {}
 
	public ResponseModel(int codigo, String mensagem) {
		this.codigo   = codigo;
		this.mensagem =  mensagem;
	}
 
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
 
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}



 - Agora vamos criar um pacote com o nome de "br.com.phcode.service" e vamos adicionar uma classe com o nome de "PessoaService"
    - Veja que na classe que criamos já temos todas as operações para a realização de um CRUD no nosso serviço REST.
 
package br.com.phcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.phcode.model.PessoaModel;
import br.com.phcode.model.ResponseModel;
import br.com.phcode.repository.PessoaRepository;
 
@RestController
@RequestMapping("/service")
public class PessoaService {
 
	@Autowired
	private PessoaRepository pessoaRepository; 
 
	/**
	 * SALVAR UM NOVO REGISTRO
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value="/pessoa", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody PessoaModel pessoa){
 		try {
 			this.pessoaRepository.save(pessoa);
			return new ResponseModel(1,"Registro salvo com sucesso!");
		} catch(Exception e) {
			return new ResponseModel(0,e.getMessage());			
		}
	}
 
	/**
	 * ATUALIZAR O REGISTRO DE UMA PESSOA
	 * @param pessoa
	 * @return
	 */
	@RequestMapping(value="/pessoa", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody PessoaModel pessoa){
		try {
			this.pessoaRepository.save(pessoa);		
			return new ResponseModel(1,"Registro atualizado com sucesso!");
		}catch(Exception e) {
			return new ResponseModel(0,e.getMessage());
		}
	}
 
	/**
	 * CONSULTAR TODAS AS PESSOAS
	 * @return
	 */
	@RequestMapping(value="/pessoa", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PessoaModel> consultar(){
		return this.pessoaRepository.findAll();
	}
 
	/**
	 * BUSCAR UMA PESSOA PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody PessoaModel buscar(@PathVariable("codigo") Integer codigo){
 		return this.pessoaRepository.findOne(codigo);
	}
 
	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value="/pessoa/{codigo}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Integer codigo){
 		PessoaModel pessoaModel = pessoaRepository.findOne(codigo);
 		try {
 			pessoaRepository.delete(pessoaModel);
 			return new ResponseModel(1, "Registro excluido com sucesso!");
 		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}
} 
 

 - Testar
    - POST
    - http://localhost:8090/service/pessoa 

{
 	"nome": "Juca Tigre",
	"ativo": true
}

 - Testar
    - PUT
    - http://localhost:8090/service/pessoa 

{
 	"codigo": 1,
 	"nome": "Juca Leao",
	"ativo": true
}	
	

> ANGULAR

 - O Angular CLI facilita a criação de um projeto com Angular, e já nos traz uma aplicação estruturada com as melhores práticas determinas para o Angular.
 - Lembrando que na introdução eu falei que devemos ter o Node e o Npm instalado, depois que tivermos realizado a instalação do Node e do 
   NPM devemos executar o comando abaixo para instalar o Angular CLI.
 
npm install -g @angular/cli
npm install -g @angular/cli@7.8.3

npm uninstall -g @angular-cli
npm uninstall -g @angular-cli@7.8.3
npm uninstall -g @angular/cli@6.0.8

 - Criar um novo projeo

ng new crud-pessoa-front

 - Acessar o projeto e subir o serviço

cd crud-pessoa-front
ng serve --open

 
 - Abrir o arquivo "app.component.ts"
 
    - No nosso projeto todos arquivos .ts estão escrito em TypeScript, mas o que é esse tal TypeScript? TypeScript é um projeto 
	  Open Source criado pela Microsoft que não passa de um transpiler de Javascript.
    - Antes de começarmos a meter a mão na massa vamos entender um pouco como funciona o Angular, então vamos abrir o arquivo app.component.ts 
	  como mostra a imagem abaixo. 
	  
    - No Angular 2/4 vamos ter um Componente com metadata que vai ser responsável por interligar o nosso Componente com a nossa view(.html), 
	  esse metadata é o @Component, no nosso componente vamos colocar toda a lógica de interação com o usuário.
    - O Gerenciamento dos componentes são feitos pelo proprio Angular, assim o mesmo se encarrega de contruir e destruir os compoentes, 
	  não fica na nossa responsabilidade esse desenvolvimento.

    - selector: Nesse parâmetro estamos informando onde a nossa view(app.component.html) vai ser renderizada.
    - templateUrl:Nesse parâmetro estamos informando qual arquivo .html vai ser renderizado no nosso selector.
    - styleUrls: Nesse parâmetro estamos informando quais arquivos css vamos usar na nossa view(.html). 
 
 
 - Abrir o arquivo "index.html"
 
    - Podemos ver que no Component AppComponent a nossa view vai ser renderizada no selector app-root, esse selector está definido no arquivo index.html
	
	
 - Abrir o arquivo "app.module.ts"
 
    - No nosso projeto temos um arquivo com o nome de app.module.ts, nesse arquivo temos um Decorator com o nome de @NgModule, 
	  e nele temos a propriedade bootstrap, é essa propriedade que vai fazer nosso Component ser renderizado na nossa view index.html, 
	  esse vai ser o primeiro componente a ser carregado na nossa aplicação.
 
 - Criar uma pasta "services" em "src -> app -> services"
 - Dentro do diretorio, criar  >> "pessoa.ts"
 
    - Essa classe vai ser o espelho da classe usada no nosso serviço REST, vamos usar ela para Serializar e Deserializar os dados do nosso serviço, 
	  veja abaixo as duas classes.
 
export class Pessoa {
 
    codigo:number;
    nome:string;				
    ativo:boolean;
}

 - Ainda no mesmo diretorio >> "response.ts"
 
    - A nossa classe Response, vai receber o resultado das operações de excluir, atualizar e deletar, nesse resultado vamos ter um código e uma mensagem.
      Até o momento nosso projeto deve ter os objetos como mostra a imagem abaixo.
 
export class Response{
 
    codigo:number;
    mensagem:string;
} 


 - Ainda no mesmo diretorio >> "config.service.ts"
 
    - Essa classe vai ter apenas a URL do nosso serviço.
   
 
export class ConfigService {
 
    private urlService:string;
 
    constructor(){
 
        this.urlService = 'http://localhost:8090/service';
    }
 
    getUrlService(): string {
 
        return this.urlService;
    }
 
}

 - Ainda no mesmo diretorio >> "pessoa.service.ts"
 
    - Agora vamos criar a classe responsável por realizar as requests no nosso serviço REST, para isso vamos adicionar um arquivo com o nome de 
	  pessoa.service.ts, e depois vamos deixar nosso arquivo com o código abaixo.
	- Veja que na classe acima temos um Decorator com o nome de @Injectable, quando usamos esse Decorator estamos injetando o nosso serviço na 
	  nossa aplicação, assim o TypeScript emiti metadados sobre o nosso serviço, pronto, agora já temos todos os itens necessários 
	  para acessar o nosso serviço. O diretório de serviços da nossa aplicação deve ficar como mostra a imagem abaixo.
 
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';
 
import {Pessoa} from '../services/pessoa';
import {ConfigService} from './config.service';
 
@Injectable()
export class PessoaService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http,
                private configService: ConfigService) { 
 
        /**SETANDO A URL DO SERVIÇO REST QUE VAI SER ACESSADO */
        this.baseUrlService = configService.getUrlService() + '/pessoa/';
 
        /*ADICIONANDO O JSON NO HEADER */
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }
 
    /**CONSULTA TODAS AS PESSOAS CADASTRADAS */
    getPessoas(){        
        return this.http.get(this.baseUrlService).map(res => res.json());
    }
 
    /**ADICIONA UMA NOVA PESSOA */
    addPessoa(pessoa: Pessoa){
 
        return this.http.post(this.baseUrlService, JSON.stringify(pessoa),this.options)
        .map(res => res.json());
    }
    /**EXCLUI UMA PESSOA */
    excluirPessoa(codigo:number){
 
        return this.http.delete(this.baseUrlService + codigo).map(res => res.json());
    }
 
    /**CONSULTA UMA PESSOA PELO CÓDIGO */
    getPessoa(codigo:number){
 
        return this.http.get(this.baseUrlService + codigo).map(res => res.json());
    }
 
    /**ATUALIZA INFORMAÇÕES DA PESSOA */
    atualizarPessoa(pessoa:Pessoa){
 
        return this.http.put(this.baseUrlService, JSON.stringify(pessoa),this.options)
        .map(res => res.json());
    }
 
} 
 

 
 - Dentro do diretorio "app" vamos criar "home"
 - home.component.html e home.component.ts 
 
    - Essa página vai ter apenas um texto, mas depois se você quiser pode adicionar mais informações, então dentro do diretório app 
	  vamos criar um diretório com o nome de home, e depois vamos adicionar dois arquivos, um com o nome de home.component.html que vai ser 
	  a nossa view, e outro com o nome de home.component.ts que vai ser o componente para a view.
 
    - Podemos ver na nossa view que temos uma expressão que não vimos ainda, o {{mensagem}}, e o que seria essa expressão? 
	  Essa expressão é conhecida como Data Binding, é dessa forma que o Angular faz para manter sincronizado os dados da View com o Component, 
	  mais a frente vamos ver que existe mais alguns Data Binding diferentes.
	  No nosso caso será atribuido o valor Página Inicial. a view atráves da variável definida no componente.
 
 - Dentro do diretorio "app" vamos criar "pessoa/cadastro"
 
    - No diretório app vamos criar um sub diretório com o nome de pessoa, e dentro do diretório pessoa vamos criar um outro diretório com o nome 
	  de cadastro, veja como deve ficar na imagem abaixo.
 
 
 
- Dentro do diretorio "app" vamos criar "pessoa/consulta" 
 
 
 
 
 
 
 
 
 
 
 
 ng --version
 ng -v
 ********************************************************************************************************
 
 > Colocando paginação
   -- https://backefront.com.br/criando-paginacao-tabela-angular/
   -- http://www.macoratti.net/19/10/ang7_pag1.htm
 
 
   -- npm install ngx-pagination --save
   

 
 
 
 
org.springframework.beans.factory.support.BeanDefinitionOverrideException: Invalid bean definition with name 'pessoaRepository' defined in null: Cannot register bean definition [Root bean: class [org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null] for bean 'pessoaRepository': There is already [Root bean: class [org.springframework.data.jdbc.repository.support.JdbcRepositoryFactoryBean]; scope=; abstract=false; lazyInit=false; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null] bound.

Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.

java.sql.SQLException: The server time zone value 'Hora oficial do Brasil' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support. 
 
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'pessoaService': Unsatisfied dependency expressed through field 'pessoaRepository'; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'pessoaRepository': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Failed to create query for method public abstract br.com.phcode.model.PessoaModel br.com.phcode.repository.PessoaRepository.findOne(java.lang.Integer)! No property findOne found for type PessoaModel! 
 
java.sql.sqlsyntaxerrorexception table 'tb_pessoa.hibernate_sequence' doesn't exist 









{
	"resource": "/c:/Desenvolvimento/crud-pessoa-front/src/app/pessoa/cadastro/cadastro.component.ts",
	"owner": "typescript",
	"code": "1219",
	"severity": 8,
	"message": "Experimental support for decorators is a feature that is subject to change in a future release. Set the 'experimentalDecorators' option in your 'tsconfig' or 'jsconfig' to remove this warning.",
	"source": "ts",
	"startLineNumber": 13,
	"startColumn": 16,
	"endLineNumber": 13,
	"endColumn": 33
}