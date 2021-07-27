CREATE TABLE endereco (
  id INT NOT NULL AUTO_INCREMENT,
  data_criacao date NULL,    
  cep VARCHAR(10) NULL,  
  logradouro VARCHAR(350) NULL,
  numero VARCHAR(20) NULL,
  complemento VARCHAR(350) NULL,
  bairro VARCHAR(200) NULL,  
  id_cidade int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_endereco_cidade (id_cidade),
  CONSTRAINT fk_endereco_cidade FOREIGN KEY (id_cidade) REFERENCES cidade (id)    
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;